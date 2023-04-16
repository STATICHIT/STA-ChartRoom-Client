package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmojiMain {
    public static Stage emojistage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        emojistage = stage;
        root = FXMLLoader.load(EnrollMain.class.getResource("../fxml/emoji.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("表情包");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件
}
