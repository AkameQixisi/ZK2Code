/*
 * Created by JFormDesigner on Sun Jul 26 13:32:05 CST 2020
 */

package ui.tabs;

import element.SideTaskList;
import element.User;
import setting.S;
import ui.MyUI;
import ui.base.JProcessCellRender;
import ui.base.UpdateHelper;
import ui.dialog.ManagerConfirmUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Akame
 */
public class SideTaskTabUI extends JPanel implements MyUI {
    private static SideTaskTabUI sSideTaskTabUI;
    private SideTaskTabUI() {
        initComponents();
        initSettings();
        firstDisplay();
        updateAllUI();
    }

    private boolean mOnlyShowIncompleteTask = false;

    private void setOnlyShowIncompleteTask(boolean onlyShowIncompleteTask) {
        if (onlyShowIncompleteTask == mOnlyShowIncompleteTask) return;
        mOnlyShowIncompleteTask = onlyShowIncompleteTask;
        updateSideTaskListUI();
    }

    public void updateSideTaskListUI() {
        SideTaskList sideTaskList = SideTaskList.get();
        jList.setListData(sideTaskList.getAllSideTasks(mOnlyShowIncompleteTask));
    }

    public static SideTaskTabUI get() {
        if (sSideTaskTabUI == null) {
            sSideTaskTabUI = new SideTaskTabUI();
        }
        return sSideTaskTabUI;
    }

    private void jButtonMouseClicked(MouseEvent e) {
        // 点击完成按钮，选择的任务进度加1，如果未选中或者事件以完成，则无反应
        boolean ok = ManagerConfirmUI.confirm(this);
        if (!ok) return;
        int selectedIndex = jList.getSelectedIndex();
        if (selectedIndex == -1) return;
        SideTaskList sideTaskList = SideTaskList.get();
        float point = sideTaskList.accomplish(selectedIndex, mOnlyShowIncompleteTask);
        User user =User.get();
        user.setPoint(user.getPoint() + point);
        UpdateHelper.updateAllPointUI();    // 更新所有显示Point的UI
        if (point != -1) {
            updateSideTaskListUI();
        }
    }

    private void jCheckBoxStateChanged(ChangeEvent e) {
        boolean isOnlyIncomplete = jCheckBox.isSelected();
        setOnlyShowIncompleteTask(isOnlyIncomplete);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jScrollPane = new JScrollPane();
        jList = new JList();
        jCheckBox = new JCheckBox();
        jButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 0.0, 1.0E-4};

        //======== jScrollPane ========
        {
            jScrollPane.setViewportView(jList);
        }
        add(jScrollPane, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

        //---- jCheckBox ----
        jCheckBox.setText("\u9690\u85cf\u5df2\u5b8c\u6210");
        jCheckBox.addChangeListener(e -> jCheckBoxStateChanged(e));
        add(jCheckBox, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        //---- jButton ----
        jButton.setText("\u5b8c\u6210");
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButtonMouseClicked(e);
            }
        });
        add(jButton, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane jScrollPane;
    private JList jList;
    private JCheckBox jCheckBox;
    private JButton jButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        setFont(S.Fo.COM_FONT);
        jButton.setFont(S.Fo.COM_FONT);
        jList.setFont(S.Fo.COM_FONT_BOLD);
        jCheckBox.setFont(S.Fo.COM_FONT);
    }

    @Override
    public void firstDisplay() {
        jList.setCellRenderer(new JProcessCellRender());
    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {
        updateSideTaskListUI();
    }
}
