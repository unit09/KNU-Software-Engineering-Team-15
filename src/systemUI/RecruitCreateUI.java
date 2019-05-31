package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exchange.*;
import user.*;

public class RecruitCreateUI extends JPanel {
    private JTextField[] format = new JTextField[10];
    private JTextArea contents = new JTextArea();
    private JButton confirm;

    public RecruitCreateUI(RecruitmentList mainList, Administer admin) {
        setLayout(new FlowLayout());
        setSize(500, 400);

        format[0] = new JTextField("Number of Recruitment (ex. 1)", 44);
        format[1] = new JTextField("Title", 44);
        format[2] = new JTextField("Nation", 44);
        format[3] = new JTextField("University", 44);
        format[4] = new JTextField("Major", 44);
        format[5] = new JTextField("Start Year", 44);
        format[6] = new JTextField("Start Semester", 44);
        format[7] = new JTextField("Period", 44);
        format[8] = new JTextField("Deadline (ex. 20180908)", 44);
        format[9] = new JTextField("Resisteration Deadline (ex. 20181010)", 44);

        contents = new JTextArea("Contents");
        contents.setColumns(44);
        contents.setRows(8);

        confirm = new JButton("작성완료");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String[] buttons = {"등록", "취소"};
				int result = 0;
				result = JOptionPane.showOptionDialog(null, "해당 정보로 모집공고를 등록하시겠습니까??", "모집공고 등록", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
				if(result == 0) {
	                if (mainList.checkList(Integer.parseInt(format[0].getText())) == false) {
	                    Recruitment newone = admin.createRecruitment(Integer.parseInt(format[0].getText()), format[1].getText(), contents.getText(), Integer.parseInt(format[8].getText()),
	                            Integer.parseInt(format[9].getText()), Integer.parseInt(format[5].getText()), Integer.parseInt(format[6].getText()), Integer.parseInt(format[7].getText()),
	                            format[2].getText(), format[3].getText(), format[4].getText());
	                    mainList.addList(newone);
	                    
	                    Observable.uploadData();
	                    Observable.notifyObservers();
	                    
	                    JOptionPane.showMessageDialog(null, "모집공고 작성이 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
	                }
                } else
                    JOptionPane.showMessageDialog(null, "같은 번호의 모집공고가 이미 존재합니다.", "알림", JOptionPane.PLAIN_MESSAGE);
            }
        });

        for (int i = 0; i < 10; i++) {
            add(format[i]);
        }

        add(contents);
        add(confirm);
    }
    
}
