package Exchange;

import User.*;
import java.io.Serializable;

public class Application implements Serializable {
    private Student applyStudent;
    private int recuritNum;
    private boolean pass = false;
    private boolean choice = false;
    //추가정보에 대해서는 구현하지 않음

    public Application(int recuritNum, Student applyStudent){
        this.recuritNum = recuritNum;
        this.applyStudent = applyStudent;
    }

    //getter & setter
    public int getRecuritNum() {
        return recuritNum;
    }
    public boolean isPass() {
        return pass;
    }
    public boolean isChoice() {
        return choice;
    }

    public void setRecuritNum(int recuritNum) {
        this.recuritNum = recuritNum;
    }
    public void setPass(boolean pass) {
        this.pass = pass;
    }
    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    public int getStudentID(){
        return this.applyStudent.getStudentID();
    }
    public double getScore() {
    	return this.applyStudent.getScore();
    }
}
