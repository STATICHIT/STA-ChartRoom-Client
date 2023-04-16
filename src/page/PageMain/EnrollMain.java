package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.atomic.AtomicReference;

public class EnrollMain {
    public static Stage enrollstage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        enrollstage = stage;
        root = FXMLLoader.load(EnrollMain.class.getResource("../fxml/enroll.fxml"));
        /**窗口拖动事件**/
        stage.initStyle(StageStyle.TRANSPARENT);//隐藏标题栏
        AtomicReference<Double> xOffSet = new AtomicReference<>((double) 0);
        AtomicReference<Double> yOffSet = new AtomicReference<>((double) 0);
        root.setOnMousePressed(event -> {
            xOffSet.set(event.getSceneX());
            yOffSet.set(event.getSceneY());
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffSet.get());
            stage.setY(event.getScreenY() - yOffSet.get());
        });
        Scene scene = new Scene(root);
        stage.setTitle("Sta聊天室-注册页面");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件
}
