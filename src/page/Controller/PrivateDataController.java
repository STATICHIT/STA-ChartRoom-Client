package page.Controller;

import chat.Controller;
import chat.User;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import page.PageMain.ChangeMain;
import page.PageMain.ResetPasswordMain;
import service.Commend;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static page.PageMain.PrivateDataMain.privatedatastage;

public class PrivateDataController {

    public Text id;
    public Text nick;
    public Text sex;
    public Text birth;
    public Text sign;
    public ImageView avatar;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setPrivateDataController(this);
        init(Commend.me);
    }

    /**
     * 开启页面时的初始化
     * @param user2
     */
    public void init(User user2) {
        Commend.user = user2;
//        System.out.println(user2);
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
     * 更改个人资料
     * @param mouseEvent
     * @throws Exception
     */
    public void change(MouseEvent mouseEvent) throws Exception {
        ChangeMain.start((new Stage()));
        privatedatastage.close();
    }

    /**
     * 更换个人头像
     * @param mouseEvent
     */
    public void select(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser(); //文件选择窗口
        fileChooser.setTitle("Open Resource File");  //窗口名
        fileChooser.setInitialDirectory(new File("D:\\avatar")); //设置默认打开的文件路径
        File file = fileChooser.showOpenDialog(new Stage());//显示屏幕选择窗口
        if (file != null) {
            String str = file.getAbsolutePath();  //sowOpenDialog 方法返回选择文件的相对路径
            String url = "file:" + str;
            avatar.setImage(new Image(url, 100, 100, true, true, true));
            Commend.me.setAvatar(url);
            Controller.getChatController().theavatar.setImage(new Image(url, 100, 100, true, true, true));
            Commend.otherClientService.SetAvatar(Commend.theUser, url);
        }
    }

    /**
     * 修改密码
     * @param mouseEvent
     * @throws Exception
     */
    public void resetpassword(MouseEvent mouseEvent) throws Exception {
        privatedatastage.close();
        ResetPasswordMain.start((new Stage()));
    }
}
