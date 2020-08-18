/*
 * Created by JFormDesigner on Sun Jul 26 21:24:49 CST 2020
 */

package ui.tabs;

import java.awt.event.*;
import javax.swing.event.*;
import element.User;
import setting.S;
import ui.MyUI;
import ui.base.UpdateHelper;

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;

/**
 * @author Akame
 */
public class PointStoreUI extends JPanel implements MyUI {
    private static PointStoreUI sPointStoreUI;
    private PointStoreUI() {
        initComponents();
        initSettings();
        updateAllUI();
    }
    public static PointStoreUI get() {
        if (sPointStoreUI == null) {
            sPointStoreUI = new PointStoreUI();
        }
        return sPointStoreUI;
    }

    public void updatePointAndSliderUI() {
        float point = User.get().getPoint();
        jLabel_PointText.setText(String.format("已有点数：%.1f（可购买%d小时电脑使用时间）", point, (int)point));

        jSlider_WhatHours.setMaximum((int) point);
        jSlider_WhatHours.setMinimum(0);
        jSlider_WhatHours.setValue(0);
        jSlider_WhatHours.setPaintLabels(true);
        jSlider_WhatHours.setPaintTicks(true);
        Hashtable hashtable;
        if (point > 10) {
            hashtable = jSlider_WhatHours.createStandardLabels(5);
        } else {
            hashtable = jSlider_WhatHours.createStandardLabels(1);
        }
        jSlider_WhatHours.setMajorTickSpacing(1);
        jSlider_WhatHours.setLabelTable(hashtable);
    }

    private void jButton_BuyMouseClicked(MouseEvent e) {
        // 购买一定的时间，修改相应的User，以及更新UI
        int hours = jSlider_WhatHours.getValue();
        if (hours == 0) return;
        User user = User.get();
        user.setPoint(user.getPoint() - hours);
        UpdateHelper.updateAllPointUI();    // 更新显示所有Point的UI
        UpdateHelper.addRemainingSecondAndUpdateUI(hours * 3600);
        updatePointAndSliderUI();
    }

    private void updateBuyHit() {
        String text = String.format("购买%d小时", jSlider_WhatHours.getValue());
        jLabel_BuyHit.setText(text);
    }

    private void jSlider_WhatHoursStateChanged(ChangeEvent e) {
        // 更新购买提示
        updateBuyHit();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jLabel_PointText = new JLabel();
        jLabel_BuyHit = new JLabel();
        jSlider_WhatHours = new JSlider();
        jButton_Buy = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());

        //---- jLabel_PointText ----
        jLabel_PointText.setText("\u5df2\u6709\u70b9\u6570\uff1a%.1f\uff08\u53ef\u8d2d\u4e70%d\u5c0f\u65f6\u7535\u8111\u4f7f\u7528\u65f6\u95f4\uff09");
        jLabel_PointText.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_PointText, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_BuyHit ----
        jLabel_BuyHit.setText("\u8d2d\u4e70%d\u5c0f\u65f6");
        jLabel_BuyHit.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_BuyHit, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jSlider_WhatHours ----
        jSlider_WhatHours.addChangeListener(e -> jSlider_WhatHoursStateChanged(e));
        add(jSlider_WhatHours, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jButton_Buy ----
        jButton_Buy.setText("\u8d2d\u4e70");
        jButton_Buy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton_BuyMouseClicked(e);
            }
        });
        add(jButton_Buy, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel jLabel_PointText;
    private JLabel jLabel_BuyHit;
    private JSlider jSlider_WhatHours;
    private JButton jButton_Buy;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jLabel_PointText.setFont(S.Fo.COM_FONT_BOLD);
        jLabel_BuyHit.setFont(S.Fo.COM_FONT_BOLD);
        jSlider_WhatHours.setFont(S.Fo.COM_FONT);
        jButton_Buy.setFont(S.Fo.COM_FONT);
    }

    @Override
    public void firstDisplay() {

    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {
        updatePointAndSliderUI();
        updateBuyHit();
    }
}
