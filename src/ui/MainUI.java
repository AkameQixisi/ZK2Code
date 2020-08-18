/*
 * Created by JFormDesigner on Sun Jul 26 13:12:34 CST 2020
 */

package ui;

import java.awt.event.*;
import setting.S;
import ui.base.MySystemTray;
import ui.base.UpdateHelper;
import ui.tabs.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akame
 */
public class MainUI extends JFrame implements MyUI {
    private static MainUI sMainUI;
    private MainUI() {
        initComponents();
        initSettings();
        initMySelf();
        addAllTabsUI();
    }

    private void initMySelf() {
        // 系统托盘
        mMySystemTray = new MySystemTray(this);
        mMySystemTray.setTrayListener();

        // 图标
        setIconImage(new ImageIcon(S.Fi.ICON_FILE).getImage());
    }

    private void addAllTabsUI() {
        jTabbedPane.add("主线任务", MainTaskTabUI.get());
        jTabbedPane.add("支线任务", SideTaskTabUI.get());
        jTabbedPane.add("点数商城", PointStoreUI.get());
        jTabbedPane.add("我的时间", TimeCounterUI.get());
        jTabbedPane.add("我的属性", UserInfoTabUI.get());
        jTabbedPane.add("管理员操作", ManagerUI.get());
        jTabbedPane.add("注意事项", ExplanationTabUI.get());
    }

    public static MainUI get() {
        if (sMainUI == null) {
            sMainUI = new MainUI();
        }
        return sMainUI;
    }

    private void jMenuItem_addComFontSizeActionPerformed(ActionEvent e) {
        // 增大普通字体
        S.addComSize(6);
        UpdateHelper.updateAllFontSize();
    }

    private void jMenuItem_minusComFontSizeActionPerformed(ActionEvent e) {
        // 减小普通字体
        if (S.Fo.COM_FONT_SIZE > 12) {
            S.addComSize(-6);
            UpdateHelper.updateAllFontSize();
        }
    }

    private void jMenuItem_addCounterFontSizeActionPerformed(ActionEvent e) {
        // 增大计时字体
        S.addCounterSize(36);
        UpdateHelper.updateAllFontSize();
    }

    private void jMenuItem_minusCounterFontSizeActionPerformed(ActionEvent e) {
        // 减小计时字体
        if (S.Fo.TIME_COUNTER_FONT_SIZE > 72) {
            S.addCounterSize(-36);
            UpdateHelper.updateAllFontSize();
        }
    }

    private MySystemTray mMySystemTray;

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jMenuBar = new JMenuBar();
        jMenu_Settings = new JMenu();
        jMenu_Fonts = new JMenu();
        jMenuItem_addComFontSize = new JMenuItem();
        jMenuItem_minusComFontSize = new JMenuItem();
        jMenuItem_addCounterFontSize = new JMenuItem();
        jMenuItem_minusCounterFontSize = new JMenuItem();
        jTabbedPane = new JTabbedPane();

        //======== this ========
        setTitle("\u70b9\u6570\u7cfb\u7edf");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(5, 5));

        //======== jMenuBar ========
        {

            //======== jMenu_Settings ========
            {
                jMenu_Settings.setText("\u8bbe\u7f6e");

                //======== jMenu_Fonts ========
                {
                    jMenu_Fonts.setText("\u5b57\u4f53");

                    //---- jMenuItem_addComFontSize ----
                    jMenuItem_addComFontSize.setText("\u589e\u5927\u666e\u901a\u5b57\u4f53");
                    jMenuItem_addComFontSize.addActionListener(e -> jMenuItem_addComFontSizeActionPerformed(e));
                    jMenu_Fonts.add(jMenuItem_addComFontSize);

                    //---- jMenuItem_minusComFontSize ----
                    jMenuItem_minusComFontSize.setText("\u51cf\u5c0f\u666e\u901a\u5b57\u4f53");
                    jMenuItem_minusComFontSize.addActionListener(e -> jMenuItem_minusComFontSizeActionPerformed(e));
                    jMenu_Fonts.add(jMenuItem_minusComFontSize);
                    jMenu_Fonts.addSeparator();

                    //---- jMenuItem_addCounterFontSize ----
                    jMenuItem_addCounterFontSize.setText("\u589e\u5927\u8ba1\u65f6\u5b57\u4f53");
                    jMenuItem_addCounterFontSize.addActionListener(e -> jMenuItem_addCounterFontSizeActionPerformed(e));
                    jMenu_Fonts.add(jMenuItem_addCounterFontSize);

                    //---- jMenuItem_minusCounterFontSize ----
                    jMenuItem_minusCounterFontSize.setText("\u51cf\u5c0f\u8ba1\u65f6\u5b57\u4f53");
                    jMenuItem_minusCounterFontSize.addActionListener(e -> jMenuItem_minusCounterFontSizeActionPerformed(e));
                    jMenu_Fonts.add(jMenuItem_minusCounterFontSize);
                }
                jMenu_Settings.add(jMenu_Fonts);
            }
            jMenuBar.add(jMenu_Settings);
        }
        setJMenuBar(jMenuBar);

        //======== jTabbedPane ========
        {
            jTabbedPane.setTabPlacement(SwingConstants.BOTTOM);
        }
        contentPane.add(jTabbedPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar jMenuBar;
    private JMenu jMenu_Settings;
    private JMenu jMenu_Fonts;
    private JMenuItem jMenuItem_addComFontSize;
    private JMenuItem jMenuItem_minusComFontSize;
    private JMenuItem jMenuItem_addCounterFontSize;
    private JMenuItem jMenuItem_minusCounterFontSize;
    private JTabbedPane jTabbedPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jTabbedPane.setFont(S.Fo.COM_FONT);
        jMenuBar.setFont(S.Fo.COM_FONT);
        jMenu_Settings.setFont(S.Fo.COM_FONT);
        jMenu_Fonts.setFont(S.Fo.COM_FONT);
        jMenuItem_addComFontSize.setFont(S.Fo.COM_FONT);
        jMenuItem_minusComFontSize.setFont(S.Fo.COM_FONT);
        jMenuItem_addCounterFontSize.setFont(S.Fo.COM_FONT);
        jMenuItem_minusCounterFontSize.setFont(S.Fo.COM_FONT);
    }

    @Override
    public void firstDisplay() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
    }



    @Override
    public void display() {
        mMySystemTray.removeTray();
        setSize(800, 500);
        setVisible(true);
    }

    @Override
    public void updateAllUI() {

    }

}
