package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import exchange.*;
import user.*;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecruitCreateUI2 extends JPanel {
    private JTextField titleField;
    private JTextField nationField;
    private JTextField univField;
    private JTextField startyearField;
    private JTextField textField;
    private JTextField deadYear;
    private JTextField deadMonth;
    private JTextField deadDay;
    private JTextField selectDay;
    private JTextField selectMonth;
    private JTextField selectYear;
    

    public RecruitCreateUI2(RecruitmentList mainList, Administer admin) {
        setSize(700, 530);
        setLayout(null);
        
        JLabel titleLabel = new JLabel("\uBAA8\uC9D1\uACF5\uACE0 \uC791\uC131");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("HY견고딕", Font.PLAIN, 24));
        titleLabel.setBounds(268, 12, 163, 39);
        add(titleLabel);
        
        JLabel title = new JLabel("\uC81C\uBAA9");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        title.setBounds(50, 69, 53, 32);
        add(title);
        
        titleField = new JTextField();
        titleField.setFont(new Font("굴림", Font.PLAIN, 18));
        titleField.setBounds(105, 72, 545, 32);
        add(titleField);
        titleField.setColumns(10);
        
        JLabel nationLabel = new JLabel("\uAD6D\uAC00");
        nationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nationLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        nationLabel.setBounds(50, 113, 53, 32);
        add(nationLabel);
        
        nationField = new JTextField();
        nationField.setFont(new Font("굴림", Font.PLAIN, 18));
        nationField.setColumns(10);
        nationField.setBounds(105, 116, 164, 32);
        add(nationField);
        
        JLabel univLabel = new JLabel("\uB300\uD559");
        univLabel.setHorizontalAlignment(SwingConstants.CENTER);
        univLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        univLabel.setBounds(283, 113, 53, 32);
        add(univLabel);
        
        univField = new JTextField();
        univField.setFont(new Font("굴림", Font.PLAIN, 18));
        univField.setColumns(10);
        univField.setBounds(340, 116, 310, 32);
        add(univField);
        
        JLabel startyearLabel = new JLabel("\uC2DC\uC791\uC5F0\uB3C4");
        startyearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startyearLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        startyearLabel.setBounds(50, 159, 80, 32);
        add(startyearLabel);
        
        startyearField = new JTextField();
        startyearField.setFont(new Font("굴림", Font.PLAIN, 18));
        startyearField.setHorizontalAlignment(SwingConstants.RIGHT);
        startyearField.setColumns(10);
        startyearField.setBounds(136, 162, 108, 32);
        add(startyearField);
        
        JLabel label_1 = new JLabel("\uB144");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_1.setBounds(245, 160, 30, 32);
        add(label_1);
        
        JLabel statrseLabel = new JLabel("\uC2DC\uC791\uD559\uAE30");
        statrseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statrseLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        statrseLabel.setBounds(283, 159, 80, 32);
        add(statrseLabel);
        
        JComboBox startSe = new JComboBox();
        startSe.setModel(new DefaultComboBoxModel(new String[] {"1\uD559\uAE30", "2\uD559\uAE30", "\uC5EC\uB984 \uACC4\uC808\uD559\uAE30", "\uACA8\uC6B8 \uACC4\uC808\uD559\uAE30"}));
        startSe.setFont(new Font("굴림", Font.PLAIN, 18));
        startSe.setToolTipText("");
        startSe.setBounds(367, 163, 178, 31);
        add(startSe);
        
        JLabel label = new JLabel("\uBD80\uD130 \uC2DC\uC791");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label.setBounds(546, 160, 80, 32);
        add(label);
        
        JLabel majorLabel = new JLabel("\uC804\uACF5");
        majorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        majorLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        majorLabel.setBounds(50, 203, 53, 32);
        add(majorLabel);
        
        textField = new JTextField();
        textField.setFont(new Font("굴림", Font.PLAIN, 18));
        textField.setColumns(10);
        textField.setBounds(105, 205, 231, 32);
        add(textField);
        
        JLabel periodLabel = new JLabel("\uAE30\uAC04");
        periodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        periodLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        periodLabel.setBounds(350, 203, 53, 32);
        add(periodLabel);
        
        JComboBox period = new JComboBox();
        period.setModel(new DefaultComboBoxModel(new String[] {"1\uD559\uAE30", "2\uD559\uAE30(1\uB144)", "3\uD559\uAE30", "4\uD559\uAE30(2\uB144) \uC774\uC0C1"}));
        period.setToolTipText("");
        period.setFont(new Font("굴림", Font.PLAIN, 18));
        period.setBounds(404, 206, 178, 31);
        add(period);
        
        JLabel label_3 = new JLabel("\uB3D9\uC548");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_3.setBounds(582, 205, 54, 32);
        add(label_3);
        
        JLabel deadLabel = new JLabel("\uBAA8\uC9D1\uAE30\uAC04");
        deadLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deadLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        deadLabel.setBounds(50, 249, 80, 32);
        add(deadLabel);
        
        deadYear = new JTextField();
        deadYear.setHorizontalAlignment(SwingConstants.RIGHT);
        deadYear.setFont(new Font("굴림", Font.PLAIN, 18));
        deadYear.setColumns(10);
        deadYear.setBounds(136, 249, 108, 32);
        add(deadYear);
        
        JLabel label_2 = new JLabel("\uB144");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_2.setBounds(245, 249, 30, 32);
        add(label_2);
        
        deadMonth = new JTextField();
        deadMonth.setHorizontalAlignment(SwingConstants.RIGHT);
        deadMonth.setFont(new Font("굴림", Font.PLAIN, 18));
        deadMonth.setColumns(10);
        deadMonth.setBounds(279, 249, 108, 32);
        add(deadMonth);
        
        JLabel label_4 = new JLabel("\uC6D4");
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_4.setBounds(390, 249, 30, 32);
        add(label_4);
        
        deadDay = new JTextField();
        deadDay.setHorizontalAlignment(SwingConstants.RIGHT);
        deadDay.setFont(new Font("굴림", Font.PLAIN, 18));
        deadDay.setColumns(10);
        deadDay.setBounds(425, 249, 108, 32);
        add(deadDay);
        
        JLabel label_5 = new JLabel("\uC77C \uAE4C\uC9C0");
        label_5.setHorizontalAlignment(SwingConstants.CENTER);
        label_5.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_5.setBounds(535, 249, 67, 32);
        add(label_5);
        
        JLabel selectLabel = new JLabel("\uB4F1\uB85D\uB9C8\uAC10");
        selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        selectLabel.setBounds(50, 293, 80, 32);
        add(selectLabel);
        
        selectYear = new JTextField();
        selectYear.setHorizontalAlignment(SwingConstants.RIGHT);
        selectYear.setFont(new Font("굴림", Font.PLAIN, 18));
        selectYear.setColumns(10);
        selectYear.setBounds(136, 293, 108, 32);
        add(selectYear);
        
        JLabel label_8 = new JLabel("\uB144");
        label_8.setHorizontalAlignment(SwingConstants.CENTER);
        label_8.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_8.setBounds(245, 293, 30, 32);
        add(label_8);
        
        selectMonth = new JTextField();
        selectMonth.setHorizontalAlignment(SwingConstants.RIGHT);
        selectMonth.setFont(new Font("굴림", Font.PLAIN, 18));
        selectMonth.setColumns(10);
        selectMonth.setBounds(279, 293, 108, 32);
        add(selectMonth);
        
        JLabel label_7 = new JLabel("\uC6D4");
        label_7.setHorizontalAlignment(SwingConstants.CENTER);
        label_7.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_7.setBounds(390, 293, 30, 32);
        add(label_7);
        
        selectDay = new JTextField();
        selectDay.setHorizontalAlignment(SwingConstants.RIGHT);
        selectDay.setFont(new Font("굴림", Font.PLAIN, 18));
        selectDay.setColumns(10);
        selectDay.setBounds(425, 293, 108, 32);
        add(selectDay);
        
        JLabel label_6 = new JLabel("\uC77C \uAE4C\uC9C0");
        label_6.setHorizontalAlignment(SwingConstants.CENTER);
        label_6.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        label_6.setBounds(535, 293, 67, 32);
        add(label_6);
        
        JLabel contentLabel = new JLabel("\uB0B4\uC6A9");
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
        contentLabel.setBounds(50, 337, 53, 32);
        add(contentLabel);
        
        JTextArea content = new JTextArea();
        content.setLineWrap(true);
        content.setBounds(105, 345, 545, 115);
        Border border = BorderFactory.createLineBorder(Color.GRAY); 
        content.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        add(content);
        
        JButton confirm = new JButton("\uC791\uC131 \uC644\uB8CC");
        confirm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		/*String[] buttons = {"등록", "취소"};
        		int result = 0;
        		result = JOptionPane.showOptionDialog(null, "해당 정보로 모집공고를 등록하시겠습니까??", "모집공고 등록", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
        		if(result == 0) {
        			if(!checkFormat(format[5].getText()))
        	           	JOptionPane.showMessageDialog(null, "시작 연도의 형식이 잘못되었습니다.\n정수를 입력해야합니다.", "오류!", JOptionPane.ERROR_MESSAGE);
        	        else if(!checkFormat(format[6].getText()))
        	           	JOptionPane.showMessageDialog(null, "시작 학기의 형식이 잘못되었습니다.\n정수를 입력해야합니다.", "오류!", JOptionPane.ERROR_MESSAGE);
        	        else if(!checkFormat(format[7].getText()))
        	            JOptionPane.showMessageDialog(null, "기간의 형식이 잘못되었습니다.\n정수를 입력해야합니다.", "오류!", JOptionPane.ERROR_MESSAGE);
        	        else if(!checkFormat(format[8].getText()))
        	            JOptionPane.showMessageDialog(null, "모집 마감 시기의 형식이 잘못되었습니다.\n정수를 입력해야합니다.", "오류!", JOptionPane.ERROR_MESSAGE);
        	        else if(!checkFormat(format[9].getText()))
        	            JOptionPane.showMessageDialog(null, "최종 등록 마감 시기의 형식이 잘못되었습니다.\n정수를 입력해야합니다.", "오류!", JOptionPane.ERROR_MESSAGE);
        	        else {
        	            Recruitment newone = admin.createRecruitment(Integer.parseInt(format[0].getText()), format[1].getText(), contents.getText(), Integer.parseInt(format[8].getText()),
        	                        Integer.parseInt(format[9].getText()), Integer.parseInt(format[5].getText()), Integer.parseInt(format[6].getText()), Integer.parseInt(format[7].getText()),
        	                        format[2].getText(), format[3].getText(), format[4].getText());
        	            mainList.addList(newone);
        	                    
        	            Observable.uploadData();
        	            Observable.notifyObservers();
        	                    
        	            JOptionPane.showMessageDialog(null, "모집공고 작성이 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);			                
        	        }
        		}*/
        	}
        });
        confirm.setBounds(290, 479, 120, 32);
        add(confirm);
        
        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon(RecruitCreateUI2.class.getResource("/systemUI/image/sky.jpg")));
        background.setBounds(0, 0, 700, 530);
        add(background);

    }
    
    private boolean checkFormat(String s) {
    	try {
    		Integer.parseInt(s);
    		return true;
    	}
    	catch(NumberFormatException e) {
    		return false;
    	}    	
    }
}

