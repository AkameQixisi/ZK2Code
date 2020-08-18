package setting;

import datalink.DataLinkerFactory;
import datalink.SingleDataLinker;

import java.awt.*;

/**
 * Settings
 */
public final class S {
    private S() {
    }

    /**
     * Program Settings
     */
    public static final class Pr {
        public static final String NEWLINE = System.lineSeparator();
    }

    /**
     * File Settings
     */
    public static final class Fi {
        public static final String USER_XML = "UserData/data/user.xml";
        public static final String MAIN_TASK_XML = "UserData/data/main_task.xml";
        public static final String SIDE_TASK_XML = "UserData/data/side_task.xml";
        public static final String ICON_FILE = "UserData/pic/icon.png";
        public static final String USER_IMG_FILE = "UserData/pic/user.jpg";
        public static final String SETTINGS_FILE = "SystemData/settings.xml";
    }

    /**
     * Font Settings
     */
    public static final class Fo {
        public static int COM_FONT_SIZE;
        public static int TIME_COUNTER_FONT_SIZE;
        public static Font COM_FONT;
        public static Font COM_FONT_BOLD;
        public static Font TIME_COUNTER_FONT;
        public static final Font TIMEOUT_COUNT_FONT = new Font("Microsoft Yahei UI", Font.BOLD, 256);
        public static final Font TIMEOUT_WARN_FONT = new Font("Microsoft Yahei UI", Font.BOLD, 84);
        public static final Font TIMEOUT_WARN_CHANGE_FONT = new Font("Microsoft Yahei UI", Font.BOLD, 128);
    }

    static {
        // 加载设置
        SingleDataLinker dataLinker = (SingleDataLinker) DataLinkerFactory.getSingle(Fi.SETTINGS_FILE);
        if (dataLinker != null) {
            String[] fontsSize = dataLinker.getElementsText(new String[]{"COM_FONT_SIZE", "TIME_COUNTER_FONT_SIZE"});
            int comSize = Integer.parseInt(fontsSize[0]);
            int counterSize = Integer.parseInt(fontsSize[1]);
            setFontSize(comSize, counterSize);
        }
    }

    // 需另外更新UI
    public static void addComSize(int size) {
        Fo.COM_FONT_SIZE += size;
        Fo.COM_FONT = new Font("Microsoft Yahei UI", Font.PLAIN, Fo.COM_FONT_SIZE);
        Fo.COM_FONT_BOLD = new Font("Microsoft Yahei UI", Font.BOLD, Fo.COM_FONT_SIZE);
    }

    // 需另外更新UI
    public static void addCounterSize(int size) {
        Fo.TIME_COUNTER_FONT_SIZE += size;
        Fo.TIME_COUNTER_FONT = new Font("Calibri", Font.BOLD, Fo.TIME_COUNTER_FONT_SIZE);
    }

    public static void setFontSize(int comSize, int counterSize) {
        Fo.COM_FONT_SIZE = comSize;
        Fo.TIME_COUNTER_FONT_SIZE = counterSize;
        Fo.COM_FONT = new Font("Microsoft Yahei UI", Font.PLAIN, Fo.COM_FONT_SIZE);
        Fo.COM_FONT_BOLD = new Font("Microsoft Yahei UI", Font.BOLD, Fo.COM_FONT_SIZE);
        Fo.TIME_COUNTER_FONT = new Font("Calibri", Font.BOLD, Fo.TIME_COUNTER_FONT_SIZE);
    }

    public static void saveFontSettings() {
        SingleDataLinker dataLinker = (SingleDataLinker) DataLinkerFactory.getSingle(Fi.SETTINGS_FILE);
        if (dataLinker == null) return;
        dataLinker.setElementsText(new String[]{"COM_FONT_SIZE", "TIME_COUNTER_FONT_SIZE"},
                new String[]{String.valueOf(Fo.COM_FONT_SIZE), String.valueOf(Fo.TIME_COUNTER_FONT_SIZE)});

    }
}
