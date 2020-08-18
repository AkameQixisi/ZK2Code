/*
 * Created by JFormDesigner on Mon Jul 27 10:04:01 CST 2020
 */

package ui.tabs;

import setting.S;
import ui.MyUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author Akame
 */
public class ExplanationTabUI extends JPanel implements MyUI {
    private static ExplanationTabUI sExplanationTabUI;
    private ExplanationTabUI() {
        initComponents();
        initSettings();
        updateAllUI();
    }
    public static ExplanationTabUI get() {
        if (sExplanationTabUI == null) {
            sExplanationTabUI = new ExplanationTabUI();
        }
        return sExplanationTabUI;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        jScrollPane = new JScrollPane();
        jTextArea_Explanation = new JTextArea();

        //======== this ========
        setLayout(new BorderLayout(5, 5));

        //======== jScrollPane ========
        {
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jScrollPane.setViewportView(jTextArea_Explanation);
        }
        add(jScrollPane, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane jScrollPane;
    private JTextArea jTextArea_Explanation;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void initSettings() {
        jTextArea_Explanation.setFont(S.Fo.COM_FONT);
    }

    @Override
    public void firstDisplay() {

    }

    @Override
    public void display() {

    }

    @Override
    public void updateAllUI() {
        StringBuilder sb = new StringBuilder();
        sb.append("1、剩余时间不能存储，只能暂停（需要管理员操作），且当次时间仅当次有效，下次（第二天）剩余时间将清0").append(S.Pr.NEWLINE);
        sb.append("2、待续");
        jTextArea_Explanation.setText(sb.toString());
        jTextArea_Explanation.setLineWrap(true);            // 自动换行功能
        jTextArea_Explanation.setWrapStyleWord(true);       // 断行不断字功能
        jTextArea_Explanation.setEditable(false);
    }
}
