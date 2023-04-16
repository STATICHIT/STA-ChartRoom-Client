package page.Controller;

import Util.ABCUtil.CheckABC;
import Util.CountDownUtil.ReSetCountDown;
import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import page.PageMain.PrivateDataMain;
import service.Commend;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static page.Controller.EnrollController.isMail;
import static page.PageMain.ResetPasswordMain.resetstage;

public class ResetPasswordController {

    public TextField NewPassword;
    public PasswordField NewPassword2;
    public TextField Email;
    public TextField Vcode;
    public Text vvcode;
    public Text emailright;
    public Button text;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setResetPasswordController(this);

    }

    /**
     * 重新设置密码
     * @param mouseEvent
     * @throws Exception
     */
    public void yes(MouseEvent mouseEvent) throws Exception {
        if (NewPassword.getText().equals(NewPassword2.getText())) {
                Commend.otherClientService.resetPassword(Vcode.getText(), Email.getText(), NewPassword.getText());
        }else{
            JOptionPane.showMessageDialog(null, "两次输入的密码不一样，请重新输入");
        }
    }

    /**
     * 重新设置密码成功
     * @param b
     * @throws Exception
     */
    public void ResetOk(boolean b) throws Exception {
        if (b) {
            JOptionPane.showMessageDialog(null, "修改密码成功！",
                    "StaChatRoom", JOptionPane.INFORMATION_MESSAGE, null);
            //验证成功,重设密码成功
            resetstage.close();
            PrivateDataMain.start((new Stage()));

        } else {
            System.out.println("验证码错误！");
            vvcode.setText("验证码错误！");
        }
    }


    /**
     * 发送验证码
     * @param mouseEvent
     * @throws Exception
     */
    public void send(MouseEvent mouseEvent) throws Exception {
        if (NewPassword.getText().equals(NewPassword2.getText())) {
            if(NewPassword.getText().length()>=8 && CheckABC.Check(NewPassword.getText())) {
                try {
                    if (isMail(Email.getText()) != true) {
                        emailright.setText("请输入正确格式的邮箱！");
                    } else {
                        Commend.userClientService.sendEmail(Email.getText());//发送验证码的方法
                        ReSetCountDown countDown = new ReSetCountDown();
                        Thread thread = new Thread(countDown);
                        thread.start();
                    }
                } catch (Exception e) {
                    System.out.println("验证码发送失败！！");
                }
            }else{
                JOptionPane.showMessageDialog(null, "为了保证您的账号安全,\n" +
                        "密码设置必须超过八个字符，并带有至少一个英文字母");
            }
        } else {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一样，请重新输入");
        }
    }
}
