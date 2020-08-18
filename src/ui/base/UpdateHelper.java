package ui.base;

import ui.MainUI;
import ui.tabs.*;

public class UpdateHelper {
    public static void updateAllPointUI() {
        PointStoreUI.get().updatePointAndSliderUI();
        ManagerUI.get().updateAllUI();
        UserInfoTabUI.get().updateUserPointUI();
    }

    public static void addRemainingSecondAndUpdateUI(int second) {
        TimeCounterUI.get().addSecondAndUpdateUI(second);
    }

    public static void updateAllFontSize() {
        MainUI.get().initSettings();
        MainTaskTabUI.get().initSettings();
        SideTaskTabUI.get().initSettings();
        PointStoreUI.get().initSettings();
        TimeCounterUI.get().initSettings();
        UserInfoTabUI.get().initSettings();
        ManagerUI.get().initSettings();
        ExplanationTabUI.get().initSettings();
    }
}
