import ui.MainUI;

public class Akame {
    public static void main(String[] args) {
        try {
            Class.forName("setting.S");     // 加载设置类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MainUI.get().firstDisplay();
    }
}
