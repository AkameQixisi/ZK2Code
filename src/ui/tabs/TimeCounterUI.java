/*
 * Created by JFormDesigner on Sun Jul 26 22:14:41 CST 2020
 */

package ui.tabs;

import java.awt.event.*;

import element.SaveHelper;
import element.User;
import setting.S;
import ui.MyUI;
import ui.dialog.ManagerConfirmUI;
import ui.dialog.TimeoutUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Akame
 */
public class TimeCounterUI extends JPanel implements MyUI {
    private static TimeCounterUI sTimeCounterUI;
    private TimeCounterUI() {
        initComponents();
        initSettings();
        updateAllUI();
    }
    public static TimeCounterUI get() {
        if (sTimeCounterUI == null) {
            sTimeCounterUI = new TimeCounterUI();
        }
        return sTimeCounterUI;
    }

    private boolean mIsCounting = false;
    private int mRemainingSecond = 1;//User.get().getSecond();

    public void addSecondAndUpdateUI(int second) {
        if (second == 0) return;
        mRemainingSecond += second;
        checkHasRemainingTime();    // 检查自动计时结束
        updateRemainingTimeUI();
    }

    private void checkHasRemainingTime() {
        if (mRemainingSecond <= 0) {
            // 自动计时结束，保存当前数据，切换计时状态，弹出时间结束界面
            mIsCounting = false;
            mTimer.stop();

            SaveHelper.saveAllElement();

            updateStateShowUI();
            new TimeoutUI().firstDisplay();
        }
    }

    private void updateStateShowUI() {
        if (mIsCounting) {
            jLabel_StateHit.setText("计时中");
            jButton_StateChanger.setText("暂停");
            jLabel_TimeShow.setForeground(Color.RED);
        } else {
            jLabel_StateHit.setText("暂停中");
            jButton_StateChanger.setText("开始计时");
            jLabel_TimeShow.setForeground(Color.GREEN);
        }
    }

    private void updateRemainingTimeUI() {
        int h = mRemainingSecond / 3600;
        int m = (mRemainingSecond % 3600) / 60;
        int s = mRemainingSecond % 60;
        String timeText = String.format("%02d : %02d : %02d", h, m, s);
        jLabel_TimeShow.setText(timeText);
    }

    private Timer mTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addSecondAndUpdateUI(-1);
        }
    });

    public void startCounting(int second) {
        if (!mIsCounting) {
            mIsCounting = true;
            updateStateShowUI();
            addSecondAndUpdateUI(second);
            if (!mTimer.isRunning()) {
                mTimer.start();
            }
        }
    }

    private void jButton_StateChangerMouseClicked(MouseEvent e) {
        // 开始或暂停计时，手动控制
        if (mIsCounting) {
            // 正在计时中，验证管理员身份，暂停计时
            boolean ok = ManagerConfirmUI.confirm(this);
            if (!ok) return;
            mIsCounting = false;
            mTimer.stop();
        } else {
            // 暂停中，开始计时
            if (mRemainingSecond > 0) {
                mIsCounting = true;
                mTimer.start();
            }
            // 剩余时间本就为0
            // do nothing

        }
        updateStateShowUI();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jLabel_StateHit = new JLabel();
        jLabel_TimeShow = new JLabel();
        jButton_StateChanger = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());

        //---- jLabel_StateHit ----
        jLabel_StateHit.setText("\u8ba1\u65f6\u4e2d/\u6682\u505c\u4e2d");
        jLabel_StateHit.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_StateHit, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jLabel_TimeShow ----
        jLabel_TimeShow.setText("00 : 00 : 00");
        jLabel_TimeShow.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel_TimeShow, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //---- jButton_StateChanger ----
        jButton_StateChanger.setText("\u8ba1\u65f6\u5f00\u59cb/\u6682\u505c");
        jButton_StateChanger.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jButton_StateChangerMouseClicked(e);
            }
        });
        add(jButton_StateChanger, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 5, 5), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel jLabel_StateHit;
    private JLabel jLabel_TimeShow;
    private JButton jButton_StateChanger;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jLabel_StateHit.setFont(S.Fo.COM_FONT_BOLD);
        jLabel_TimeShow.setFont(S.Fo.TIME_COUNTER_FONT);
        jButton_StateChanger.setFont(S.Fo.COM_FONT);
    }

    @Override
    public void firstDisplay() {

    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {
        updateStateShowUI();
        updateRemainingTimeUI();
    }

}
