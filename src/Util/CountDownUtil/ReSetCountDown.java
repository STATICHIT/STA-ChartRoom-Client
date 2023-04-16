package Util.CountDownUtil;

import chat.Controller;
import javafx.application.Platform;

public class ReSetCountDown extends Thread {

    @Override
    public void run() {
        System.out.println("按钮不可用");
        int time = 60;
        Platform.runLater(() -> {
            Controller.getResetPasswordController().text.setDisable(true);
        });

        while (time > 0) {
            time--;
            try {
                Thread.sleep(1000);
                int ss = time % 60;
//                System.out.println("(" + ss + "s)");
                String s = String.valueOf(ss);
                Platform.runLater(() -> {
                    Controller.getResetPasswordController().text.setText("( " + s + "s )");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Platform.runLater(() -> {
            Controller.getResetPasswordController().text.setDisable(false);
            Controller.getResetPasswordController().text.setText("send");
        });

        System.out.println("按钮可用");
    }
}
