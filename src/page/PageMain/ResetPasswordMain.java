package page.PageMain;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ResetPasswordMain {
    public static Stage resetstage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        resetstage = stage;
        root = FXMLLoader.load(ResetPasswordMain.class.getResource("../fxml/resetpassword.fxml"));
        stage.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(root);
        stage.setTitle("Sta聊天室—找回密码");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件
}
