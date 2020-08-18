/*
 * Created by JFormDesigner on Tue Jul 28 15:27:09 CST 2020
 */

package ui;

import java.awt.event.*;

import element.SideTask;
import element.SideTaskList;
import setting.S;
import ui.base.FloatOnlyDocument;
import ui.base.IntegerOnlyDocument;
import ui.tabs.SideTaskTabUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Akame
 */
public class ManagerTaskUI extends JFrame implements MyUI {
    public ManagerTaskUI() {
        initComponents();
        initSettings();
        initMySelf();
        updateAllUI();
    }

    private void initMySelf() {
        // 设置文本输入框只能接受小数
        jTextField_Point.setDocument(new FloatOnlyDocument()); // 小数
        jTextField_Times.setDocument(new IntegerOnlyDocument());     // 整数
    }

    private void jButton_OKMouseClicked(MouseEvent e) {
        // 从UI获得支线任务的信息，构造支线任务，更新之并存储之
        if (jComboBox_WhichTask.getSelectedIndex() != 1) {
            JOptionPane.showMessageDialog(this, "前选择正确的任务类型");
            return;
        }
        String description = jTextField_Description.getText();
        String pointStr = jTextField_Point.getText();
        String totalStr = jTextField_Times.getText();
        SideTask sideTask = new SideTask(new String[]{description, pointStr, "0", totalStr});
        SideTaskList.get().addSidTask(sideTask);
        JOptionPane.showMessageDialog(this, "添加完成");
        // 添加完，重置输入的内容
        jTextField_Description.setText("");
        jTextField_Point.setText("");
        jTextField_Times.setText("");

        // 更新支线任务的UI
        SideTaskTabUI.get().updateSideTaskListUI();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jLabel_WhichTask = new JLabel();
        jComboBox_WhichTask = new JComboBox();
        jLabel_Description = new JLabel();
        jTextField_Description = new JTextField();
        jLabel_Times = new JLabel();
        jTextField_Times = new JTextField();
        jLabel_Point = new JLabel();
        jTextField_Point = new JTextField();
        jButton_OK = new JButton();

        //======== this ========
        setTitle("\u4efb\u52a1\u7ba1\u7406");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        //---- jLabel_WhichTask ----
        jLabel_WhichTask.setText("\u6dfb\u52a0\u7684\u4efb\u52a1\u7c7b\u578b\uff1a");
        jLabel_WhichTask.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabel_WhichTask, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- jComboBox_WhichTask ----
        jComboBox_WhichTask.setSelectedIndex(-1);
        contentPane.add(jComboBox_WhichTask, new GridBagConstraints(1, 0, 1, 1, 3.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_Description ----
        jLabel_Description.setText("\u4efb\u52a1\u5185\u5bb9\uff1a");
        jLabel_Description.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabel_Description, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));
        contentPane.add(jTextField_Description, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_Times ----
        jLabel_Times.setText("\u4efb\u52a1\u603b\u6b21\u6570\uff1a");
        jLabel_Times.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabel_Times, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));
        contentPane.add(jTextField_Times, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_Point ----
        jLabel_Point.setText("\u6bcf\u6b21\u5b8c\u6210\u53ef\u83b7\u5f97\u70b9\u6570\uff1a");
        jLabel_Point.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabel_Point, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));
        contentPane.add(jTextField_Point, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jButton_OK ----
        jButton_OK.setText("\u786e\u5b9a\u53d1\u5e03");
        jButton_OK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton_OKMouseClicked(e);
            }
        });
        contentPane.add(jButton_OK, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel jLabel_WhichTask;
    private JComboBox jComboBox_WhichTask;
    private JLabel jLabel_Description;
    private JTextField jTextField_Description;
    private JLabel jLabel_Times;
    private JTextField jTextField_Times;
    private JLabel jLabel_Point;
    private JTextField jTextField_Point;
    private JButton jButton_OK;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jLabel_WhichTask.setFont(S.Fo.COM_FONT_BOLD);
        jLabel_Description.setFont(S.Fo.COM_FONT_BOLD);
        jLabel_Times.setFont(S.Fo.COM_FONT_BOLD);
        jLabel_Point.setFont(S.Fo.COM_FONT_BOLD);
        jComboBox_WhichTask.setFont(S.Fo.COM_FONT);
        jTextField_Description.setFont(S.Fo.COM_FONT);
        jTextField_Times.setFont(S.Fo.COM_FONT);
        jTextField_Point.setFont(S.Fo.COM_FONT);
        jButton_OK.setFont(S.Fo.COM_FONT);
    }

    @Override
    public void firstDisplay() {
        jComboBox_WhichTask.addItem("<请选择任务类型（Please select the kind of task）>");
        jComboBox_WhichTask.addItem("支线任务（Side task）");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {

    }
}
