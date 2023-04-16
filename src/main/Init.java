package main;

import chat.Request;
import chat.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Init {
    public static Socket socket;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;

    public Init() {
    }

    static {
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(Request request) {
        try {
            oos.writeObject(request);//发送request对象
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Response receive() {
        Response response = new Response();
        try {
            response = (Response) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
