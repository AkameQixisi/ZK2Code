/*
 * Created by JFormDesigner on Mon Jul 27 10:35:41 CST 2020
 */

package ui.tabs;

import java.awt.event.*;

import element.User;
import setting.S;
import ui.MyUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Akame
 */
public class UserInfoTabUI extends JPanel implements MyUI {
    private static UserInfoTabUI sUserInfoTabUI;
    private UserInfoTabUI() {
        initComponents();
        initSettings();
        firstDisplay();
        updateAllUI();
    }
    public static UserInfoTabUI get() {
        if (sUserInfoTabUI == null) {
            sUserInfoTabUI = new UserInfoTabUI();
        }
        return sUserInfoTabUI;
    }

    public void updateUserPointUI() {
        jLabel_Point.setText(String.format("当前点数：%.1f", User.get().getPoint()));
    }

    private void updateStateTextUI() {
        if (jComboBox_Some.getSelectedIndex() != 0) {
            jLabel_State.setText((String) jComboBox_Some.getSelectedItem());
        } else {
            jLabel_State.setText("什么也没有...");
        }
    }

    private ImageIcon mImageIcon;

    private void jComboBox_SomeItemStateChanged(ItemEvent e) {
        // 更新当前状态显示
        updateStateTextUI();
    }

    private void jButtonMouseClicked(MouseEvent e) {
        // 更新一下图片
        int width = mImageIcon.getIconWidth();
        int height = mImageIcon.getIconHeight();
        width /= 2;
        height /= 2;
        if (width <= 50 || height <= 50) {
            width = height = 200;
        }
        mImageIcon.setImage(mImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        jLabel_Img.setIcon(null);
        jLabel_Img.setIcon(mImageIcon);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jLabel_Img = new JLabel();
        jLabel_Name = new JLabel();
        jLabel_Point = new JLabel();
        jLabel_State = new JLabel();
        jButton = new JButton();
        jComboBox_Some = new JComboBox();

        //======== this ========
        setLayout(new GridBagLayout());

        //---- jLabel_Img ----
        jLabel_Img.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_Img, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- jLabel_Name ----
        jLabel_Name.setText("\u59d3\u540d\uff1a%s");
        jLabel_Name.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_Name, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_Point ----
        jLabel_Point.setText("\u5df2\u6709\u70b9\u6570\uff1a%.1f");
        jLabel_Point.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_Point, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- jLabel_State ----
        jLabel_State.setText("\u5f53\u524d\u72b6\u6001\uff1a%s");
        jLabel_State.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_State, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- jButton ----
        jButton.setText("\u6ca1\u5565\u7528\u7684\u6309\u94ae");
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButtonMouseClicked(e);
            }
        });
        add(jButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 10), 0, 0));

        //---- jComboBox_Some ----
        jComboBox_Some.addItemListener(e -> jComboBox_SomeItemStateChanged(e));
        add(jComboBox_Some, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel jLabel_Img;
    private JLabel jLabel_Name;
    private JLabel jLabel_Point;
    private JLabel jLabel_State;
    private JButton jButton;
    private JComboBox jComboBox_Some;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jLabel_Img.setFont(S.Fo.COM_FONT);
        jLabel_Name.setFont(S.Fo.COM_FONT);
        jLabel_Point.setFont(S.Fo.COM_FONT);
        jLabel_State.setFont(S.Fo.COM_FONT);
        jButton.setFont(S.Fo.COM_FONT);
        jComboBox_Some.setFont(S.Fo.COM_FONT);
    }

    @Override
    public void firstDisplay() {
        jComboBox_Some.addItem("<技能（Skills）>");
        jComboBox_Some.addItem("写作业速度提升1.5倍！");
        jComboBox_Some.addItem("会飞！");
        jLabel_Name.setText("姓名："+ User.get().getName());
        mImageIcon = new ImageIcon(S.Fi.USER_IMG_FILE);
        mImageIcon.setImage(mImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        jLabel_Img.setIcon(mImageIcon);
    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {
        updateStateTextUI();
        updateUserPointUI();
    }

}
