package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import page.PageMain.ChatMain;
import page.PageMain.EnrollMain;
import page.PageMain.RefindMain;
import service.Commend;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static page.PageMain.LoginMain.loginstage;

public class LoginController {

    public TextField id;
    public TextField ps;
    public Text tip;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setLoginController(this);
    }

    /**
     * 登录
     * @param mouseEvent
     * @throws Exception
     */
    public void login(MouseEvent mouseEvent) throws Exception {
        Commend.userClientService.CheckOnline(id.getText());
    }

    public void CouldLogin(Boolean b){
        if(b){
            //返回true，说明用户已经在线，不能再次登录，给弹窗提示
            JOptionPane.showMessageDialog(null, "该用户已经处于在线状态，不能重复登录。");
        }else{
            //返回false，说明用户不在线，可登录
            //把用户填入的账号密码传参到这个方法中去
            Commend.userClientService.checkUser(id.getText(), ps.getText());

        }
    }

    /**
     * 登录成功
     * @param b
     * @throws Exception
     */
    public void LoginOK(boolean b) throws Exception {
        if (b) {
            System.out.println("<==欢迎(用户" + id + ")登录==>");
            Commend.userClientService.AddOnlineUser(id.getText());
            ChatMain.start((new Stage()));
            loginstage.close();
        } else {
            tip.setText("账号或密码输入错误！");
            System.out.println("登录失败");
        }

    }

    /**
     * 打开找回密码页面
     * @param mouseEvent
     * @throws Exception
     */
    public void retrieve(MouseEvent mouseEvent) throws Exception {
        RefindMain.start((new Stage()));
        loginstage.close();
    }

    /**
     * 打开注册页面
     * @param mouseEvent
     * @throws Exception
     */
    public void enroll(MouseEvent mouseEvent) throws Exception {
        EnrollMain.start((new Stage()));
        loginstage.close();
    }

    /**
     * 退出
     * @param mouseEvent
     */
    public void Close(MouseEvent mouseEvent) {
        Commend.otherClientService.Out();
    }

    /**
     * 最小化
     * @param mouseEvent
     */
    public void MinSize(MouseEvent mouseEvent) {
        Stage stage = (Stage) id.getScene().getWindow();
        stage.setIconified(true);
    }
}
