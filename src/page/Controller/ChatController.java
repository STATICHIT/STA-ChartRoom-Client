package page.Controller;

import chat.Controller;
import chat.MessageList;
import chat.ViewList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import page.PageMain.*;
import service.Commend;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static page.PageMain.EmojiMain.emojistage;

public class ChatController {

    public ListView messageListView;//消息列表（好友列表）
    public ListView chatListView;//聊天列表
    public ImageView theavatar;
    public Text target;
    public TextArea connect;


    public static ObservableList<ViewList> obslist = FXCollections.observableArrayList();
    public static ObservableList<MessageList> obslist2 = FXCollections.observableArrayList();
    public ImageView init;
    public ImageView creat;
    public ImageView more;
    public Button commonPhrases;
    public static ContextMenu contextMenu = new ContextMenu();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {


        //想实现回车键发送来着，没成功
//        JTextArea jTextArea = new JTextArea();
//        jTextArea.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                if(e.getKeyChar()== KeyEvent.VK_ENTER) {
//                    System.out.println("ENTER");
//                }
//            }
//        });

        //初始化常用语列表
        MenuItem item0 = new MenuItem("==添加更多==");
        item0.setStyle("-fx-background-color: #BED2B6");
        MenuItem item1 = new MenuItem("在吗？");
        MenuItem item2 = new MenuItem("在干嘛？");
        MenuItem item3 = new MenuItem("吃了饭嘛？");
        MenuItem item4 = new MenuItem("好久不见!");
        MenuItem item5 = new MenuItem("哈哈哈哈哈哈~");
        item0.setOnAction(event -> {
            String newPhrases = JOptionPane.showInputDialog(null," 请输入您想添加的常用于：\n","添加常用语",JOptionPane.PLAIN_MESSAGE);
            Commend.messageClientService.CommonPhrases(Commend.me.getUserId(),newPhrases);
            AddCommonPhrases(newPhrases);
        });
        item1.setOnAction(event -> {
            sendMessage(item1.getText());
        });

        item2.setOnAction(event -> {
            sendMessage(item2.getText());
        });

        item3.setOnAction(event -> {
            sendMessage(item3.getText());
        });

        item4.setOnAction(event -> {
            sendMessage(item4.getText());
        });

        item5.setOnAction(event -> {
            sendMessage(item5.getText());
        });
        contextMenu.getItems().addAll(item0,item1,item2,item3,item4,item5);
        commonPhrases.setContextMenu(contextMenu);

        init.setVisible(true);
        creat.setVisible(false);
//        打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setChatController(this);

        //判断是否有自选头像
        if (!Commend.me.getAvatar().equals("无")) {
            theavatar.setImage(new Image(Commend.me.getAvatar(), 100, 100, true, true, true));
        }

        Commend.otherClientService.friendList("1");
        Commend.messageClientService.LoadPhrases();

        messageListView.setItems(obslist);
        messageListView.setCellFactory(new Callback<ListView<ViewList>, ListCell<ViewList>>() {
            @Override
            public ListCell<ViewList> call(ListView<ViewList> param) {
                return new ListCell<ViewList>() {
                    protected void updateItem(ViewList item, boolean empty) {
//                        setStyle("-fx-background-color: transparent");
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            //导入好友列表图像
                            FXMLLoader fxmlLoader = new FXMLLoader();

                            setOnMouseClicked(event -> {
                                init.setVisible(false);
                                //清空原本的信息列表
                                chatListView.getItems().clear();

//                                System.out.println("当前点击的是：" + item.getId());
                                target.setText(item.getId());

                                //加载消息列表
                                Commend.messageClientService.GetMessageList(Commend.theUser, item.getId());
                                //使得初始滚动条置于底端
                                chatListView.scrollTo(100000);
                            });
                            fxmlLoader.setLocation(getClass().getResource("../fxml/son.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友列表加载错误");
                                e.printStackTrace();
                            }
                            this.setGraphic(hBox);

                            //修改对应列表的内容
                            //获得子项的Controller-
                            SonController sonController = fxmlLoader.getController();

                            //根据item书写id处内容
                            sonController.setId(item.getId());
                            //根据item书写签名处内容
                            sonController.setSign(item.getSign());
                            //判断是否有自选头像
                            if (!item.getImage().equals("无")) {
                                sonController.image.setImage(new Image(item.getImage(), 100, 100, true, true, true));
                            }

                        } else {
                            this.setGraphic(null);
                        }
                    }
                };
            }
        });

        chatListView.setItems(obslist2);

        chatListView.setCellFactory(new Callback<ListView<MessageList>, ListCell<MessageList>>() {
            @Override
            public ListCell<MessageList> call(ListView<MessageList> param) {
                return new ListCell<MessageList>() {
                    protected void updateItem(MessageList item, boolean empty) {
                        setStyle("-fx-background-color: white");
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            //导入好友列表图像
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/messages.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友消息加载错误");
                                e.printStackTrace();
                            }
                            this.setGraphic(hBox);

                            //修改对应列表的内容
                            //获得子项的Controller
                            MessagesController messagesController = fxmlLoader.getController();
                            //如果是己方，显示在右边
                            if (item.getId().equals(Commend.me.getNickName())) {
                                //根据item书写id处内容
                                messagesController.setId2(item.getId());
                                //根据item书写发送时间处内容
                                messagesController.setTime(item.getTime());
                                //根据item书写消息内容
//                                System.out.println(item.getMessage());
                                if(item.getMessage().endsWith("png") || item.getMessage().endsWith("jpg")){
                                    messagesController.setImageMessage2(item.getMessage());
                                }else if(item.getMessage().endsWith("mp3")) {
                                    messagesController.setMediaMessage2(item.getMessage());
                                }else if(item.getMessage().startsWith("ReallyFile")){
//                                    System.out.println("我是己方，我的内容是"+item.getMessage());
                                    messagesController.setFileMessage2(item.getMessage());
                                }
                                else{
                                    messagesController.setEmojiMesage2(item.getMessage());
                                }
                                //判断是否有自选头像
                                if (!item.getImage().equals("无")) {
                                    messagesController.image2.setImage(new Image(item.getImage(), 100, 100, true, true, true));
                                }
                                //设置另一变看不见
                                Controller.getMessagesController().id.setVisible(false);
//                                Controller.getMessagesController().mesage.setVisible(false);
                                Controller.getMessagesController().image.setVisible(false);
                            } else {
                                //根据item书写id处内容
                                messagesController.setId(item.getId());
                                //根据item书写发送时间处内容
                                messagesController.setTime(item.getTime());
                                //根据item书写消息内容
                                if(item.getMessage().endsWith("png") || item.getMessage().endsWith("jpg")){
                                    messagesController.setImageMessage(item.getMessage());
                                }else if(item.getMessage().endsWith("mp3")){
                                    messagesController.setMediaMessage(item.getMessage());
                                }else if(item.getMessage().startsWith("ReallyFile")){
//                                    System.out.println("我是对方，我的内容是"+item.getMessage());//测试
                                    messagesController.setFileMessage(item.getMessage());
                                }
                                else{
                                    messagesController.setEmojiMesage(item.getMessage());
                                }
                                //判断是否有自选头像
                                if (!item.getImage().equals("无")) {
                                    messagesController.image.setImage(new Image(item.getImage(), 100, 100, true, true, true));
                                }
                                //设置另一变看不见
                                Controller.getMessagesController().id2.setVisible(false);
//                                Controller.getMessagesController().mesage2.setVisible(false);
                                Controller.getMessagesController().image2.setVisible(false);

                                Controller.messagesController.message.setOnMouseClicked(event -> {

                                });
                            }
                        } else {
                            this.setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    /**
     * 加载好友列表
     * @param friendList
     */
    public void LoadFriendList(ArrayList<ViewList> friendList) {
        for (ViewList a : friendList) {
            obslist.add(a);
        }
    }

    /**
     * 加载消息列表
     * @param messageList
     */
    public void LoadMessageList(ArrayList<MessageList> messageList) {
        for (MessageList a : messageList) {
            obslist2.add(a);
        }
    }

    /**
     * 退出
     * @param mouseEvent
     */
    public void out(MouseEvent mouseEvent) {
        Commend.otherClientService.goOut();
    }


    /**
     * send按键的点击事件
     * @param mouseEvent
     */
    public void send(MouseEvent mouseEvent) {
        if(connect.getText().equals("")){
            JOptionPane.showMessageDialog(null, "消息不能为空");
        }else{
            sendMessage(connect.getText());
        }
    }

    /**
     * 发消息的具体方法
     * @param message
     */
    public void sendMessage(String message){
        if (Commend.state == 1) {
            Commend.messageClientService.sendMessageToOne(message, Commend.me.getNickName(), target.getText());
            chatListView.scrollTo(100000);
            obslist2.add(new MessageList(Commend.me.getNickName(), message, new Date().toString(), Commend.me.getAvatar()));
            connect.setText("");
        } else if (Commend.state == 2) {
            Commend.messageClientService.sendMessageToGroup(message, Commend.me.getNickName(), target.getText());
            chatListView.scrollTo(100000);
            obslist2.add(new MessageList(Commend.me.getNickName(), message, new Date().toString(), Commend.me.getAvatar()));
            connect.setText("");
        }
    }


    /**
     * 发送新消息
     * @param nick
     * @param concent
     * @param time
     * @param avatar
     * @param state
     * @param groupName
     */
    public void GetNewMessage(String nick, String concent, String time, String avatar, int state, String groupName) {
        if (state == 1) {
            if (target.getText().equals(nick)) {
                //接收到消息使滑动条置于底部
                chatListView.scrollTo(100000);
                Platform.runLater(() -> {
                    obslist2.add(new MessageList(nick, concent, time, avatar));
                });
            }
        } else if (state == 2) {
            if (target.getText().equals(groupName)) {
                //接收到消息使滑动条置于底部
                chatListView.scrollTo(100000);
                Platform.runLater(() -> {
                    obslist2.add(new MessageList(nick, concent, time, avatar));
                });
            }
        }
    }

    /**
     * 请求加载群组列表
     * @param mouseEvent
     */
    public void group(MouseEvent mouseEvent) {
        init.setVisible(true);
        creat.setVisible(true);
        //页面状态变成群组页面
        Commend.state = 2;
        //清空原本的信息列表
        messageListView.getItems().clear();
        //加载群组列表
        Commend.otherClientService.GetGroupList(Commend.theUser);
    }

    /**
     * 加载群组列表
     * @param groupList
     */
    public void LoadGroupList(ArrayList<ViewList> groupList) {
        for (ViewList a : groupList) {
            obslist.add(a);
        }
    }

    /**
     * 点击好友页面
     * @param mouseEvent
     */
    public void friend(MouseEvent mouseEvent) {
        init.setVisible(true);
        creat.setVisible(false);
        //页面状态变成好友页面
        Commend.state = 1;
        //清空原本的信息列表
        messageListView.getItems().clear();
        //加载好友列表
        Commend.otherClientService.friendList("1");
    }

    /**
     * 点击群组页面
     * @param mouseEvent
     * @throws Exception
     */
    public void more(MouseEvent mouseEvent) throws Exception {
        Commend.object = target.getText();
//        System.out.println("我打开的是"+Commend.object+"的信息");
        if (Commend.state == 1) {
//            System.out.println("我现在处于好友页面");
            FriendDataMain.start((new Stage()));
        } else if (Commend.state == 2) {
//            System.out.println("我现在处于群组页面");
            GroupInfoMain.start((new Stage()));
        }

    }

    /**
     * 打开个人资料
     * @param mouseEvent
     * @throws Exception
     */
    public void privatemessage(MouseEvent mouseEvent) throws Exception {
        PrivateDataMain.start((new Stage()));
    }

    /**
     * 打开添加页面
     * @param mouseEvent
     * @throws Exception
     */
    public void add(MouseEvent mouseEvent) throws Exception {
        AddMain.start((new Stage()));
    }

    /**
     * 打开搜索页面
     * @param mouseEvent
     * @throws Exception
     */
    public void search(MouseEvent mouseEvent) throws Exception {
        SearchMain.start((new Stage()));
    }

    /**
     * 打开创建群聊页面
     * @param mouseEvent
     * @throws Exception
     */
    public void CreatGroup(MouseEvent mouseEvent) throws Exception {
        CreatGroupMain.start((new Stage()));
    }

    /**
     * 最小化
     * @param mouseEvent
     */
    public void MinSize(MouseEvent mouseEvent) {
        Stage stage = (Stage) Controller.chatController.target.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * 传输图片
     * @param mouseEvent
     */
    public void addImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser(); //文件选择窗口
        fileChooser.setTitle("Open Resource File");  //窗口名
        fileChooser.setInitialDirectory(new File("D:\\桌面")); //设置默认打开的文件路径
        File file = fileChooser.showOpenDialog(new Stage());//显示屏幕选择窗口
        if (file != null) {
            String str = file.getAbsolutePath();  //sowOpenDialog 方法返回选择文件的相对路径
            if(str.endsWith("jpg")||str.endsWith("png")||str.endsWith("jpeg")){
                String url = "file:" + str;
                if (Commend.state == 1) {
                    Commend.messageClientService.sendMessageToOne(url, Commend.me.getNickName(), target.getText());
                    chatListView.scrollTo(100000);
                    obslist2.add(new MessageList(Commend.me.getNickName(), url, new Date().toString(), Commend.me.getAvatar()));
                } else if (Commend.state == 2) {
                    Commend.messageClientService.sendMessageToGroup(url, Commend.me.getNickName(), target.getText());
                    chatListView.scrollTo(100000);
                    obslist2.add(new MessageList(Commend.me.getNickName(), url, new Date().toString(), Commend.me.getAvatar()));
                }
            }else{
                JOptionPane.showMessageDialog(null, "请选择图片文件！");

            }

        }
    }

    /**
     * 添加常用语
     * @param phrases
     */
    public void AddCommonPhrases(String phrases){
        MenuItem item = new MenuItem(phrases);
        item.setOnAction(event -> {
            sendMessage(item.getText());
        });
        contextMenu.getItems().add(item);
    }

    /**
     * 加载自定义常用语
     * @param commonPhrases
     */
    public void LoadCommonPhrases(ArrayList<String> commonPhrases){
        for(String phrases : commonPhrases){
            AddCommonPhrases(phrases);
        }
    }

    //传输文件
    public void SendFile(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser(); //文件选择窗口
        fileChooser.setTitle("Open Resource File");  //窗口名
        fileChooser.setInitialDirectory(new File("D:\\桌面")); //设置默认打开的文件路径
        File file = fileChooser.showOpenDialog(new Stage());//显示屏幕选择窗口
        if (file != null) {
            String url = "ReallyFile"+file.getAbsolutePath();  //sowOpenDialog 方法返回选择文件的相对路径
            if (Commend.state == 1) {
                Commend.messageClientService.sendMessageToOne(url, Commend.me.getNickName(), target.getText());
                chatListView.scrollTo(100000);
                obslist2.add(new MessageList(Commend.me.getNickName(), url, new Date().toString(), Commend.me.getAvatar()));
            } else if (Commend.state == 2) {
                Commend.messageClientService.sendMessageToGroup(url, Commend.me.getNickName(), target.getText());
                chatListView.scrollTo(100000);
                obslist2.add(new MessageList(Commend.me.getNickName(), url, new Date().toString(), Commend.me.getAvatar()));
            }
        }
    }


    public void Emoji(MouseEvent mouseEvent) throws Exception {
        EmojiMain.start((new Stage()));
        emojistage.setX(mouseEvent.getScreenX());
        emojistage.setY(mouseEvent.getScreenY()-275);
    }

    public void CloseEmoji1(MouseEvent mouseEvent) {
        if(Controller.getEmojiController()!= null){
            emojistage.close();
            Controller.setEmojiController(null);
        }

    }

    public void CloseEmoji2(MouseEvent mouseEvent) {
        if(Controller.getEmojiController()!= null){
            emojistage.close();
            Controller.setEmojiController(null);
        }
    }

    public void CloseEmoji3(MouseEvent mouseEvent) {
        if(Controller.getEmojiController()!= null){
            emojistage.close();
            Controller.setEmojiController(null);
        }
    }
}

