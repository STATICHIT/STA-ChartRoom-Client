package main;

import javafx.application.Application;
import javafx.stage.Stage;
import page.PageMain.LoginMain;
import service.ClientConnectServerThread;
import service.Commend;


public class Main extends Application {
    public static void main(String[] args) {
        new Init();
        Commend.ThreadRun = true;
        ClientConnectServerThread ccst = new ClientConnectServerThread(Init.socket);
        ccst.start();
        launch(args);
    }

    @Override
    public void start(Stage primary) throws Exception {
        LoginMain.start(primary);
    }
}