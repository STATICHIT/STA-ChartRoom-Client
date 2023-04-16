package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SonController {

    public Text id;
    public ImageView image;
    public Text text;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setSonController(this);
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setSign(String qiansign) {
        this.text.setText(qiansign);
    }

}
