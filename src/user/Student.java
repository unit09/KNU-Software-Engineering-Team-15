package user;

import java.io.Serializable;

import exchange.*;

public class Student implements Serializable {
    private int studentID;
    private String name;
    private String major;
    private int year;
    private double score;
    private String email;
    private String tel;

    public Student(int ID, String name, String major, int year, double score, String email, String tel) {
        studentID = ID;
        this.name = name;
        this.major = major;
        this.year = year;
        this.score = score;
        this.email = email;
        this.tel = tel;
    }

    public Application ApplicationCreate(int recruitNum) {
        Application newApply = new Application(recruitNum, this);
        return newApply;
    }

    // getter & setter
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getStudentID() {
        return studentID;
    }
    public String getName() {
        return name;
    }
    public String getMajor() {
        return major;
    }
    public double getScore() {
        return score;
    }
    public String getEmail() {
        return email;
    }
    public int getYear() {
        return year;
    }
    public String getTel() {
        return tel;
    }
}
