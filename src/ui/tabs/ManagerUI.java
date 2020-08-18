/*
 * Created by JFormDesigner on Sun Jul 26 23:35:57 CST 2020
 */

package ui.tabs;

import java.awt.event.*;
import javax.swing.event.*;
import element.User;
import setting.S;
import ui.ManagerTaskUI;
import ui.MyUI;
import ui.base.UpdateHelper;
import ui.dialog.ManagerConfirmUI;

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;

/**
 * @author Akame
 */
public class ManagerUI extends JPanel implements MyUI {
    private static ManagerUI sManagerUI;
    private ManagerUI() {
        initComponents();
        initSettings();
        firstDisplay();
        updateAllUI();
    }
    public static ManagerUI get() {
        if (sManagerUI == null) {
            sManagerUI = new ManagerUI();
        }
        return sManagerUI;
    }

    private void updatePointHitUI() {
        float point = User.get().getPoint();
        point += jSlider_WhatPoint.getValue();
        String showText = String.format("修改为：%.1f", point);
        jLabel_ModefiyHit.setText(showText);
    }

    private void jSlider_WhatPointStateChanged(ChangeEvent e) {
        // 如果滑块的值发生变化，则修改相应的显示
        updatePointHitUI();
    }

    private void jButton_OKMouseClicked(MouseEvent e) {
        // 点击了确定，验证管理员身份，如果成功则保存修改的Point值
        boolean ok = ManagerConfirmUI.confirm(this);
        if (!ok) return;
        User user = User.get();
        float point = user.getPoint();
        point += jSlider_WhatPoint.getValue();
        user.setPoint(point);
        UpdateHelper.updateAllPointUI();
        jSlider_WhatPoint.setValue(0);  // 修改完成，将滑块置零
    }

    private void jButton_TaskManageMouseClicked(MouseEvent e) {
        // 验证管理员身份，成功后显示管理员的任务管理界面
        boolean ok = ManagerConfirmUI.confirm(this);
        if (!ok) return;
        new ManagerTaskUI().firstDisplay();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jLabel_UesrPoint = new JLabel();
        jLabel_ModefiyHit = new JLabel();
        jSlider_WhatPoint = new JSlider();
        jButton_OK = new JButton();
        jButton_TaskManage = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());

        //---- jLabel_UesrPoint ----
        jLabel_UesrPoint.setText("%s\u5f53\u524d\u7684\u70b9\u6570\u4e3a\uff1a%.1f");
        jLabel_UesrPoint.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_UesrPoint, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_ModefiyHit ----
        jLabel_ModefiyHit.setText("\u4fee\u6539\u4e3a\uff1a%.1f");
        jLabel_ModefiyHit.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_ModefiyHit, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jSlider_WhatPoint ----
        jSlider_WhatPoint.addChangeListener(e -> jSlider_WhatPointStateChanged(e));
        add(jSlider_WhatPoint, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jButton_OK ----
        jButton_OK.setText("\u786e\u5b9a");
        jButton_OK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton_OKMouseClicked(e);
            }
        });
        add(jButton_OK, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jButton_TaskManage ----
        jButton_TaskManage.setText("\u4efb\u52a1\u7ba1\u7406\uff08\u652f\u7ebf\u4efb\u52a1\u53d1\u5e03\uff09");
        jButton_TaskManage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton_TaskManageMouseClicked(e);
            }
        });
        add(jButton_TaskManage, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel jLabel_UesrPoint;
    private JLabel jLabel_ModefiyHit;
    private JSlider jSlider_WhatPoint;
    private JButton jButton_OK;
    private JButton jButton_TaskManage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jLabel_UesrPoint.setFont(S.Fo.COM_FONT_BOLD);
        jLabel_ModefiyHit.setFont(S.Fo.COM_FONT_BOLD);
        jButton_OK.setFont(S.Fo.COM_FONT);
        jButton_TaskManage.setFont(S.Fo.COM_FONT);
    }

    private void updateUserPointUI() {
        User user = User.get();
        jLabel_UesrPoint.setText(String.format("%s当前的点数为：%.1f", user.getName(), user.getPoint()));
    }

    @Override
    public void firstDisplay() {
        updateUserPointUI();
        jSlider_WhatPoint.setMaximum(5);
        jSlider_WhatPoint.setMinimum(-5);
        jSlider_WhatPoint.setValue(0);
        jSlider_WhatPoint.setPaintLabels(true);
        jSlider_WhatPoint.setPaintTicks(true);
        Hashtable hashtable = jSlider_WhatPoint.createStandardLabels(1, -5);
        jSlider_WhatPoint.setMajorTickSpacing(1);
        jSlider_WhatPoint.setLabelTable(hashtable);
    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {
        updateUserPointUI();
        updatePointHitUI();
    }
}
