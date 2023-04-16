package Util.OpenFileUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenFileUtil {
    public static void openFile(String filePath) {
        try {
            File file = new File(filePath); // 创建文件对象，路径为filePath
            Desktop.getDesktop().open(file); // 启动已在本机桌面上注册的关联应用程序，打开文件对象file。
        } catch (IOException | NullPointerException e) { // 异常处理
            System.err.println(e);
        }
    } // 方法openFile结束

    public static void main(String[] args) {
        //不加File：
//        openFile("C:\\Windows\\Web\\Wallpaper\\Windows\\img0.jpg"); // 打开系统默认桌面壁纸
//        openFile("D:\\桌面\\question.txt"); // 打开txt文件
//        openFile("D:\\桌面\\question.txt");

    } // 方法main结束

}
