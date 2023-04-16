package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddInfoMain {
    public static Stage addinfostage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        addinfostage = stage;
        root = FXMLLoader.load(AddInfoMain.class.getResource("../fxml/addinfo.fxml"));
        stage.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(root);
        stage.setTitle("Sta聊天室-添加好友");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件
}
