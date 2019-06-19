package systemUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import client.Client;

import javax.swing.JFrame;
import javax.swing.JLabel;

import user.*;

public class LoginUI extends JFrame {
    private JPanel contentPane;
    private JTextField pwdPassword;
    private JTextField IDField;

    private Client client;
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int xx,xy;
    /**
     * @wbp.nonvisual location=-29,-36
     */

    public LoginUI() {
    	client = new Client();
    	
    	setTitle("Login");
    	setBackground(Color.WHITE);
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((screenSize.width-733)/2, (screenSize.height-501)/2, 556, 471); //100 100 302 205 처음 나오는 UI의 위치와 크기
        contentPane = new JPanel();
        contentPane.setForeground(Color.WHITE);
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblMemberLogin = new JLabel("Member Login");
        lblMemberLogin.setFont(new Font("나눔스퀘어 ExtraBold", Font.BOLD, 18));
        lblMemberLogin.setBounds(209, 49, 143, 42);
        contentPane.add(lblMemberLogin);

        pwdPassword = new JPasswordField();
        pwdPassword.setFont(new Font("굴림", Font.PLAIN, 16));
        pwdPassword.setBounds(177, 190, 211, 42);
        contentPane.add(pwdPassword);

        IDField = new JTextField();
        IDField.setFont(new Font("굴림", Font.PLAIN, 16));
        IDField.setBounds(177, 119, 211, 42);
        contentPane.add(IDField); 
        IDField.setColumns(10);

        JButton LoginButton = new JButton("로그인");
        LoginButton.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
        LoginButton.setBackground(new Color(135, 206, 250));
        LoginButton.setBounds(177, 252, 211, 42); //195 49 79 23
        contentPane.add(LoginButton);

        LoginButton.addActionListener(new ActionListener() {
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
                         MainUI UI = new MainUI();//(user, client);
                         /*UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                         //UI.setBounds((screenSize.width-700)/2, (screenSize.height-550)/2, 700, 550);
                         UI.setVisible(true);
                         UI.setResizable(false);*/
                         //UI.setVisible(true);
                         UserInterface UIX = new UserInterface(user, client);
                         UIX.setVisible(true);
                         UIX.setBounds((screenSize.width-700)/2, (screenSize.height-550)/2, 1000, 700);
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
            	LoginButton.doClick();
            } 
        };
        pwdPassword.addActionListener( action ); 
        IDField.addActionListener( action );
       
        
        JButton button = new JButton("비회원 로그인");
        button.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
        button.setBackground(new Color(135, 206, 250));
        button.setBounds(177, 305, 211, 42);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //종료하는것
 
                MainUI UI = new MainUI();//(new Student(-2,"guest", " ", 0, 0, " ", " "), client);	// 비회원 로그인 관련
                /*UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //UI.setBounds((screenSize.width-700)/2, (screenSize.height-550)/2, 700, 550);
                UI.setVisible(true);
                UI.setResizable(false);*/
                UI.setVisible(true);
            } 
        });
        contentPane.add(button);
        
        JButton button_join = new JButton("\uD68C\uC6D0\uAC00\uC785");	// 회원가입 버튼
        button_join.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		// 회원가입 창 띄우기
        		JoinUI join = new JoinUI(client);
                join.setVisible(true);
                join.setResizable(false);
        	}
        });
        button_join.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
        button_join.setBackground(new Color(135, 206, 250));
        button_join.setBounds(177, 359, 211, 42);
        contentPane.add(button_join);
        
        JLabel ID_label = new JLabel("ID");
        ID_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
        ID_label.setBounds(177, 101, 57, 15);
        contentPane.add(ID_label);
        
        JLabel PASSWD_label = new JLabel("PASSWORD");
        PASSWD_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 12));
        PASSWD_label.setBounds(177, 171, 86, 15);
        contentPane.add(PASSWD_label);
        
        JLabel lbl_close = new JLabel("X");
        lbl_close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        
        JLabel label = new JLabel("");
        label.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		xx = e.getX();
	        	xy = e.getY();
        	}
        });
        label.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseDragged(MouseEvent arg0) {
        		int x = arg0.getXOnScreen();
        		int y = arg0.getYOnScreen();
        		LoginUI.this.setLocation(x - xx, y - xy);  
        	}
        });
        label.setIcon(new ImageIcon(LoginUI.class.getResource("/systemUI/image/login.png")));
        label.setBounds(-9, -51, 590, 569);
        contentPane.add(label);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginUI frame = new LoginUI();
                    frame.setUndecorated(true);
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

