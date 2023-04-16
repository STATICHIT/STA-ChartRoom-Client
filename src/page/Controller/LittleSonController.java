package page.Controller;

import chat.Controller;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LittleSonController {
    public ImageView image;
    public Text id;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        Controller.setLittleSonController(this);
    }

    public void setId(String id) {
        this.id.setText(id);
    }
}
