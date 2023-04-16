package page.Controller;

import chat.Controller;
import chat.Message;
import chat.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.Commend;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static page.PageMain.AddInfoMain.addinfostage;

public class AddInfoController {

    public Text nickname;
    public Text userid;
    public Text text1;
    public Text text2;
    public TextField addinfo;
    public ImageView avatar;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setAddInfoController(this);
        Commend.otherClientService.privatemessage(Commend.object, "2");
    }

    /**
     * 开启页面时的初始化
     * @param user
     */
    public void init(User user) {
        nickname.setText(user.getNickName());
        userid.setText(user.getUserId());
        text1.setText(user.getSex());
        text2.setText(user.getBirth());
        if (!user.getAvatar().equals("无")) {
            avatar.setImage(new Image(user.getAvatar(), 100, 100, true, true, true));
        }
    }

    /**
     * 添加好友
     * @param mouseEvent
     */
    public void add(MouseEvent mouseEvent) {
        Message message = new Message(Commend.theUser, Commend.object, addinfo.getText(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Commend.otherClientService.addRequest(message);

        JOptionPane.showMessageDialog(null, "请求已发送");
        addinfostage.close();
    }
}
