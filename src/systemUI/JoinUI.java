package systemUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import client.Client;

import javax.swing.JFrame;
import javax.swing.JLabel;

import user.*;

public class JoinUI extends JFrame {
    private JPanel contentPane;
    private JTextField pwdPassword;
    private JTextField IDField;

    private Client client;
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int xx,xy;
    private JPasswordField passwordField;
    private JTextField name_Field;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    /**
     * @wbp.nonvisual location=-29,-36
     */

    public JoinUI() {
    	client = new Client();
    	
    	setTitle("Login");
    	setBackground(Color.WHITE);
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((screenSize.width-556)/2, (screenSize.height-471)/2, 395, 761); //100 100 302 205 처음 나오는 UI의 위치와 크기
        contentPane = new JPanel();
        contentPane.setForeground(Color.WHITE);
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblMemberLogin = new JLabel("\uD68C\uC6D0\uAC00\uC785");
        lblMemberLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblMemberLogin.setFont(new Font("나눔스퀘어 ExtraBold", Font.BOLD, 18));
        lblMemberLogin.setBounds(114, 12, 144, 42);
        contentPane.add(lblMemberLogin);

        pwdPassword = new JPasswordField();
        pwdPassword.setFont(new Font("굴림", Font.PLAIN, 16));
        pwdPassword.setBounds(42, 155, 211, 42);
        contentPane.add(pwdPassword);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("굴림", Font.PLAIN, 16));
        passwordField.setBounds(43, 220, 211, 42);
        contentPane.add(passwordField);

        IDField = new JTextField();
        IDField.setFont(new Font("굴림", Font.PLAIN, 16));
        IDField.setBounds(42, 84, 211, 42);
        contentPane.add(IDField); 
        IDField.setColumns(10);

        JButton id_check_button = new JButton("\uC911\uBCF5\uD655\uC778");
        id_check_button.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
        id_check_button.setBackground(new Color(135, 206, 250));
        id_check_button.setBounds(261, 85, 96, 35); //195 49 79 23
        contentPane.add(id_check_button);

        id_check_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = IDField.getText();
                String pw = pwdPassword.getText();

                if(id.length() == 0)
                    JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "caution", JOptionPane.DEFAULT_OPTION);
                else if(pw.length() == 0)
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "caution", JOptionPane.DEFAULT_OPTION);
                else{
                	Student user = (Student)client.getObject(id, pw);
                	if(user == null)
                		JOptionPane.showMessageDialog(null, "ID 혹은 비밀번호가 잘못되었습니다.", "로그인 에러", JOptionPane.DEFAULT_OPTION);
                	else {
                		 dispose();	// 현재 창 닫기

                         UserInterface UI = new UserInterface(user, client);
                         UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                         UI.setBounds((screenSize.width-700)/2, (screenSize.height-550)/2, 700, 550);
                         UI.setVisible(true);
                         UI.setResizable(false);
                      
                	}
                }
            }
        });
        //enter action
        Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	//action 할 것!
            	id_check_button.doClick();
            } 
        };
        pwdPassword.addActionListener( action ); 
        IDField.addActionListener( action );
        
        JButton succ_button = new JButton("\uAC00\uC785\uD558\uAE30");	// 회원가입 버튼
        succ_button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// 회원가입 창 띄우기
        		client.setObject("s1123", "1123", new Student(2015123456, "김길동", "컴퓨터학부", 2, 2.14, "test1@naver.com", "010-1264-1231"));
        		client.setObject("s1132", "1132", new Student(2015111111, "홍길동", "컴퓨터학부", 2, 4.12, "test@naver.com", "010-1234-5678"));
        		client.setObject("a1124", "1124", new Student(-1, "관리자", "관리자용학생객체", 777, 0, "year가 관리자번호", "010-5667-8931"));
        		JOptionPane.showMessageDialog(null, "테스트용 아이디 생성 완료", "caution", JOptionPane.DEFAULT_OPTION);
        	}
        });
        succ_button.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
        succ_button.setBackground(new Color(135, 206, 250));
        succ_button.setBounds(107, 633, 144, 42);
        contentPane.add(succ_button);
        
        JLabel ID_label = new JLabel("\uC544\uC774\uB514");
        ID_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
        ID_label.setBounds(42, 68, 57, 15);
        contentPane.add(ID_label);
        
        JLabel PASSWD_label = new JLabel("\uBE44\uBC00\uBC88\uD638");
        PASSWD_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
        PASSWD_label.setBounds(42, 138, 86, 15);
        contentPane.add(PASSWD_label);
        
        JLabel lbl_close = new JLabel("X");
        lbl_close.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		System.exit(0);
        	}
        });
        lbl_close.setForeground(new Color(135, 206, 250));
        lbl_close.setBackground(new Color(30, 144, 255));
        lbl_close.setFont(new Font("나눔스퀘어 ExtraBold", Font.BOLD, 18));
        lbl_close.setBounds(521, 5, 13, 15);
        contentPane.add(lbl_close);
        
        name_Field = new JTextField();
        name_Field.setBounds(42, 284, 211, 42);
        contentPane.add(name_Field);
        name_Field.setColumns(10);
        
        JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC7AC\uD655\uC778");
        label.setFont(new Font("Dialog", Font.PLAIN, 12));
        label.setBounds(42, 205, 96, 15);
        contentPane.add(label);
        
        JLabel name_label = new JLabel("\uC774\uB984");
        name_label.setFont(new Font("Dialog", Font.PLAIN, 12));
        name_label.setBounds(42, 268, 86, 15);
        contentPane.add(name_label);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(42, 352, 211, 42);
        contentPane.add(textField);
        
        JLabel label_1 = new JLabel("\uD559\uBC88");
        label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
        label_1.setBounds(42, 335, 86, 15);
        contentPane.add(label_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(40, 423, 211, 42);
        contentPane.add(textField_1);
        
        JLabel label_2 = new JLabel("\uD559\uB144");
        label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
        label_2.setBounds(40, 406, 86, 15);
        contentPane.add(label_2);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(40, 494, 211, 42);
        contentPane.add(textField_2);
        
        JLabel label_3 = new JLabel("\uC774\uBA54\uC77C");
        label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
        label_3.setBounds(40, 477, 86, 15);
        contentPane.add(label_3);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(40, 565, 211, 42);
        contentPane.add(textField_3);
        
        JLabel label_4 = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
        label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
        label_4.setBounds(40, 548, 86, 15);
        contentPane.add(label_4);
    }
}
