package systemUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import client.Client;

import javax.swing.JFrame;
import javax.swing.JLabel;

import user.*;

public class younghun extends JFrame {
    private JPanel contentPane;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private String stored;
    
    public younghun(Client client) {
    	
    	setTitle("È¸¿ø°¡ÀÔ");
    	setBackground(Color.WHITE);
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((screenSize.width-800)/2, (screenSize.height-620)/2, 800, 620); 
        contentPane = new JPanel();
        contentPane.setForeground(Color.WHITE);
        contentPane.setBackground(new Color(246, 245, 247));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblX = new JLabel("X");
        lblX.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		dispose();
        	}
        });
        lblX.setForeground(Color.BLACK);
        lblX.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 23));
        lblX.setBounds(763, 10, 19, 18);
        contentPane.add(lblX);
        
        JLabel lblNewLabel = new JLabel("\uB4F1\uB85D\uD559\uC810 \uC870\uD68C");
        lblNewLabel.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 20));
        lblNewLabel.setBounds(329, 12, 124, 52);
        contentPane.add(lblNewLabel);
        
        JButton button = new JButton("\uC870\uD68C");
        button.setBounds(620, 125, 105, 27);
        contentPane.add(button);
        
        JLabel label = new JLabel("\uB144\uB3C4/\uD559\uAE30");
        label.setIcon(null);
        label.setBackground(Color.WHITE);
        label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Light", Font.PLAIN, 18));
        label.setBounds(79, 120, 84, 34);
        contentPane.add(label);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(170, 126, 84, 24);
        contentPane.add(comboBox);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(79, 162, 646, 350);
        contentPane.add(tabbedPane);
    }
}
