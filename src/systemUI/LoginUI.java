package systemUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;

import user.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginUI extends JFrame {
    private JPanel contentPane;
    private JTextField pwdPassword;
    private JTextField IDField;

    private File login = new File("database/login/login DB.txt");
    private File userD = new File("database/login/User DB.txt");
    private ArrayList<String> IDList = new ArrayList<>();
    private ArrayList<String> password = new ArrayList<>();
    private ArrayList<Student> users;
    private Scanner scan;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * @wbp.nonvisual location=-29,-36
     */
    private final JPanel panel = new JPanel();

    public LoginUI() {
    	setTitle("Login");
    	setBackground(Color.WHITE);
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((screenSize.width-302)/2, (screenSize.height-205)/2, 302, 205); //100 100 302 205 처음 나오는 UI의 위치와 크기
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(120, 10, 57, 15);
        contentPane.add(lblLogin);

        pwdPassword = new JPasswordField();
        pwdPassword.setBounds(40, 66, 211, 21);
        contentPane.add(pwdPassword);

        IDField = new JTextField();
        IDField.setBounds(40, 35, 211, 21);
        contentPane.add(IDField); 
        IDField.setColumns(10);

        JButton LoginButton = new JButton("로그인");
        LoginButton.setBounds(40, 111, 79, 23); //195 49 79 23
        contentPane.add(LoginButton);

        try {
            scan = new Scanner(login);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            FileInputStream fs = new FileInputStream(userD);
            ObjectInputStream os = new ObjectInputStream(fs);
            try {
                users = (ArrayList<Student>)os.readObject();
            } catch (Exception e) { 
                System.out.println("read");
            }
        } catch (Exception e) {
            System.out.println("stream");
        }

        while(scan.hasNext()) {
            IDList.add(scan.next());
            password.add(scan.next());
            String buf = scan.next();
        }
        
       
       

       

        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = IDField.getText();
                String pw = pwdPassword.getText();

                if(id.length() == 0)
                    JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "caution", JOptionPane.DEFAULT_OPTION);
                else if(pw.length() == 0)
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "caution", JOptionPane.DEFAULT_OPTION);
                else{
                    int j;
                    for(j = 0; j < IDList.size(); j++){
                        if(id.equals(IDList.get(j)) && pw.equals(password.get(j))){
                            dispose();

                            UserInterface UI = new UserInterface(users.get(j));
                            UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            UI.setBounds((screenSize.width-700)/2, (screenSize.height-550)/2, 700, 550);
                            UI.setVisible(true);
                            UI.setResizable(false);
                            break;
                        }
                    }
                    if(j == IDList.size())
                        JOptionPane.showMessageDialog(null, "ID 혹은 비밀번호가 잘못되었습니다.", "로그인 에러", JOptionPane.DEFAULT_OPTION);
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
        button.setBounds(125, 111, 126, 23);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //종료하는것
 
                UserInterface UI = new UserInterface(new Student(-2,"guest", " ", 0, 0, " ", " "));
                UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                UI.setBounds((screenSize.width-700)/2, (screenSize.height-550)/2, 700, 550);
                UI.setVisible(true);
                UI.setResizable(false);
            } 
        });
        contentPane.add(button);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginUI frame = new LoginUI();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

