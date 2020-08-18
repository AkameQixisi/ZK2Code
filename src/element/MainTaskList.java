package element;

import datalink.DataLinkerFactory;
import datalink.MultiDataLinker;
import setting.S;

import java.util.ArrayList;
import java.util.List;

public class MainTaskList {
    private static MainTaskList sMainTaskList;
    private final String FILENAME = S.Fi.MAIN_TASK_XML;

    private List<MainTask> mAllMainTaskList;
    private List<MainTask> mIncompleteMainTaskList;

    private MainTaskList() {
        mAllMainTaskList = new ArrayList<>();
        mIncompleteMainTaskList = new ArrayList<>();
        loadElements();
    }

    public static MainTaskList get() {
        if (sMainTaskList == null) {
            sMainTaskList = new MainTaskList();
        }
        return sMainTaskList;
    }

    private String[] getElementsName() {
        return new String[]{"Description", "Point", "CurProcess", "TotalProcess"};
    }

    private void loadElements() {
        MultiDataLinker dataLinker = (MultiDataLinker) DataLinkerFactory.getMulti(FILENAME);
        if (dataLinker == null) return;
        List<String[]> ls = dataLinker.getElementsText(getElementsName());
        for (String[] elementsText : ls) {
            MainTask mainTask = new MainTask(elementsText);
            mAllMainTaskList.add(mainTask);
            if (!mainTask.isCompleted()) {
                // 如果任务未完成，则加到未完成的列表中
                mIncompleteMainTaskList.add(mainTask);
            }
        }
    }

    private List<String[]> getElementsText() {
        List<String[]> ls = new ArrayList<>();
        for (MainTask mainTask : mAllMainTaskList) {
            ls.add(mainTask.getElementsText());
        }
        return ls;
    }

    public MainTask[] getAllMainTasks(boolean onlyIncomplete) {
        if (onlyIncomplete) {
            return mIncompleteMainTaskList.toArray(new MainTask[0]);
        } else {
            return mAllMainTaskList.toArray(new MainTask[0]);
        }
    }

    public void addMainTask(MainTask mainTask) {
        mAllMainTaskList.add(mainTask);
        if (!mainTask.isCompleted()) {
            mIncompleteMainTaskList.add(mainTask);
        }
    }

    public void saveElements() {
        MultiDataLinker dataLinker = (MultiDataLinker) DataLinkerFactory.getMulti(FILENAME);
        if (dataLinker == null) return;
        dataLinker.setElementsText(getElementsName(), getElementsText());
    }

    /**
     * 完成一次任务
     * @return 如果任务正常完成，则返回任务的报酬，大于等于0的数，且表示需要更新UI
     *         如果任务没有完成，或任务早已完成，则返回-1表示不需要更新UI
     */
    public float accomplish(int selectedIndex, boolean inIncompleteTaskList) {
        if (inIncompleteTaskList) {
            if (selectedIndex >= mIncompleteMainTaskList.size()) return -1;
            MainTask mainTask = mIncompleteMainTaskList.get(selectedIndex);
            // ret表示任务的完成情况，-1表任务早已完成，无需更新UI；0表示任务全部完成，1表示正常完成一次
            int ret = mainTask.accomplish();
            if (ret == -1) return -1;
            else if (ret == 0) {
                // 任务已全部完成
                mIncompleteMainTaskList.remove(selectedIndex);
            }
            return mainTask.getPoint();

        } else {
            if (selectedIndex >= mAllMainTaskList.size()) return -1;
            MainTask mainTask = mAllMainTaskList.get(selectedIndex);
            // ret表示任务的完成情况，-1表任务早已完成，无需更新UI；0表示任务全部完成，1表示正常完成一次
            int ret = mainTask.accomplish();
            if (ret == -1) return -1;
            else if (ret == 0) {
                // 任务已全部完成
                mIncompleteMainTaskList.remove(mainTask);
            }
            return mainTask.getPoint();
        }
    }
}

