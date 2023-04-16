package page.PageMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.atomic.AtomicReference;

public class ChatMain {
    public static Stage chatstagestage;
    public static Parent root;

    public static void start(Stage stage) throws Exception {
        chatstagestage = stage;
        root = FXMLLoader.load(ChatMain.class.getResource("../fxml/chat.fxml"));
        Scene scene = new Scene(root);

        /**窗口拖动事件**/
        stage.initStyle(StageStyle.UNDECORATED);//隐藏标题栏
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

        stage.setTitle("Sta聊天室");
        stage.setScene(scene);
        stage.show();
    }

    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }//该方法用来获取每一个控件
}
