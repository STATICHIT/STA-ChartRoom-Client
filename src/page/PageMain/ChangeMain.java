package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ChangeMain {
    public static Stage changestage;
    public static Parent root;

    public static void start(Stage stage) throws IOException {
        changestage = stage;
        root = FXMLLoader.load(ChangeMain.class.getResource("../fxml/change.fxml"));
        stage.initStyle(StageStyle.UTILITY);
        ComboBox sex = (ComboBox) root.lookup("#sexchose");
        sex.getItems().addAll("男", "女", "-");
        Scene scene = new Scene(root);
        stage.setTitle("Sta聊天室");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件
}
