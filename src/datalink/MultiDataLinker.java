package datalink;

import setting.S;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiDataLinker extends DataLinker {

    MultiDataLinker(File file) {
        super(file);
    }

    /**
     * 根据一组元素名字，获得匹配这一组元素名字的所有文本
     *
     * @param elementsName 一组元素的名字，这些元素构成一个实体
     * @return 所有匹配一组元素名字构成实体的List，如果不完全匹配一组元素构成的实体，
     * 则无法被匹配出来
     */
    public List<String[]> getElementsText(String[] elementsName) {
        List<String[]> texts = new ArrayList<>();
        String rawStr = loadAllFileContent();

        // 设置匹配规则，并获得所有匹配实体
        String regStr = getXmlReg(elementsName);
        Pattern namesPattern = Pattern.compile(regStr);
        Matcher matcher = namesPattern.matcher(rawStr);

        // 将匹配到的结果组装成String[]，并添加到List中
        while (matcher.find()) {
            String[] elementTexts = new String[elementsName.length];
            for (int i = 1; i <= matcher.groupCount(); ++i) {
                elementTexts[i - 1] = matcher.group(i);
            }
            texts.add(elementTexts);
        }
        return texts;
    }

    /**
     * 设置一组元素所匹配的实体的文本值
     * 如果文本长度小于匹配到的实体个数，则之后的实体保持不变
     * 如果文本的长度大于所匹配到的实体个数，则之后的文本作为新的实体添加到文件尾部
     *
     * @param elementsName  一组元素的名字，它们一起匹配一个实体
     * @param elementsText 实体文本的List
     */
    public void setElementsText(String[] elementsName, List<String[]> elementsText) {
        StringBuilder sb = new StringBuilder(loadAllFileContent());

        // 设置匹配规则，并获得所有匹配实体
        String regStr = getXmlReg(elementsName);
        Pattern namesPattern = Pattern.compile(regStr);
        Matcher matcher = namesPattern.matcher(sb.toString());

        int index = 0, off = 0;
        while (matcher.find() && index < elementsText.size()) {
            for (int i = 1; i <= matcher.groupCount(); ++i) {
                // sb字符串替换一次后，原来的索引位置就失效了，需要加上off调整
                int start = matcher.start(i) + off;
                int end = matcher.end(i) + off;
                // 如果元素名字有对应的新文本
                if (i - 1 < elementsText.get(index).length) {
                    String str = elementsText.get(index)[i - 1];
                    // 如果文本不为null，则设置新文本（为null则表示采用原值）
                    if (str != null) {
                        off = off + str.length() - (end - start);  // off即新位置相对于原位置的偏移量
                        sb.replace(start, end, str);
                    }
                }
            }
            ++index;
        }

        // 文本中还有剩余，则将它们添加到文件尾部
        while (index < elementsText.size()) {
            sb.append(getRawXmlStr(elementsName, elementsText.get(index)));
            ++index;
        }
        saveAllContentToFile(sb.toString());    // 将数据保存到文件
    }

    /**
     * 获得多个元素名字的正则匹配字符串
     *
     * @param elementsName 一组元素的名字，这些元素构成一个实体
     * @return 一次匹配一组元素的正则表达式
     */
    private String getXmlReg(String[] elementsName) {
        StringBuilder sb = new StringBuilder();
        for (String s : elementsName) {
            sb.append("<").append(s).append(">(.*?)</").append(s).append(">")
                    .append("\\s*");
        }
        sb.delete(sb.lastIndexOf("\\s*"), sb.length());
        return sb.toString();
    }

    /**
     * 根据元素名字和文本，来生成所需的xml格式字符串，最后自带换行，无需手动再加
     *
     * @param elementsName 一组元素名字
     * @param elementsText 一组文本值，他与元素名字相对于，如果其长度小于名字的个数，
     *                     则用空字符串；如果长度大于元素名字，则多出的部分被忽略
     */
    private String getRawXmlStr(String[] elementsName, String[] elementsText) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elementsName.length; ++i) {
            String text = i < elementsText.length ? elementsText[i] : "";
            sb.append("<").append(elementsName[i]).append(">").append(text)
                    .append("</").append(elementsName[i]).append(">").append(S.Pr.NEWLINE);
        }
        return sb.toString();
    }
}
