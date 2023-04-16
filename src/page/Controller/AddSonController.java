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

import static page.Controller.AddController.obslist3;

public class AddSonController {


    public ImageView image;
    public Text id;
    public Text age;
    public Text sex;
    public Text text;
    public Text nickname;
    public Text time;
    public Button agree;
    public Button refuse;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setAddSonController(this);
    }

    /**
     * 同意好友申请
     * @param mouseEvent
     */
    public void Agree(MouseEvent mouseEvent){
        String Id = id.getText();
        //告诉服务器该好友申请被同意了，更新数据库
        Commend.otherClientService.getnew(Id, Commend.theUser);//告诉服务端两个用户是好友了
        //删除该条申请
        obslist3.removeIf(s -> s.getId().equals(Id));
        //获取该好友信息，加入好友列表
        Commend.otherClientService.privatemessage(Id, "3");
    }

    /**
     * 拒绝好友申请
     * @param mouseEvent
     */
    public void Refuse(MouseEvent mouseEvent) {
        //告诉服务器该好友申请被拒绝了，更新数据库
        Commend.otherClientService.refusegetnew(Commend.theUser, id.getText());
        //删除该条申请
        obslist3.removeIf(s -> s.getId().equals(id.getText()));
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id.setText(id);
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

}
