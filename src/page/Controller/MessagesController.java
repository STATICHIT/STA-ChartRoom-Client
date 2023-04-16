package page.Controller;

import Util.EmojiUtil.ConstructEmoji;
import Util.OpenFileUtil.OpenFileUtil;
import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class MessagesController {

    public Text id;
    public ImageView image;
    public TextFlow message;
    public Text time;
    public ImageView image2;
    public Text id2;
    public TextFlow message2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setMessagesController(this);
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setMesage(String message) {
        Text m1 = new Text();
        m1.setText(message);
        this.message.getChildren().add(m1);
    }

    //有表情的字符串
    public void setEmojiMesage(String message) {
        //从第一个字符开始遍历
        for(int i=0;i<message.length();i++){
            //如果检查到表情包
            if(message.charAt(i) == '$' && i<=message.length()-5 && message.charAt(i+4) == '%' && message.charAt(i+1)-'0'>=0 && message.charAt(i+1)-'0'<=9 &&
                    message.charAt(i+2)-'0'>=0 && message.charAt(i+2)-'0'<=9 && message.charAt(i+3)-'0'>=0 && message.charAt(i+3)-'0'<=9 &&
                    (message.charAt(i+1)-'0'+message.charAt(i+2)-'0'+message.charAt(i+3)-'0')>=0 && (message.charAt(i+1)-'0'+message.charAt(i+2)-'0'+message.charAt(i+3)-'0')<=170){

                int url =message.charAt(i+1)-'0'+message.charAt(i+2)-'0'+message.charAt(i+3)-'0';
                this.message.getChildren().add(ConstructEmoji.getEmoji(url));
                i+=4;
            }else{
                //不是表情包
                Text t= new Text();
                t.setText(String.valueOf(message.charAt(i)));
                this.message.getChildren().add(t);
            }
        }
    }

    //图片消息
    public void setImageMessage(String url){
        this.message.setUserData(url);
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(url, 127, 188, true, true, true));
        this.message.getChildren().add(imageView);
        imageView.setOnMouseClicked(event -> {
            OpenFileUtil.openFile(this.message.getUserData().toString().substring(5));
        });
    }

    //音频消息
    public void setMediaMessage(String url){
        Media media = new Media(url);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView();
        mediaView.setMediaPlayer(mediaPlayer);
        this.message.getChildren().add(mediaView);
    }

    //文件消息
    public void setFileMessage(String url){
        this.message.setUserData(url);
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("file:D:\\桌面\\聊天室\\chatroom\\client\\src\\page\\picture\\接收文件左.jpg", 250, 200, true, true, true));
        this.message.getChildren().add(imageView);
        imageView.setOnMouseClicked(event -> {
            OpenFileUtil.openFile(this.message.getUserData().toString().substring(10));
        });
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public void setId2(String id) {
        this.id2.setText(id);
    }

    public void setMesage2(String message) {
        Text m2 = new Text();
        m2.setText(message);
        this.message2.getChildren().add(m2);
    }

    //有表情的字符串
    public void setEmojiMesage2(String message) {
        //从第一个字符开始遍历
        for(int i=0;i<message.length();i++){
            //如果检查到表情包
            if(message.charAt(i) == '$' && i<=message.length()-5 && message.charAt(i+4) == '%' && message.charAt(i+1)-'0'>=0 && message.charAt(i+1)-'0'<=9 &&
                    message.charAt(i+2)-'0'>=0 && message.charAt(i+2)-'0'<=9 && message.charAt(i+3)-'0'>=0 && message.charAt(i+3)-'0'<=9 &&
                    ((message.charAt(i+1)-'0')*100+(message.charAt(i+2)-'0')*10+message.charAt(i+3)-'0')>=0 && ((message.charAt(i+1)-'0')*100+(message.charAt(i+2)-'0')*10+message.charAt(i+3)-'0')<=170){

                int url =((message.charAt(i+1)-'0')*100+(message.charAt(i+2)-'0')*10+message.charAt(i+3)-'0');
                this.message2.getChildren().add(ConstructEmoji.getEmoji(url));
                i+=4;
            }else{
                //不是表情包
                Text t= new Text();
                t.setText(String.valueOf(message.charAt(i)));
                this.message2.getChildren().add(t);
            }
        }
    }

    public void setImageMessage2(String url){
        this.message2.setUserData(url);
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(url, 127, 188, true, true, true));
        this.message2.getChildren().add(imageView);
        imageView.setOnMouseClicked(event -> {
            OpenFileUtil.openFile(this.message2.getUserData().toString().substring(5));
        });
    }
    public void setMediaMessage2(String url){
        Media media = new Media(url);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView();
        mediaView.setMediaPlayer(mediaPlayer);
        this.message2.getChildren().add(mediaView);
    }

    public void setFileMessage2(String url){
        this.message2.setUserData(url);
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("file:D:\\桌面\\聊天室\\chatroom\\client\\src\\page\\picture\\打开文件.png", 250, 200, true, true, true));
        this.message2.getChildren().add(imageView);
        imageView.setOnMouseClicked(event -> {
            OpenFileUtil.openFile(this.message2.getUserData().toString().substring(10));
        });
    }
}
