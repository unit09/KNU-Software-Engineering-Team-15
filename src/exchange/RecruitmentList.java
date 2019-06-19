package exchange;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class RecruitmentList implements Serializable {
    private ArrayList<Recruitment> recruitments = new ArrayList<Recruitment>();

    //생성자 자동 생성

    public void addList(Recruitment newThing){
        recruitments.add(newThing);
    }

    public void deleteList(int index){
        recruitments.remove(index);
    }

    public Vector<String> printList(){	// 모집공고 조회시 필요한 JList를 만들기 위해 vector를 return
        Vector<String> RList = new Vector<String>();

        for(int i = 0; i < recruitments.size(); i++){
            Recruitment temp = recruitments.get(i);
            RList.add("" + temp.getRecruitNum() + ". " + temp.getTitle());
        }

        return RList;
    }

    public Vector<String> printList2(){	// 모집공고 삭제시 필요한 JList를 만들기 위해 vertor를 return
        Vector<String> RList = new Vector<String>();

        for(int i = 0; i < recruitments.size(); i++){
            Recruitment temp = recruitments.get(i);
            RList.add("" + temp.getRecruitNum() + "번 모집공고 : " + temp.getTitle());
        }

        return RList;
    }
    
    public Vector<String> printState(int stID){
        Vector<String> RList = new Vector<String>();
        int index;
        for(int i = 0; i < recruitments.size(); i++){
            index = recruitments.get(i).checkUser(stID);
            if(index != -1){
                RList.addElement(recruitments.get(i).printState(index));
            }
        }
        return RList;
    }
    
    public JPanel printContents(int index){
        Recruitment temp = recruitments.get(index);
        JPanel temp2 = temp.printRecuritment();
        return temp2;
    }

    public boolean checkList(int num){	// 모집공고 번호가 num인 모집공고가 존재하는지 체크하는 메소드
        int i;
        for(i = 0; i < recruitments.size(); i ++){
            Recruitment check = recruitments.get(i);
            if(check.getRecruitNum() == num)
                break;
        }
        if(i == recruitments.size())
            return false;
        else
            return true;
    }

    public boolean checkUser(int index, int stID){  // index번째에 있는 모집공고에 stID인 학생이 신청을 했는지 안했는지 확인하는 메소드
        if(recruitments.get(index).checkUser(stID) != -1)
            return true;
        else
            return false;
    }

    public void apply(int index, Application newone){
        recruitments.get(index).addList(newone);
    }

    public void deleteAplication(int num, int stID){
        for(int i = 0; i < recruitments.size(); i++){
            if(num == recruitments.get(i).getRecruitNum()){
                int index = recruitments.get(i).checkUser(stID);
                recruitments.get(i).deleteList(index);
                break;
            }
        }
    }

    public int finalChoice(int num, int stID){	// 최종등록을 위한 메소드
    	int index = 0;
        for(int i = 0; i < recruitments.size(); i++) {
            if (num == recruitments.get(i).getRecruitNum()) {
                index = recruitments.get(i).checkUser(stID);
                if(recruitments.get(i).getProgress() == 2){
                    recruitments.get(i).finalChoice(index);
                }
                else
                    JOptionPane.showMessageDialog(null, "등록할 수 없는 상태입니다.", "알림", JOptionPane.PLAIN_MESSAGE);	// 이거 출력 자체는 UI에서 하도록 해야되는데...
                break;
            }
        }
        
        return index;
    }

    public void setRecruitState(int year, int month, int date) {	//시연을 위해 심사단계를 넘어가서 바로 합격처리를 함
    	for(int i = 0; i < recruitments.size(); i++) {
    		int t_y = recruitments.get(i).getDeadlineYear();
    		int t_m = recruitments.get(i).getDeadlineMonth();
    		int t_d = recruitments.get(i).getDeadlineDay();
    		if(t_y < year) {	
    			recruitments.get(i).setProgress(2);
    			recruitments.get(i).setPass();
    		}
    		else if(t_y == year){
    			if(month > t_m) {
    				recruitments.get(i).setProgress(2);
    				recruitments.get(i).setPass();
    			}
    			else if(month == t_m && date > t_d) {
    				recruitments.get(i).setProgress(2);
    				recruitments.get(i).setPass();
    			}
    		}
    		
    		t_y = recruitments.get(i).getSelectDeadlineYear();
    		t_m = recruitments.get(i).getSelectDeadlineMonth();
    		t_d = recruitments.get(i).getSelectDeadlineDay();
    		if(t_y < year) {
    			recruitments.get(i).setProgress(3);
    		}
    		else if(t_y == year){
    			if(month > t_m) {
    				recruitments.get(i).setProgress(3);
    			}
    			else if(month == t_m && date > t_d) {
    				recruitments.get(i).setProgress(3);
    			}
    		}
    		
    	}
    }

    //getter
    public int getRecruitNum(int index){
        return recruitments.get(index).getRecruitNum();
    }
    public int getRecruitState(int index) {
    	return recruitments.get(index).getProgress();
    }
    
    /*public Recruitment getRecruitment(int index) {
    	return recruitments.get(index);
    }*/
    
    public int getSemester(int index) {
        return recruitments.get(index).getStartSemester();
    }
    public int getYear(int index) {
        return recruitments.get(index).getStartYear();
    }
    public String getMajor(int index) {
        return recruitments.get(index).getMajor();
    }
    public String getNation(int index) {
        return recruitments.get(index).getNation();
    }
    public String getUniv(int index) {
        return recruitments.get(index).getUniversity();
    }
    public int getPeriod(int index) {
    	return recruitments.get(index).getPeriod();
    }
    
}
