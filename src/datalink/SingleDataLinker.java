package datalink;

import setting.S;

import java.io.File;

public class SingleDataLinker extends DataLinker {

    SingleDataLinker(File file) {
        super(file);
    }

    /**
     * 按照传入的元素名称返回元素的文本
     *
     * @param elementsName 一组元素的名称
     * @return 和参数元素名称顺序相对应的文本，如果元素名称不存在，则文本内容为null
     */
    public String[] getElementsText(String[] elementsName) {
        String[] textArray = new String[elementsName.length];
        String rawStr = loadAllFileContent();
        String lReg = "<%s>";
        String rReg = "</%s>";
        String lStr, rStr;
        int lIndex, rIndex;
        // 遍历所有要获得Text的节点
        for (int i = 0; i < elementsName.length; ++i) {
            lStr = String.format(lReg, elementsName[i]);
            rStr = String.format(rReg, elementsName[i]);
            lIndex = rawStr.indexOf(lStr);
            rIndex = rawStr.indexOf(rStr);
            if (lIndex == -1 || rIndex == -1) {
                textArray[i] = null;    // 如果没有这样的节点
            } else {
                textArray[i] = rawStr.substring(lIndex + lStr.length(), rIndex);
            }
        }
        return textArray;
    }

    /**
     * 按照参数将元素名和元素文本添加到文件中，不会改变文件中原有信息
     * 如果元素名字原来存在于文件，则更新之；如果不存在，则创建之
     *
     * @param elementsName 所要添加的所有文本名字
     * @param elementsText 与文本名字顺序相对应的文本，如果文本个数小于文本名字，
     *                     则剩下的多出的文本名字的文本为空字符串 ""
     */
    public void setElementsText(String[] elementsName, String[] elementsText) {
        StringBuilder sb = new StringBuilder(loadAllFileContent());
        int textsLength = elementsText.length;
        String lReg = "<%s>";
        String rReg = "</%s>";
        String lStr, rStr;
        int lIndex, rIndex;
        String text;
        // 遍历所有元素名字
        for (int i = 0; i < elementsName.length; ++i) {
            text = i >= textsLength ? "" : elementsText[i];     // 获得节点的文本
            lStr = String.format(lReg, elementsName[i]);
            rStr = String.format(rReg, elementsName[i]);
            lIndex = sb.indexOf(lStr);
            rIndex = sb.indexOf(rStr);
            if (lIndex == -1 || rIndex == -1) {
                // 文件中不存在元素，添加之
                sb.append(lStr).append(text).append(rStr).append(S.Pr.NEWLINE);
            } else {
                // 文件中存在元素，更新它的文本值
                sb.replace(lIndex + lStr.length(), rIndex, text);
            }
        }
        saveAllContentToFile(sb.toString());
    }

}
