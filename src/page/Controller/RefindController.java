package page.Controller;

import Util.ABCUtil.CheckABC;
import Util.CountDownUtil.ReFindCountDown;
import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import page.PageMain.LoginMain;
import service.Commend;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static page.Controller.EnrollController.isMail;
import static page.PageMain.RefindMain.refindstage;

public class RefindController {

    public TextField ID;
    public TextField Emaill;
    public TextField Vcode;
    public Text emailright;
    public Text vvcode;
    public ImageView back;
    public TextField newpsword;
    public PasswordField newpsword2;
    public Button text;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setRefindController(this);
    }


    /**
     * 返回上一个页面
     * @param mouseEvent
     * @throws Exception
     */
    public void back(MouseEvent mouseEvent) throws Exception {
        LoginMain.start((new Stage()));
        refindstage.close();
    }


    /**
     * 修改密码控件的点击事件
     * @param mouseEvent
     * @throws Exception
     */
    public void Refind(MouseEvent mouseEvent) throws Exception {
        if (newpsword.getText().equals(newpsword2.getText())) {
                Commend.userClientService.couldResetPassword(Vcode.getText(), Emaill.getText(), newpsword.getText());
        }else{
            JOptionPane.showMessageDialog(null, "两次输入的密码不一样，请重新输入");
        }
    }

    /**
     * 修改密码成功
     * @param b
     * @throws Exception
     */
    public void RefindOk(boolean b) throws Exception {
        if (b) {
            //验证成功,重设密码成功
            LoginMain.start((new Stage()));
            refindstage.close();
        } else {
            System.out.println("验证码错误！");
            vvcode.setText("验证码错误！");
        }
    }

    /**
     * 发送验证码
     * @param mouseEvent
     */
    public void Send(MouseEvent mouseEvent) {
        if (newpsword.getText().equals(newpsword2.getText())) {
            if(newpsword.getText().length()>=8 && CheckABC.Check(newpsword.getText())) {
                //            System.out.println("为了找回密码，我点击了发送");
                String email = Emaill.getText();
                if (isMail(email)) {
                    Commend.userClientService.checkAgain(email, "Refind");
                    ReFindCountDown countDown = new ReFindCountDown();
                    Thread thread = new Thread(countDown);
                    thread.start();
                } else {
                    emailright.setText("请输入正确格式的邮箱！");
                }
            }else{
                JOptionPane.showMessageDialog(null, "为了保证您的账号安全,\n" +
                        "密码设置必须超过八个字符，并带有至少一个英文字母");
            }

        }else{
            JOptionPane.showMessageDialog(null, "两次输入的密码不一样，请重新输入");
        }

    }

    /**
     * 邮箱匹配
     * @param b
     */
    public void EmailOk(boolean b) {
        if (!b) {
            Commend.userClientService.sendEmail(Emaill.getText());//发送验证码的方法
        } else {
            JOptionPane.showMessageDialog(null, "该邮箱未被绑定");
        }
    }

    /**
     * 关闭该页面
     * @param mouseEvent
     * @throws Exception
     */
    public void Close(MouseEvent mouseEvent) throws Exception {
        LoginMain.start((new Stage()));
        refindstage.close();
    }
}
