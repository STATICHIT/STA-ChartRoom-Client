package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import page.Controller.GroupInfoController;

public class GroupInfoMain {
    public static Stage groupinfostage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        groupinfostage = stage;
        root = FXMLLoader.load(GroupInfoController.class.getResource("../fxml/groupinfo.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Sta聊天室");
        stage.setScene(scene);
        stage.show();
    }
    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件
}
