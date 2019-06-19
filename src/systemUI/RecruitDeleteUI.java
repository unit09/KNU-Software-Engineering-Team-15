package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exchange.RecruitmentList;
import user.Administer;
import java.awt.Font;
import java.awt.Color;

public class RecruitDeleteUI extends JFrame implements Observer{
    private JButton delet;
    private JList list;

    public RecruitDeleteUI(RecruitmentList mainList) {
    	super("모집공고 삭제");
    	
    	list = new JList(mainList.printList2());  //삭제에 필요한 리스트
        list.setVisibleRowCount(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(20);
        list.setFixedCellWidth(300);
        setSize(800, 620);
        setLayout(null);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(71, 163, 662, 326);
        add(scrollPane);

        delet = new JButton("삭제");
        delet.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        delet.setBounds(355, 501, 98, 33);
        delet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index == -1)
                    JOptionPane.showMessageDialog(null, "삭제할 모집공고를 선택하세요.", "알림", JOptionPane.PLAIN_MESSAGE);
                else{
                	String[] buttons = {"삭제", "취소"};
    				int result = 0;
    				result = JOptionPane.showOptionDialog(null, "등록된 모집공고를 삭제하시겠습니까?", "모집공고 삭제", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
    				if(result == 0) {
	                    mainList.deleteList(index);
	                    Observable.uploadData();
	                    Observable.notifyObservers();
	                    JOptionPane.showMessageDialog(null, "모집공고가 삭제되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
    				}
                }
            }
        });

        add(delet);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(RecruitDeleteUI.class.getResource("/systemUI/image/board-crop.jpg")));
        label.setBounds(0, 0, 800, 620);
        add(label);
    }
    
    public void update(RecruitmentList mainList) {
    	list.setListData(mainList.printList2());
    }
}
