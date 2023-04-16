package Util.FileIOUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    /**
     * 通过字符流实现文件的拷贝
     */
    public static void copyFile(String sourcePath) {
        String targetPath = "";
        //源文件路径
        File source = new File(sourcePath);
        //目标文件路径
        File target = new File(targetPath);

        //如果源文件不存在则不能拷贝
        if (!source.exists()) {
            return;
        }
        //如果目标文件目录不存在则创建
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }

        FileReader in = null;
        FileWriter out = null;
        try {
            //字符输入流和字符输出流
            in = new FileReader(source);
            out = new FileWriter(target);

            char[] c = new char[1024];
            int temp = 0;
            //每次读取1024个字符
            while ((temp = in.read(c)) != -1) {
                //输出到文件
                out.write(c, 0, temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
