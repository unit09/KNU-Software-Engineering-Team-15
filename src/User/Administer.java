package User;

import Exchange.*;
import java.io.Serializable;

public class Administer implements Serializable {
    private String name;
    private int number;

    public Administer(String name, int number){
        this.name = name;
        this.number = number;
    }

    public Recruitment createRecruitment(int num, String title, String contents, int deadline, int sel, int year, int seme, int period, String nation,
                                         String univ, String major){
        Recruitment newThing = new Recruitment(num, title, contents, deadline, sel, year, seme, period, nation, univ, major);
        return newThing;
    }

    public String getName() {
        return name;
    }
}
