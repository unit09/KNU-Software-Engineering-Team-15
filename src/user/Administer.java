package user;

import java.io.Serializable;

import exchange.*;

public class Administer implements Serializable {
    private String name;
    private int number;

    public Administer(String name, int number){
        this.name = name;
        this.number = number;
    }

    public Recruitment createRecruitment(int num, String title, String contents, int year, String nation, String univ, String major){
        Recruitment newThing = new Recruitment(num, title, contents, year, nation, univ, major);
        return newThing;
    }

    public String getName() {
        return name;
    }
}
