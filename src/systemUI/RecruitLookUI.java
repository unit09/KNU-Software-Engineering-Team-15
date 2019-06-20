package systemUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exchange.*;
import user.*;
import java.awt.event.MouseMotionAdapter;

public class RecruitLookUI extends JFrame implements Observer {
    private JButton select;
    private JList list;
    private RecruitmentList mainList;

    public RecruitLookUI(int userType, RecruitmentList mains, Student user){
    	super("모집공고 조회");
        setSize(800, 620);
        setLayout(null);
        
        mainList = mains;
        
        list = new JList(mainList.printList());
        list.setBounds(107, 122, 614, 316);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        list.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    
                    if(index >= 0) {
                    	RecruitContent window = new RecruitContent(userType, mainList.getRecruitment(index), user, mainList);
                    	window.setResizable(false);
                    	window.setBounds(500, 200, 400, 730);
                        window.setVisible(true);
                    }
        		}
        	}
        });
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(96, 157, 616, 333);     
        add(scrollPane);
        
        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon(RecruitLookUI.class.getResource("/systemUI/image/board-crop.jpg")));
        background.setBounds(0, 0, 800, 620);
        add(background);
    }
    
    public void update(RecruitmentList mains) {
    	mainList = mains;
    	list.setListData(mainList.printList());
    }
}