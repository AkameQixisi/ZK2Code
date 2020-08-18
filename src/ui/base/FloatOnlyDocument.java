package ui.base;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/**
 * 仿造的NumberDocument.java
 */
public class FloatOnlyDocument extends PlainDocument {
    public FloatOnlyDocument() {
    }

    @Override
    public void insertString(int off, String str, AttributeSet a) throws BadLocationException {
        if (isFloatNumeric(off, str)) {
            super.insertString(off, str, a);
        }
    }

    private boolean isFloatNumeric(int off, String str) {
        if (off == 0) {
            //  输入的第一个数，只能是0~9
            return isNumber(str);
        } else {
            // 输入的不是第一个数，可以为0~9，也可以为 . 表示小数点（要保证小数点是第一次出现）
            boolean hasDot = false;
            try {
                String hasStr = getText(0, getLength());
                hasDot = hasStr.contains(".");
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            if (hasDot) {
                // 如果已经有了小数点，则只能输入0~9
                return isNumber(str);
            } else {
                // 如果没有小数点，则可以输入 .
                return isNumber(str) || (str.length() == 1 && str.equals("."));
            }
        }
    }

    // 判断是否是整数
    private boolean isNumber(String str) {
        try {
            Long.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
