package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exchange.RecruitmentList;
import user.Administer;

public class RecruitDeleteUI extends JPanel {
    private JButton delet;

    public RecruitDeleteUI(RecruitmentList sampleList, JList list) {
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
                    sampleList.deleteList(index);
                    //list.setListData(sampleList.printList());
                    list.setListData(sampleList.printList2());
                    JOptionPane.showMessageDialog(null, "모집공고가 삭제되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        add(delet);
    }
}
