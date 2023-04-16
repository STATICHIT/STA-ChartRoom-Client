package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import page.PageMain.AddGroupInfoMain;
import page.PageMain.AddInfoMain;
import service.Commend;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController {


    public TextField id;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setSearchController(this);
    }

    /**
     * 搜索
     * @param mouseEvent
     * @throws Exception
     */
    public void search(MouseEvent mouseEvent) throws Exception {
        if (id.getText() == null) {
            JOptionPane.showMessageDialog(null, "搜索不能为空");
        } else {
            if (id.getText().equals(Commend.theUser)) {
                JOptionPane.showMessageDialog(null, "不可以添加自己为好友哦");
            } else {
                Commend.otherClientService.search(id.getText());
            }
        }
    }

    /**
     * 判断输入id的存在性和属性（用户或群组）
     * @param b
     * @param state
     * @throws Exception
     */
    public void IdExist(boolean b, String state) throws Exception {
        if (b) {
            if (state.equals("0")) {
                JOptionPane.showMessageDialog(null, "您搜索的用户或群组您已添加");
            } else if (state.equals("1")) {
                //好友
                Commend.object = id.getText();
                AddInfoMain.start((new Stage()));
            } else if (state.equals("2")) {
                //群主
                Commend.object = id.getText();
                AddGroupInfoMain.start((new Stage()));

            }
        } else {
            JOptionPane.showMessageDialog(null, "您搜索的用户或群组不存在");
        }
    }
}
