package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.Commend;

import java.net.URL;
import java.util.ResourceBundle;

import static page.Controller.AddController.obslist7;

public class AddGroupSonController {

    public ImageView image;//头像
    public Text nickname;//昵称
    public Text age;//年龄
    public Text sex;//性别
    public Text text;//申请备注
    public Button agree;//同意入群
    public Button refuse;//拒绝入群
    public Text time;//申请时间
    public Text groupName;//群名字
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setAddGroupSonController(this);
    }


    /**
     * 同意用户加入群组
     * @param mouseEvent
     */
    public void Agree(MouseEvent mouseEvent) {
        String userName = nickname.getText();
        String groupNames = groupName.getText();
        //告诉服务器该加群申请被同意了，该人是该群新成员了,更新数据库
        Commend.otherClientService.GetGroupNew(userName, groupNames);
        //删除该条申请
        obslist7.removeIf(s -> s.getNickName().equals(userName));
    }


    /**
     * 拒绝用户加入群组
     * @param mouseEvent
     */
    public void Refuse(MouseEvent mouseEvent) {
        //告诉服务器该加群申请被拒绝了，更新数据库
        Commend.otherClientService.RefuseGroupNew(nickname.getText(), groupName.getText());
        //删除该条申请
        obslist7.removeIf(s -> s.getNickName().equals(nickname.getText()));
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setAge(String age) {
        this.age.setText(age);
    }

    public void setSex(String sex) {
        this.sex.setText(sex);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setNickname(String nickname) {
        this.nickname.setText(nickname);
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public void setGroupName(String groupName) {
        this.groupName.setText(groupName);
    }
}
