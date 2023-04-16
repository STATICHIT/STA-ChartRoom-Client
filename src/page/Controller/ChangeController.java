package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.Commend;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static page.PageMain.ChangeMain.changestage;

public class ChangeController {

    public ComboBox sexchose;
    public TextField sign;
    public TextField nickname;
    public DatePicker birthday;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() throws ParseException {

        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setChangeController(this);
        init();
    }

    /**
     * 开启页面的初始化
     * @throws ParseException
     */
    public void init() throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (Commend.user.getBirth().equals("暂未设置")) {
            birthday.setValue(LocalDate.parse(dateFormat.format(date)));
        } else {
            date = dateFormat.parse(Commend.user.getBirth());
            birthday.setValue(LocalDate.parse(dateFormat.format(date)));
        }
        if (Commend.user.getSex().equals("男")) {
            sexchose.setValue("男");
        } else if (Commend.user.getSex().equals("女")) {
            sexchose.setValue("女");
        } else {
            sexchose.setValue("-");
        }
        nickname.setPromptText(Commend.user.getNickName());
        sign.setPromptText(Commend.user.getSign());
    }

    /**
     * 修改个人资料
     * @param mouseEvent
     */
    public void ok(MouseEvent mouseEvent) {
        String sex ;
        sex = sexchose.getValue().toString();
        Commend.me.setSex(sex);
        String birth = birthday.getValue().toString();
        Commend.me.setBirth(birth);
        if (nickname.getText().equals("")) {
            nickname.setText(null);
        } else {
            Commend.me.setNickName(nickname.getText());
        }
        if (sign.getText().equals("")) {
            sign.setText(null);
        } else {
            Commend.me.setSign(sign.getText());
        }
        Commend.otherClientService.resetPrivateData(nickname.getText(), sex, birth, sign.getText());
        changestage.close();
    }

    public void CheckName1(MouseEvent mouseEvent) {
        Commend.otherClientService.CheckName(nickname.getText());
    }

    public void CheckName2(MouseEvent mouseEvent) {
        Commend.otherClientService.CheckName(nickname.getText());
    }

    public void CheckName3(MouseEvent mouseEvent) {
        Commend.otherClientService.CheckName(nickname.getText());
    }
}
