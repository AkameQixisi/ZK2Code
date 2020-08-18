package element;

import datalink.DataLinkerFactory;
import datalink.SingleDataLinker;
import setting.S;
import ui.base.UpdateHelper;

public class User {
    private static User sUser;
    private final String FILENAME = S.Fi.USER_XML;

    // Elements begin
    private String mName;
    private float mPoint = 0;
    private int mSecond = 0;
    // Elements end

    private User() {
        loadElements();
    }

    public static User get() {
        if (sUser == null) {
            sUser = new User();
        }
        return sUser;
    }

    private String[] getElementsName() {
        return new String[]{"Name", "Point", "Second"};
    }

    private String[] getElementsText() {
        return new String[]{mName, String.valueOf(mPoint), String.valueOf(mSecond)};
    }

    private void loadElements() {
        SingleDataLinker dataLinker = (SingleDataLinker) DataLinkerFactory.getSingle(FILENAME);
        if (dataLinker == null) return;
        String[] texts = dataLinker.getElementsText(getElementsName());
        try {
            mName = texts[0] == null ? "" : texts[0];
            mPoint = Float.parseFloat(texts[1]);
            mSecond = Integer.parseInt(texts[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveElements() {
        SingleDataLinker dataLinker = (SingleDataLinker) DataLinkerFactory.getSingle(FILENAME);
        if (dataLinker == null) return;
        dataLinker.setElementsText(getElementsName(), getElementsText());
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getPoint() {
        return mPoint;
    }

    public void setPoint(float point) {
        mPoint = point;
    }

    public int getSecond() {
        return mSecond;
    }

    public void setSecond(int second) {
        mSecond = second;
    }
}
