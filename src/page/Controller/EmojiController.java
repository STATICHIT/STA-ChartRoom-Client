package page.Controller;

import Util.EmojiUtil.ConstructEmoji;
import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EmojiController {

    public VBox vBox;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setEmojiController(this);

        HBox hBox = new HBox();
        for(int i=1;i<= 170;i++){
            hBox.getChildren().add(ConstructEmoji.getEmoji(i));
            if(i%10 == 0){
                //如果该行表情包达到十个，就加入一个列盒子
                vBox.getChildren().add(hBox);
                //新设置一个行盒子
                hBox = new HBox();
            }
        }

    }
}
