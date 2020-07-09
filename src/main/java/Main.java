import entity.Person;
import entity.Persons;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> listPersons = new ArrayList<>(Arrays.asList(
                new Person("Aleg", 13),
                new Person("Ivgeny", 14)));
        Persons persons = new Persons();
        persons.setList(listPersons);


        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Persons.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(persons, writer);  //сериализация
            String result = writer.toString();
//            System.out.println(result);

            StringReader reader = new StringReader(result);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Persons deserelialPersons = (Persons) unmarshaller.unmarshal(reader); //десериализация
//            System.out.println(deserelialPersons);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
