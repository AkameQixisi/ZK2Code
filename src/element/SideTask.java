package element;

public class SideTask implements MyTask {
    // Elements begin
    private String mDescription;
    private float mPoint = 0;
    private int mCurProcess = 0;
    private int mTotalProcess = 0;
    // Elements end

    public SideTask(String[] elementsText) {
        try {
            mDescription = elementsText[0];
            mPoint = Float.parseFloat(elementsText[1]);
            mCurProcess = Integer.parseInt(elementsText[2]);
            mTotalProcess = Integer.parseInt(elementsText[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String[] getElementsText() {
        return new String[]{mDescription, String.valueOf(mPoint), String.valueOf(mCurProcess), String.valueOf(mTotalProcess)};
    }

    public boolean isCompleted() {
        return mCurProcess >= mTotalProcess;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public float getPoint() {
        return mPoint;
    }

    public void setPoint(float point) {
        mPoint = point;
    }

    public int getCurProcess() {
        return mCurProcess;
    }

    public void setCurProcess(int curProcess) {
        mCurProcess = curProcess;
    }

    public int getTotalProcess() {
        return mTotalProcess;
    }

    public void setTotalProcess(int totalProcess) {
        mTotalProcess = totalProcess;
    }

    /**
     * 完成一次任务
     * @return 如果本次完成后，任务全部进度完成，则返回0，如果未全部完成，则返回1
     *         如果任务本来就是已完成，则返回-1
     */
    public int accomplish() {
        if (mCurProcess < mTotalProcess) {
            ++mCurProcess;
            if (mCurProcess == mTotalProcess) return 0;
            return 1;
        }
        return -1;
    }
}
