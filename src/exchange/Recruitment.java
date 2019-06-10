package exchange;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Recruitment implements Serializable {
    private int recruitNum;
    private String title;
    private String contents;
    private int deadline;
    private int selectDeadline;
    private int startYear;
    private int startSemester;	// 3: 여름 계절학기, 4: 겨울 계절학기
    private int period;
    private String nation;
    private String university;
    private String major;

    private int progress = 0;   //0:모집중, 1:심사중, 2:심사종료, 3:다끝남

    private ArrayList<Application> applications = new ArrayList<Application>();

    public Recruitment(int recruitNum, String title, String contents, int deadline, int selectDeadline, int startYear, int startSemester, int period,
                       String nation, String university,String major){
        this.recruitNum = recruitNum;
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
        this.selectDeadline = selectDeadline;
        this.startYear = startYear;
        this.startSemester = startSemester;
        this.period = period;
        this.nation = nation;
        this.university = university;
        this.major = major;
    }

    public void addList(Application apply){
        applications.add(apply);
    }

    public int checkUser(int stID){
        int i;
        for(i = 0; i < applications.size(); i++){
            Application ch = applications.get(i);
            if(ch.getStudentID() == stID)
                break;
        }
        if(i == applications.size())
            return -1;
        else
            return i;
    }

    public void deleteList(int index){ ;
        applications.remove(index);
    }

    public JPanel printRecuritment(){
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JTextField num = new JTextField("모집공고 번호 : " + recruitNum, 32);
        JTextField ti = new JTextField("제목 : " + title, 32);
        JTextField na = new JTextField("국가/대학/전공 : " + nation + "/" + university + "/" + major, 32);
        JTextField st = new JTextField("시작 : " + startYear + "년 " + startSemester + "학기부터 시작", 32);
        JTextField pe = new JTextField("기간 : " + period + "학기동안", 32);
        JTextArea con = new JTextArea(contents, 12, 32);
        JTextField de = new JTextField("모집기간 : " + deadline + "까지", 32);
        JTextField sde = new JTextField("최종등록 마감일 : " + selectDeadline + "까지", 32);

        num.setEditable(false);
        ti.setEditable(false);
        na.setEditable(false);
        st.setEditable(false);
        pe.setEditable(false);
        con.setEnabled(false);
        de.setEditable(false);
        sde.setEditable(false);

        pane.add(num);
        pane.add(ti);
        pane.add(na);
        pane.add(st);
        pane.add(pe);
        pane.add(con);
        pane.add(de);
        pane.add(sde);

        return pane;
    }
    
    public void finalChoice(int index){
        if(applications.get(index).isChoice() == true){
            JOptionPane.showMessageDialog(null, "이미 등록되어있습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
        }
        else if(applications.get(index).isPass() == true){
            applications.get(index).setChoice(true);
            JOptionPane.showMessageDialog(null, "등록되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "등록할 수 있는 상태가 아닙니다.", "알림", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public String printState(int index){
    	if(progress == 0)
            return recruitNum + ". " + title + " : 모집중";
        else if(progress == 1)
            return recruitNum + ". " + title + " : 심사중";
        else if(progress == 2){
            if(applications.get(index).isPass() == true) {
                if(applications.get(index).isChoice() == true)
                    return recruitNum + ". " + title + " : 최종 등록 완료";
                else
                    return recruitNum + ". " + title + " : 합격";
            }
            else
                return recruitNum + ". " + title + " : 불합격";
        }
        else{
            if(applications.get(index).isChoice() == true)
                return recruitNum + ". " + title + " : 마감 - 최종 등록 완료";
            else
                return recruitNum + ". " + title + " : 마감";
        }
    }
    
    public void setPass() {	//원래는 외국대학에서 응시원서를 받아서 보고 처리하지만 여기서는 학점이 4.0 이상이면 자동 합격하도록 설정
    	for(int i = 0; i < applications.size(); i++) {
    		if(applications.get(i).getScore() >= 4.0)
    			applications.get(i).setPass(true);
    	}
    }

    //getter & setter
    public int getRecruitNum() {
        return recruitNum;
    }
    public String getTitle() {
        return title;
    }
    public int getDeadline() {
        return deadline;
    }
    public int getPeriod() {
        return period;
    }
    public String getMajor() {
        return major;
    }
    public int getProgress() {
        return progress;
    }
    public int getSelectDeadline() {
        return selectDeadline;
    }
    public int getStartSemester() {
        return startSemester;
    }
    public int getStartYear() {
        return startYear;
    }
    public String getContents() {
        return contents;
    }
    public String getNation() {
        return nation;
    }
    public String getUniversity() {
        return university;
    }

    public void setRecruitNum(int recruitNum) {
        this.recruitNum = recruitNum;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public void setPeriod(int period) {
        this.period = period;
    }
    public void setProgress(int progress) {
        this.progress = progress;
    }
    public void setSelectDeadline(int selectDeadline) {
        this.selectDeadline = selectDeadline;
    }
    public void setStartSemester(int startSemester) {
        this.startSemester = startSemester;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
}
