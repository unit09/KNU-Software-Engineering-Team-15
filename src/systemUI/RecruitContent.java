package systemUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import exchange.*;
import user.*;

public class RecruitContent extends JFrame {
	
	private static final String FONT1 = "맑은 고딕 Semilight";
	
	public RecruitContent(int userType, Recruitment recruitment, Student user) {
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setSize(400, 700);
		
		JLabel title = new JLabel(recruitment.getTitle());
		title.setForeground(Color.WHITE);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		title.setBounds(14, 36, 372, 44);
		getContentPane().add(title);
		
		JTextArea textArea = new JTextArea(recruitment.getContents());
		textArea.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(null);
		Color temp = new Color(255, 255, 255, 0);
		textArea.setBackground(temp);
		textArea.setEditable(false);
		textArea.setBounds(103, 109, 265, 156);
		getContentPane().add(textArea);
		
		JLabel nation = new JLabel("\uBAA8\uC9D1\uAD6D\uAC00");
		nation.setForeground(Color.WHITE);
		nation.setFont(new Font(FONT1, Font.BOLD, 16));
		nation.setBounds(103, 295, 71, 35);
		getContentPane().add(nation);
		
		JLabel label1 = new JLabel(recruitment.getNation());
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font(FONT1, Font.BOLD, 16));
		label1.setBounds(204, 295, 182, 35);
		getContentPane().add(label1);
		
		JLabel univ = new JLabel("\uBAA8\uC9D1\uB300\uD559");
		univ.setForeground(Color.WHITE);
		univ.setFont(new Font(FONT1, Font.BOLD, 16));
		univ.setBounds(103, 348, 71, 35);
		getContentPane().add(univ);
		
		JLabel label2 = new JLabel(recruitment.getUniversity());
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font(FONT1, Font.BOLD, 16));
		label2.setBounds(204, 348, 182, 35);
		getContentPane().add(label2);
		
		JLabel major = new JLabel("\uC804\uACF5");
		major.setForeground(Color.WHITE);
		major.setFont(new Font(FONT1, Font.BOLD, 16));
		major.setBounds(103, 401, 71, 35);
		getContentPane().add(major);
		
		JLabel label3 = new JLabel(recruitment.getMajor());
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font(FONT1, Font.BOLD, 16));
		label3.setBounds(204, 401, 182, 35);
		getContentPane().add(label3);
		
		JLabel start = new JLabel("\uC2DC\uC791\uD559\uAE30");
		start.setForeground(Color.WHITE);
		start.setFont(new Font(FONT1, Font.BOLD, 16));
		start.setBounds(103, 453, 71, 35);
		getContentPane().add(start);
		
		JLabel label4 = new JLabel(recruitment.getStartYear() + "년 " + recruitment.getStartSemester() + "학기부터");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font(FONT1, Font.BOLD, 14));
		label4.setBounds(204, 453, 182, 35);
		getContentPane().add(label4);
		
		JLabel period = new JLabel("\uD30C\uACAC\uAE30\uAC04");
		period.setForeground(Color.WHITE);
		period.setFont(new Font(FONT1, Font.BOLD, 16));
		period.setBounds(103, 503, 71, 35);
		getContentPane().add(period);
		
		JLabel label5 = new JLabel(recruitment.getPeriod() + "학기 동안");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font(FONT1, Font.BOLD, 16));
		label5.setBounds(204, 503, 182, 35);
		getContentPane().add(label5);
		
		JLabel deadline = new JLabel("\uBAA8\uC9D1\uAE30\uAC04");
		deadline.setForeground(Color.WHITE);
		deadline.setFont(new Font(FONT1, Font.BOLD, 16));
		deadline.setBounds(103, 550, 71, 35);
		getContentPane().add(deadline);
		
		JLabel label6 = new JLabel(recruitment.getDeadlineYear() + "년 " + recruitment.getDeadlineMonth() + "월 " + recruitment.getDeadlineDay() + "일 까지");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font(FONT1, Font.BOLD, 14));
		label6.setBounds(204, 550, 182, 35);
		getContentPane().add(label6);
		
		JLabel select = new JLabel("\uB4F1\uB85D\uB9C8\uAC10");
		select.setForeground(Color.WHITE);
		select.setFont(new Font(FONT1, Font.BOLD, 16));
		select.setBounds(103, 597, 71, 35);
		getContentPane().add(select);
		
		JLabel label7 = new JLabel(recruitment.getSelectDeadlineYear() + "년 " + recruitment.getSelectDeadlineMonth() + "월 " + recruitment.getSelectDeadlineDay() + "일 까지");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font(FONT1, Font.BOLD, 14));
		label7.setBounds(204, 597, 182, 35);
		getContentPane().add(label7);
		
		if(userType == 0) {
			JButton apply = new JButton("\uC9C0\uC6D0\uD558\uAE30");
			apply.setBounds(281, 642, 105, 27);
			
			apply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	if(recruitment.getProgress() == 0) {
                		if (recruitment.checkUser(user.getStudentID()) == -1) {
                			String[] buttons = {"신청", "취소"};
            				int result = 0;
            				result = JOptionPane.showOptionDialog(null, "신청하시겠습니까?", "응시원서 작성", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
            				if(result == 0) {
                                Application newone = user.ApplicationCreate(recruitment.getRecruitNum());
                                recruitment.addList(newone);
                                Observable.uploadData();
                                Observable.notifyObservers();
                                
                                JOptionPane.showMessageDialog(null, "응시원서 작성이 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
            				}
                        } else
                            JOptionPane.showMessageDialog(null, "이미 응시한 모집공고입니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "신청할 수 없는 상태입니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                	}
                }
            });
			getContentPane().add(apply);
		}
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(RecruitContent.class.getResource("/systemUI/image/\uBAA8\uC9D1\uBC30\uACBD.png")));
		background.setBounds(0, 0, 400, 700);
		getContentPane().add(background);
		
		
	}
}
