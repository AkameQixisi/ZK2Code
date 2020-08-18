package element;

import setting.S;

public class SaveHelper {
    public static void saveAllElement() {
        User user = User.get();
        user.setSecond(0);
        user.saveElements();
        MainTaskList.get().saveElements();
        SideTaskList.get().saveElements();
        S.saveFontSettings();
    }
}
