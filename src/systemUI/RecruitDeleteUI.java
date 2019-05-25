package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exchange.RecruitmentList;
import user.Administer;

public class RecruitDeleteUI extends JPanel implements Observer{
    private JButton delet;
    private JList list;

    public RecruitDeleteUI(RecruitmentList mainList) {
    	list = new JList(mainList.printList2());  //삭제에 필요한 리스트
        list.setVisibleRowCount(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(20);
        list.setFixedCellWidth(300);
    	
        setLayout(new FlowLayout());
        setSize(500, 400);

        add(new JScrollPane(list));

        delet = new JButton("삭제");
        delet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index == -1)
                    JOptionPane.showMessageDialog(null, "삭제할 모집공고를 선택하세요.", "알림", JOptionPane.PLAIN_MESSAGE);
                else{
                    mainList.deleteList(index);
                    Observable.notifyObservers();
                    JOptionPane.showMessageDialog(null, "모집공고가 삭제되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        add(delet);
    }
    
    public void update(RecruitmentList mainList) {
    	list.setListData(mainList.printList2());
    }
}
