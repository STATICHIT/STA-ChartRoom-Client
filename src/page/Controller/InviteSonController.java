package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class InviteSonController {


    public ImageView image;
    public Text id;
    public CheckBox choose;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        Controller.setInviteSonController(this);
    }

    public void setId(String id) {
        this.id.setText(id);
    }


}