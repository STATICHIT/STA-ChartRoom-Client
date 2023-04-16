package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddGroupInfoMain {
    public static Stage addgroupinfostage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        addgroupinfostage = stage;
        root = FXMLLoader.load(AddInfoMain.class.getResource("../fxml/addgroupinfo.fxml"));
        stage.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(root);
        stage.setTitle("Sta聊天室-添加群组");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件

}
