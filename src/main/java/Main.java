import entity.Person;
import entity.Persons;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
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


        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Persons.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(persons, writer);  //сериализация
            String result = writer.toString();
            System.out.println(result);

            StringReader reader = new StringReader(result);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Persons deserelialPersons = (Persons) unmarshaller.unmarshal(reader); //десериализация
            System.out.println(deserelialPersons);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
