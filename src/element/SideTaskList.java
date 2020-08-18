package element;

import datalink.DataLinkerFactory;
import datalink.MultiDataLinker;
import setting.S;

import java.util.ArrayList;
import java.util.List;

public class SideTaskList {
    private static SideTaskList sSideTaskList;
    private final String FILENAME = S.Fi.SIDE_TASK_XML;

    private List<SideTask> mAllSidTaskList;
    private List<SideTask> mIncompleteSideTaskList;

    private SideTaskList() {
        mAllSidTaskList = new ArrayList<>();
        mIncompleteSideTaskList = new ArrayList<>();
        loadElements();
    }

    public static SideTaskList get() {
        if (sSideTaskList == null) {
            sSideTaskList = new SideTaskList();
        }
        return sSideTaskList;
    }

    private String[] getElementsName() {
        return new String[]{"Description", "Point", "CurProcess", "TotalProcess"};
    }

    private void loadElements() {
        MultiDataLinker dataLinker = (MultiDataLinker) DataLinkerFactory.getMulti(FILENAME);
        if (dataLinker == null) return;
        List<String[]> ls = dataLinker.getElementsText(getElementsName());
        for (String[] elementsText : ls) {
            SideTask sideTask = new SideTask(elementsText);
            mAllSidTaskList.add(sideTask);
            if (!sideTask.isCompleted()) {
                // 如果任务未完成，则加到未完成的列表中
                mIncompleteSideTaskList.add(sideTask);
            }
        }
    }

    private List<String[]> getElementsText() {
        List<String[]> ls = new ArrayList<>();
        for (SideTask sideTask : mAllSidTaskList) {
            ls.add(sideTask.getElementsText());
        }
        return ls;
    }

    public SideTask[] getAllSideTasks(boolean onlyIncomplete) {
        if (onlyIncomplete) {
            return mIncompleteSideTaskList.toArray(new SideTask[0]);
        } else {
            return mAllSidTaskList.toArray(new SideTask[0]);
        }
    }

    public void addSidTask(SideTask sideTask) {
        mAllSidTaskList.add(sideTask);
        if (!sideTask.isCompleted()) {
            mIncompleteSideTaskList.add(sideTask);
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
            if (selectedIndex >= mIncompleteSideTaskList.size()) return -1;
            SideTask sideTask = mIncompleteSideTaskList.get(selectedIndex);
            // ret表示任务的完成情况，-1表任务早已完成，无需更新UI；0表示任务全部完成，1表示正常完成一次
            int ret = sideTask.accomplish();
            if (ret == -1) return -1;
            else if (ret == 0) {
                // 任务已全部完成
                mIncompleteSideTaskList.remove(selectedIndex);
            }
            return sideTask.getPoint();

        } else {
            if (selectedIndex >= mAllSidTaskList.size()) return -1;
            SideTask sideTask = mAllSidTaskList.get(selectedIndex);
            // ret表示任务的完成情况，-1表任务早已完成，无需更新UI；0表示任务全部完成，1表示正常完成一次
            int ret = sideTask.accomplish();
            if (ret == -1) return -1;
            else if (ret == 0) {
                // 任务已全部完成
                mIncompleteSideTaskList.remove(sideTask);
            }
            return sideTask.getPoint();
        }
    }
}
