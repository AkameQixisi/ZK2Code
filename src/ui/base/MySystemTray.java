package ui.base;

import element.SaveHelper;
import setting.S;
import ui.MainUI;
import ui.dialog.ManagerConfirmUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MySystemTray {

    private MainUI mMainUI;
    private SystemTray mSystemTray;
    private TrayIcon mTrayIcon;

    public MySystemTray(MainUI mainUI) {
        mMainUI = mainUI;
    }

    public void setTrayListener() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // 判断系统是否支持托盘程序
        if (SystemTray.isSupported()) {
            // 设置托盘图标的右键菜单
            PopupMenu popupMenu = new PopupMenu();
            MenuItem menuItem = new MenuItem("关闭程序");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 验证管理员身份，确实是否关闭，保存当前状态
                    boolean ok = ManagerConfirmUI.confirm(mMainUI);
                    if (!ok) return;
                    SaveHelper.saveAllElement();
                    System.exit(0); // 程序关闭
                }
            });
            popupMenu.add(menuItem);

            mSystemTray = SystemTray.getSystemTray();   // 获得系统托盘和图标
            ImageIcon icon = new ImageIcon(S.Fi.ICON_FILE);
            mTrayIcon = new TrayIcon(icon.getImage(), "点数系统", popupMenu);  // 设置托盘图标
            mTrayIcon.setImageAutoSize(true);   // 托盘图标自适应大小（必须，否则可能不显示图标）

            mTrayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    // 监听托盘图标的鼠标事件，按下左键弹出主窗口
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        mSystemTray.remove(mTrayIcon);          // 移除系统托盘
                        mMainUI.setVisible(true);               // 显示主窗口
                        // 设置窗口状态，如最大化或最小化等，这里使用正常
                        mMainUI.setExtendedState(JFrame.NORMAL);
                    }
                }
            });
        }


        // 注册关闭监听器，主窗口关闭时添加到托盘
        mMainUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    // 保存当前状态
                    SaveHelper.saveAllElement();
                    mSystemTray.add(mTrayIcon);        // 添加托盘图标
                    mMainUI.setVisible(false);          // 隐藏主窗口
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void removeTray() {
        mSystemTray.remove(mTrayIcon);
    }
}