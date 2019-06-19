package systemUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exchange.*;
import user.*;

public class RecruitLookUI extends JFrame implements Observer {
    private JButton select;
    private JList list;

    public RecruitLookUI(int userType, RecruitmentList mainList, Student user){
    	super("모집공고 조회");
        setSize(800, 620);
        setLayout(null);
        
        list = new JList(mainList.printList());
        list.setBounds(107, 122, 614, 316);
        //list.setVisibleRowCount(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = list.getSelectedIndex();
                
                if(index >= 0) {
                	
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(96, 157, 616, 333);
        
        add(scrollPane);

        //scrollPane.setVisible(false);
       
        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon(RecruitLookUI.class.getResource("/systemUI/image/board-crop.jpg")));
        background.setBounds(0, 0, 800, 620);
        add(background);
    }
    
    public void update(RecruitmentList mianList) {
    	
    }
}