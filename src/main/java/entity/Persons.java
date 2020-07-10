package entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persons")
public class Persons {

    @XmlElement(name = "person")
    private List<Person> listPerson = null;

    public List<Person> getList() {
        return listPerson;
    }

    public void setList(List<Person> list) {
        this.listPerson = list;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "listPerson=" + listPerson +
                '}';
    }
}
