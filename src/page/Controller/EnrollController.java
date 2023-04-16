package page.Controller;


import Util.ABCUtil.CheckABC;
import Util.CountDownUtil.EnrollCountDown;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static page.PageMain.EnrollMain.enrollstage;


public class EnrollController {

    public TextField Nick;    //昵称
    public TextField Password;//密码
    public PasswordField Password2; //再次输入密码
    public TextField Email;   //邮箱
    public TextField Vcode;   //验证码
    public Text mailright;    //邮箱错误提示
    public Text vvcode;        //验证码错误提示
    public ImageView back;     //返回键
    public Button text;        //button上的字段


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setEnrollController(this);

        text.setText("send");
    }

    /**
     * 发送邮箱
     * @param mouseEvent
     */
    public void Sendmail(MouseEvent mouseEvent) {
        System.out.println("1__"+Password.getText());
        System.out.println("2__"+Password2.getText());
        if(Password.getText().equals(Password2.getText()))
        {
            if(Password.getText().length()>=8 && CheckABC.Check(Password.getText())){
                String email = Email.getText();
                //发送邮箱之前先验格式再查重
                if (isMail(email)) {
                    //如果邮箱格式正确,才进行查重
                    Commend.userClientService.checkAgain(email, "Enroll");
                } else {
                    mailright.setText("请输入正确格式的邮箱！");
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
     * 判断邮箱是否已经被注册
     * @param b
     */
    public void EmailExist(boolean b) {
        if (b) {
            //此方法无需接受响应所以可直接带参调用
            Commend.userClientService.sendEmail(Email.getText());//发送验证码的方法
            EnrollCountDown countDown = new EnrollCountDown();
            Thread thread = new Thread(countDown);
            thread.start();
        } else {
            JOptionPane.showMessageDialog(null, "该邮箱已被绑定，请更换邮箱");
        }
    }

    /**
     * 注册
     * @param mouseEvent
     * @throws Exception
     */
    public void Enroll(MouseEvent mouseEvent){
//        System.out.println("1__"+Password.getText());
//        System.out.println("2__"+Password2.getText());
        if(Nick.getText().equals("")){
            JOptionPane.showMessageDialog(null, "昵称不能为空！");
        }else{
            if(Password.getText().equals(Password2.getText())) {
                Commend.userClientService.checkUser(Nick.getText(), Password.getText(), Vcode.getText(), Email.getText());
            }else{
                JOptionPane.showMessageDialog(null, "两次输入的密码不一样，请重新输入");
            }
        }
    }

    /**
     * 注册成功
     * @param b
     * @throws Exception
     */
    public void EnrollOk(boolean b) throws Exception {
        if (b) {
            //打开初始登录页面，关闭注册页面，便于用户注册完直接登录
            LoginMain.start((new Stage()));
            enrollstage.close();
        } else {
            System.out.println("验证码错误！");
            vvcode.setText("验证码错误！");
        }
    }

    /**
     * 正则表达式判断邮箱
     */
    //判断邮箱是不是正确
    public static boolean isMail(String str) {
        boolean flag = false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(str);
        if (m.matches())
            flag = true;
        else
            System.out.println("输入邮箱格式错误......");
        return flag;
    }

    /**
     * 返回上一个界面
     * @param mouseEvent
     * @throws Exception
     */
    public void back(MouseEvent mouseEvent) throws Exception {
        LoginMain.start((new Stage()));
        enrollstage.close();
    }

    /**
     * 重新点击邮箱框后撤销提醒
     * @param mouseEvent
     */
    public void emailtextclick(MouseEvent mouseEvent) {
        mailright.setText(" ");
    }

    /**
     * 关闭该页面
     * @param mouseEvent
     * @throws Exception
     */
    public void Close(MouseEvent mouseEvent) throws Exception {
        LoginMain.start((new Stage()));
        enrollstage.close();
    }


    public void CheckName1(MouseEvent mouseEvent) {
        Commend.otherClientService.CheckName(Nick.getText());
    }

    public void CheckName2(MouseEvent mouseEvent) {
        Commend.otherClientService.CheckName(Nick.getText());
    }
}