package page.Controller;

import chat.Controller;
import chat.GroupInfo;
import chat.Message;
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

import static page.PageMain.AddGroupInfoMain.addgroupinfostage;

public class AddGroupInfoController {
    public ImageView avatar;
    public Text name;
    public Text groupId;
    public Text sign;
    public Text text1;
    public Text text2;
    public TextField addGroupInfo;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setAddGroupInfoController(this);
        //待修改
        Commend.otherClientService.LoadGroupBasicInfo(Commend.object);

    }

    /**
     * 开启页面时的初始化操作
     * @param groupInfo
     */
    public void init(GroupInfo groupInfo) {
        name.setText(groupInfo.getName());
        groupId.setText(groupInfo.getId());
        text1.setText(groupInfo.getNum());
        text2.setText(groupInfo.getSign());
        if (!groupInfo.getAvatar().equals("无")) {
            avatar.setImage(new Image(groupInfo.getAvatar(), 100, 100, true, true, true));
        }
    }

    /**
     * 申请添加群组
     * @param mouseEvent
     */
    public void AddGroup(MouseEvent mouseEvent) {
        Message message = new Message(Commend.me.getUserId(), Commend.object, addGroupInfo.getText(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Commend.otherClientService.addGroupRequest(message);

        JOptionPane.showMessageDialog(null, "请求已发送");
        addgroupinfostage.close();
    }
}
