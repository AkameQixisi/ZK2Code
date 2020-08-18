package ui.base;

import element.MainTask;
import element.MyTask;
import element.SideTask;
import sun.swing.DefaultLookup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JProcessCellRender extends JProgressBar implements ListCellRenderer<Object> {
    public JProcessCellRender() {
        super();
        setOpaque(true);
        setBorder(getNoFocusBorder());
        setName("List.JProcessCellRender");
    }

    /**
     * @param list，持有该render的JList对象
     * @param value，JList中的每一项，即JList<AType>中的AType类型的对象，它是通过调用list.getModel().getElementAt(index)方法获得的
     * @param index，项的索引
     * @param isSelected，该项是否被选中
     * @param cellHasFocus，该项是否获得焦点
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // 设置一些其他属性，如边框样式、被选中时样式等
        setOtherProperties(list, isSelected, cellHasFocus);
        if (value instanceof MainTask) {
            setMainTaskColor(isSelected);
        } else if (value instanceof SideTask) {
            setMSideTaskColor(isSelected);
        }

        MyTask myTask = (MyTask) value;
        int cur = myTask.getCurProcess();
        int total = myTask.getTotalProcess();
        String description = myTask.getDescription();
        setMinimum(0);
        setMaximum(total);
        setValue(cur);
        setStringPainted(true);
        setString(description + "（" + myTask.getPoint() + "点数）");

        return this;
    }

    private void setMSideTaskColor(boolean isSelected) {
        if (isSelected) {
            setBackground(new Color(192, 192, 192));
            setForeground(new Color(128, 64, 64));
        }
        else {
            setBackground(new Color(240, 240, 240));
            setForeground(new Color(255, 128, 128));
        }
    }

    private void setMainTaskColor(boolean isSelected) {
        if (isSelected) {
            setBackground(new Color(192, 192, 192));
            setForeground(new Color(64, 64, 128));
        }
        else {
            setBackground(new Color(240, 240, 240));
            setForeground(new Color(128, 128, 255));
        }
    }

    // DefaultListCellRenderer的部分实现
    private void setOtherProperties(JList<?> list, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());

        setEnabled(list.isEnabled());
        setFont(list.getFont());
        Border border = null;
        if (cellHasFocus) {
            if (isSelected) {
                border = DefaultLookup.getBorder(this, ui, "List.focusSelectedCellHighlightBorder");
            }
            if (border == null) {
                border = DefaultLookup.getBorder(this, ui, "List.focusCellHighlightBorder");
            }
        } else {
            border = getNoFocusBorder();
        }
        setBorder(border);
    }

    // DefaultListCellRenderer的实现
    private Border getNoFocusBorder() {
        Border border = DefaultLookup.getBorder(this, ui, "List.cellNoFocusBorder");
        if (System.getSecurityManager() != null) {
            if (border != null) return border;
            return SAFE_NO_FOCUS_BORDER;
        } else {
            if (border != null &&
                    (noFocusBorder == null ||
                            noFocusBorder == DEFAULT_NO_FOCUS_BORDER)) {
                return border;
            }
            return noFocusBorder;
        }
    }
    // DefaultListCellRenderer的实现
    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;
}