import db.DataSource;
import entity.Hobby;
import entity.Person;
import entity.Persons;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person first = new Person("Aleg", new GregorianCalendar(2000, Calendar.JANUARY , 25).getTime());
        Person second = new Person("Ivgeny", new GregorianCalendar(1985, Calendar.APRIL , 13).getTime());
        second.addHobby("fishing", 30);
        second.addHobby("coocing", 70);

        List<Person> listPersons = new ArrayList<>(Arrays.asList(first, second));
        Persons persons = new Persons();
        persons.setList(listPersons);


        String SQL_QUERY_PERSON = "INSERT INTO persons (name, birthday) VALUES (?, ?);";
        String SQL_QUERY_HOBBY = "INSERT INTO hobby (hobby_name, complexity, persons_id) VALUES (?, ?, ?);";
        String SQL_QUERY_FIND = "select id from persons where name = ? and birthday = ?";
        File f = new File("test1.xml");
        try (Connection con = DataSource.getConnection();
             PreparedStatement statmentPerson = con.prepareStatement( SQL_QUERY_PERSON );
             PreparedStatement statmentHobby = con.prepareStatement( SQL_QUERY_HOBBY );
             PreparedStatement statmentFindId = con.prepareStatement( SQL_QUERY_FIND);
             BufferedReader br = new BufferedReader(new FileReader(f))){

            JAXBContext context = JAXBContext.newInstance(Persons.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(persons, new File("test1.xml"));  //сериализация

            StringBuilder result = new StringBuilder();
            String line;
            while((line = br.readLine()) != null){
                result.append(line);
            }

            StringReader reader = new StringReader(result.toString());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Persons outPersons = (Persons) unmarshaller.unmarshal(reader); //десериализация
//            System.out.println(outPersons);


            for(Person one: outPersons.getList()){
                statmentPerson.setString(1, one.getName());
                statmentPerson.setDate(2, convert(one.getBirthday()));
                statmentPerson.execute();
                if(one.getHobbyes() != null){
                    for (Hobby hobby: one.getHobbyes()){
                        statmentFindId.setString(1, one.getName());
                        statmentFindId.setDate(2, convert(one.getBirthday()));
                        ResultSet rs = statmentFindId.executeQuery();
                        int id = 0;
                        while (rs.next()){
                            id = rs.getInt(1);
                        }
                        statmentHobby.setString(1, hobby.getHobby_name());
                        statmentHobby.setInt(2, hobby.getComplexity());
                        statmentHobby.setInt(3, id);
                        statmentHobby.execute();
                    }
                }
            }

        } catch (JAXBException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
