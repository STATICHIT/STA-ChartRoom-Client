package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreatGroupMain {
    public static Stage creatgrouptage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        creatgrouptage = stage;
        root = FXMLLoader.load(CreatGroupMain.class.getResource("../fxml/creatgroup.fxml"));
        stage.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(root);
        stage.setTitle("Sta聊天室-好友申请");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件

}
