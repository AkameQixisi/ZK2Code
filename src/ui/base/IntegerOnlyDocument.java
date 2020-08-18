package ui.base;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class IntegerOnlyDocument extends PlainDocument {

    public IntegerOnlyDocument() {}

    @Override
    public void insertString(int off, String str, AttributeSet a) throws BadLocationException {
        if (isNumber(str)) {
            super.insertString(off, str, a);
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
