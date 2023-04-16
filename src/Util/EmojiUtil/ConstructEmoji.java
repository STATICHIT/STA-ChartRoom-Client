package Util.EmojiUtil;

import chat.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ConstructEmoji {
    //用一个集合把每个表情的地址存起来
    public static List<Image> emoji = new ArrayList<>();


    static {
        for (int i = 1; i <= 170; i++) {
            String url ="";
            if(i<10){
                url = "00"+i;
            }else if(i>=10 && i<100){
                url = "0"+i;
            }else{
                url = "" +i;
            }
            Image image = new Image("file:" + "D:\\桌面\\聊天室\\QQ表情\\静态\\\\" + url + ".png");
            emoji.add(image);
        }
    }

    public static ImageView getEmoji (int i) {
        ImageView imageView = new ImageView(emoji.get(i-1));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        int finalI = i;
        imageView.setOnMouseClicked(event -> {
            String s = Controller.getChatController().connect.getText();
            String url;
            if(finalI<10){
                url = "00"+finalI;
            }else if(finalI>=10 && finalI<100){
                url = "0"+finalI;
            }else{
                url = "" +finalI;
            }
            s += "$" + url + "%";
            Controller.getChatController().connect.setText(s);
//            System.out.println("当前点击的是" + url);
        });
        return imageView;
    }
}
