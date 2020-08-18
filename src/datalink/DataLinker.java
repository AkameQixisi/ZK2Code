package datalink;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 读取UTF-8的文件
 */
public class DataLinker {
    protected File mLinkedFile;

    DataLinker(File file) {
        mLinkedFile = file;
    }

    protected String loadAllFileContent() {
        byte[] bytes = new byte[(int) mLinkedFile.length()];
        try {
            InputStream is = new FileInputStream(mLinkedFile);
            is.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    protected void saveAllContentToFile(String content) {
        try {
            OutputStream os = new FileOutputStream(mLinkedFile);
            os.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
