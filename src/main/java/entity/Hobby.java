package entity;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"hobby_name","complexity"})
public class Hobby {
    private String hobby_name;
    private int complexity;

    public Hobby(){}

    public Hobby(String hobby_name, int complexity) {
        this.hobby_name = hobby_name;
        this.complexity = complexity;
    }

    public String getHobby_name() {
        return hobby_name;
    }

    public void setHobby_name(String hobby_name) {
        this.hobby_name = hobby_name;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "hobby_name='" + hobby_name + '\'' +
                ", complexity=" + complexity +
                '}';
    }
}
