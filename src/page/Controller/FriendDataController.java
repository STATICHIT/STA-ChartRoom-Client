package page.Controller;

import chat.Controller;
import chat.User;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.Commend;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static page.Controller.ChatController.obslist;
import static page.PageMain.FriendDataMain.frienddatastage;

public class FriendDataController {

    @FXML
    private ImageView avatar;
    @FXML
    private Text id;
    @FXML
    private Text nick;
    @FXML
    private Text sex;
    @FXML
    private Text birth;
    @FXML
    private Text sign;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setFriendDataController(this);

//        System.out.println(Commend.object + "-------");
        Commend.otherClientService.privatemessage(Commend.object, "4");

    }

    /**
     * 打开页面时的初始化
     * @param user2
     */
    public void init(User user2) {
        nick.setText(user2.getNickName());
        id.setText(user2.getUserId());
        sex.setText(user2.getSex());
        birth.setText(user2.getBirth());
        sign.setText(user2.getSign());
        if (!user2.getAvatar().equals("无")) {
            avatar.setImage(new Image(user2.getAvatar(), 100, 100, true, true, true));
        }
    }

    /**
     * 删除好友
     * @param mouseEvent
     */
    public void Delete(MouseEvent mouseEvent) {
        int n = JOptionPane.showConfirmDialog(null, "删除后你将从对方联系人列表中消失，且以后不再接收\n" +
                "此人的会话消息。\n", "Title", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
//            System.out.println("YES");
            //告诉服务器，两人不再是好友,更改数据库
            Commend.otherClientService.Delete(Commend.theUser, id.getText());
            //删除好友列表中这一条
            obslist.removeIf(s -> s.getId().equals(nick.getText()));
            Controller.getChatController().init.setVisible(true);
            frienddatastage.close();
        } else {
            //不做处理
//            System.out.println("NO");
        }
    }


}
