package entity;


import entity.adapters.DateAdapter;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlType(propOrder = {"name","birthday", "hobbyes"})
public class Person {
    private String name;
    private Date birthday;
    private List<Hobby> hobbyes = null;

    public Person(){}

    public Person(String name, Date date) {
        this.name = name;
        this.birthday = date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", hobbyes=" + hobbyes +
                '}';
    }

    public void addHobby(String name, int complexity){
        if (hobbyes == null){
            hobbyes = new ArrayList<>();
            hobbyes.add(new Hobby(name, complexity));
        }else{
            hobbyes.add(new Hobby(name, complexity));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date date) {
        this.birthday = date;
    }

    public List<Hobby> getHobbyes() {
        return hobbyes;
    }

    public void setHobbyes(List<Hobby> hobbyes) {
        this.hobbyes = hobbyes;
    }
}
