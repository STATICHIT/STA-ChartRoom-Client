package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.Commend;

import java.net.URL;
import java.util.ResourceBundle;

import static page.PageMain.ChangeGroupMain.changegroupstage;

public class ChangeGroupController {

    public TextField name;
    public TextField sign;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setChangeGroupController(this);
        name.setPromptText(Commend.user2.getNickName());
        sign.setPromptText(Commend.user2.getSign());

    }

    /**
     * 修改群组资料
     * @param mouseEvent
     */
    public void Change(MouseEvent mouseEvent) {
        if (name.getText().equals("")) {
            name.setText(null);
        }
        if (sign.getText().equals("")) {
            sign.setText(null);
        }
        Commend.otherClientService.resetGroupData(name.getText(), sign.getText(), Commend.user2.getUserId());
        Controller.getChatController().obslist.clear();
        Commend.otherClientService.GetGroupList(Commend.theUser);
        Controller.getChatController().target.setText(name.getText());
        changegroupstage.close();
    }
}
