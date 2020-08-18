/*
 * Created by JFormDesigner on Mon Jul 27 08:41:00 CST 2020
 */

package ui.dialog;

import element.SaveHelper;
import element.User;
import setting.S;
import ui.MainUI;
import ui.MyUI;
import ui.base.UpdateHelper;
import ui.tabs.TimeCounterUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * @author Akame
 */
public class TimeoutUI extends JFrame implements MyUI {
    public TimeoutUI() {
        initComponents();
        initSettings();
    }

    private int mRemainingSecond = 180;     // 3分钟

    public void addSecondAndUpdateUI(int second) {
        if (second == 0) return;
        mRemainingSecond += second;
        checkHasRemainingTime();    // 检查自动计时结束
        updateRemainingTimeUI();
    }

    private void checkHasRemainingTime() {
        if (mRemainingSecond <= 0) {
            // 自动计时结束，执行关机命令（先保存各种数据）
            mTimer.stop();
            SaveHelper.saveAllElement();

            System.out.println("关机");
            try {
                Runtime.getRuntime().exec("shutdown -s -t 10");
            } catch (IOException e) {
                e.printStackTrace();
            }

            MainUI.get().dispose();
            dispose();
            System.exit(0);
        }
    }

    private void updateRemainingTimeUI() {
        int m = mRemainingSecond / 60;
        int s = mRemainingSecond % 60;
        String timeText = String.format("%02d : %02d", m, s);
        jLabel_TimeShow.setText(timeText);
    }

    private void jButton_BuyTimeMouseClicked(MouseEvent e) {
        // 手动续费，检查User点数，若足够则扣除点数，取消计时
        if (!jButton_BuyTime.isEnabled()) return;   // 点数不足
        if (mTimer.isRunning()) mTimer.stop();
        User user = User.get();
        user.setPoint(user.getPoint() - 1);
        UpdateHelper.updateAllPointUI();
        SaveHelper.saveAllElement();
        TimeCounterUI.get().startCounting(3600);
        dispose();
    }

    private void jButton_StopMouseClicked(MouseEvent e) {
        // 先移除失焦监听器
        jButton_Stop.removeFocusListener(mFocusListener);
        boolean ok = ManagerConfirmUI.confirm(this);
        if (!ok) {
            // 若验证失败，再加上失焦监听器
            jButton_Stop.addFocusListener(mFocusListener);
            return;
        }
        // 管理员确认，停止计时，保存所有数据，销毁该窗口
        mTimer.stop();
        SaveHelper.saveAllElement();
        MainUI.get().display();
        dispose();
    }

    private Timer mTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addSecondAndUpdateUI(-1);
        }
    });

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jLabel_Hit = new JLabel();
        jLabel_TimeShow = new JLabel();
        jLabel_Warning = new JLabel();
        jButton_Stop = new JButton();
        jButton_BuyTime = new JButton();

        //======== this ========
        setTitle("\u65f6\u95f4\u5230");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        //---- jLabel_Hit ----
        jLabel_Hit.setText("\u65f6\u95f4\u5230\uff0c\u5c06\u57283\u5206\u949f\u540e\u5173\u673a");
        jLabel_Hit.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabel_Hit, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_TimeShow ----
        jLabel_TimeShow.setText("00 : 00");
        jLabel_TimeShow.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabel_TimeShow, new GridBagConstraints(0, 1, 2, 1, 1.0, 4.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_Warning ----
        jLabel_Warning.setText("\u8bf7\u52ff\u8fdb\u884c\u5176\u4ed6\u64cd\u4f5c\uff0c\u5426\u5219\u5012\u8ba1\u65f6\u5c06\u7f29\u77ed\u81f315\u79d2");
        jLabel_Warning.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabel_Warning, new GridBagConstraints(0, 2, 2, 1, 0.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 10, 5), 0, 0));

        //---- jButton_Stop ----
        jButton_Stop.setText("\u505c\u6b62\uff08\u9700\u7ba1\u7406\u5458\u53e3\u4ee4\uff09");
        jButton_Stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton_StopMouseClicked(e);
            }
        });
        contentPane.add(jButton_Stop, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 10), 0, 0));

        //---- jButton_BuyTime ----
        jButton_BuyTime.setText("\u7eed1\u5c0f\u65f6\uff081\u70b9\u6570\uff09");
        jButton_BuyTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton_BuyTimeMouseClicked(e);
            }
        });
        contentPane.add(jButton_BuyTime, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel jLabel_Hit;
    private JLabel jLabel_TimeShow;
    private JLabel jLabel_Warning;
    private JButton jButton_Stop;
    private JButton jButton_BuyTime;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jLabel_Hit.setFont(S.Fo.COM_FONT_BOLD);
        jButton_Stop.setFont(S.Fo.COM_FONT);
        jButton_BuyTime.setFont(S.Fo.COM_FONT);
        jLabel_Warning.setFont(S.Fo.TIMEOUT_WARN_FONT);
        jLabel_Warning.setForeground(Color.ORANGE);

        // 时间到的倒计时采用的字体单独显示，不与主页面同步
        jLabel_TimeShow.setFont(S.Fo.TIMEOUT_COUNT_FONT);
        jLabel_TimeShow.setForeground(Color.RED);
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            return;
        }
        super.processWindowEvent(e);
    }

    private boolean mCanBoomTo15s = User.get().getPoint() >= 1;

    @Override
    public void firstDisplay() {
        // 设置到屏幕最大化
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setSize(800, 400); // 开发中为调试方便，设置窗口大小
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
//        setMaximumSize();
        setVisible(true);

        // 更新购买按钮的UI
        checkAndSetBuyButton();

        // 设置Stop按钮失焦处理
        jButton_Stop.setFocusable(true);     // 先设置Stop按钮拥有焦距
        jButton_Stop.addFocusListener(mFocusListener);

        // 启用窗口变化的监听器
        addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                try15SecondsCounting();
            }
        });

        start3MinutesCounting();
    }

    // 焦距监听器
    FocusListener mFocusListener = new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            // 若失去焦距，则尝试设置仅15s的倒计时
            try15SecondsCounting();
        }
    };

    private void start3MinutesCounting() {
        mRemainingSecond = 180; // s
        updateRemainingTimeUI();
        mTimer.start();
    }

    private void try15SecondsCounting() {
        if (mCanBoomTo15s) {
            mCanBoomTo15s = false;

            if (mTimer.isRunning()) mTimer.stop();

            jLabel_Hit.setText("15秒后关机");
            mRemainingSecond = 15; // s

            jLabel_Warning.setFont(S.Fo.TIMEOUT_WARN_CHANGE_FONT);
            jLabel_Warning.setText("///// WARNING /////");
            // 启动警告颜色变化
            startWaringColorChange();

            setExtendedState(JFrame.MAXIMIZED_BOTH);
            updateRemainingTimeUI();
            setVisible(true);   // 再显示
            mTimer.start();
        }
    }

    private void startWaringColorChange() {
        new Timer(25, new ActionListener() {
            int[] reds = new int[]{60, 70, 80, 90, 100,
                    110, 120, 130, 140, 150, 160, 170, 180, 190, 200,
                    210, 220, 230, 240, 250, 240, 230, 220, 210, 200,
                    190, 180, 170, 160, 150, 140, 130, 120, 110, 100,
                    90, 80, 70};
            int cSize = reds.length;
            int index = 20;

            @Override
            public void actionPerformed(ActionEvent e) {
                index = (index + 1) % cSize;
                jLabel_Warning.setForeground(new Color(reds[index], 0, 0));
            }
        }).start();
    }

    // 设置购买按键是否有效
    private void checkAndSetBuyButton() {
        if (User.get().getPoint() >= 1) {
            jButton_BuyTime.setEnabled(true);
        } else {
            jButton_BuyTime.setText("续1小时（点数不足）");
            jButton_BuyTime.setEnabled(false);
        }
    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {

    }
}
