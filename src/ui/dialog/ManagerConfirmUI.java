
package ui.dialog;

import ui.base.Password;

import javax.swing.*;
import java.awt.*;

public class ManagerConfirmUI extends JDialog {

    public static boolean confirm(Component parentComponent) {
        Password ps = new Password();
        String inStr = JOptionPane.showInputDialog(parentComponent, String.format("密文：%04d，请联系管理员生成口令", ps.getRandom()));
        if (inStr == null || inStr.equals("") || inStr.length() != 4) {
            JOptionPane.showMessageDialog(parentComponent, "口令无效");
            return false;
        }
        int inNum = Integer.parseInt(inStr);
        if (inNum < 0 || inNum >= 10000) {
            JOptionPane.showMessageDialog(parentComponent, "口令无效");
            return false;
        }
        int id = ps.checkForId(inNum);
        if (id == -1) {
            JOptionPane.showMessageDialog(parentComponent, "口令无效");
            return false;
        } else {
            // 验证成功
            return true;
        }
    }

}
