package page.Controller;

import chat.Controller;
import chat.GroupInfo;
import chat.User;
import chat.ViewList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import page.PageMain.ChangeGroupMain;
import service.Commend;
import service.OtherClientService;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static page.Controller.ChatController.obslist;
import static page.PageMain.GroupInfoMain.groupinfostage;

public class GroupInfoController {

    public ImageView avatar;//群头像
    public Text id;//群id
    public Text name;//群名字
    public Text sign;//群介绍
    public Text num;//群人数
    public Text ownNick;//群主昵称
    public ImageView ownImage;//群主头像
    public ListView manageList;//管理员列表
    public ListView OtherList;//普通成员列表
    public String Grade;//成员等级 1：群主 2：管理员 3：普通成员
    public Button out;
    public Button reset;
    public Button giveOut;
    public Button disband;

    public static ObservableList<ViewList> obslist5 = FXCollections.observableArrayList();
    public static ObservableList<ViewList> obslist6 = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        Controller.setGroupInfoController(this);

        //加载群资料页面所需要的全部信息
        OtherClientService.LoadGroupInfo(Commend.object, Commend.theUser);//传参是群名称和本人账号

        manageList.setItems(obslist5);
        manageList.setCellFactory(new Callback<ListView<ViewList>, ListCell<ViewList>>() {
            @Override
            public ListCell<ViewList> call(ListView<ViewList> param) {
                return new ListCell<ViewList>() {
                    protected void updateItem(ViewList item, boolean empty) {
                        //以下注释部分如果开放，群资料就无法查看两次
//                        if(Grade.equals("3") || Grade.equals("2")){
//                            setStyle("-fx-background-color: transparent");
//                        }
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            //导入好友列表图像
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            setOnMouseClicked(click -> {
                                if (click.getButton() == MouseButton.SECONDARY) {
                                    ContextMenu contextMenu = new ContextMenu();
                                    MenuItem item1 = new MenuItem("移出群聊");
                                    MenuItem item2 = new MenuItem("转让群主");
                                    MenuItem item3 = new MenuItem("取消管理员");

                                    item1.setOnAction(event -> {
                                        String clickName = item.getId();
                                        Commend.otherClientService.DeleteGroupMember(id.getText(), clickName);
                                        obslist5.removeIf(s -> s.getId().equals(clickName));
                                    });

                                    item2.setOnAction(event -> {
                                        String clickName = item.getId();
                                        //弹窗确认
                                        int n = JOptionPane.showConfirmDialog(null, "确认要将该群的管理身份转让给该成员吗？\n恁将失去群主身份变为管理员。", "Title", JOptionPane.YES_NO_OPTION);
                                        if (n == 0) {
//                                            System.out.println("YES");
                                            Commend.otherClientService.GiveOut(id.getText(), clickName, Commend.me.getNickName());
                                            JOptionPane.showMessageDialog(null, "重新进入本页面后身份生效。");
                                            groupinfostage.close();
                                        } else {
                                            //不做处理
//                                            System.out.println("NO");
                                        }
                                    });

                                    item3.setOnAction(event -> {
                                        String clickName = item.getId();
                                        int n = JOptionPane.showConfirmDialog(null, "是否要取消该用户的管理员身份？", "Title", JOptionPane.YES_NO_OPTION);
                                        if (n == 0) {
                                            ViewList data = new ViewList(clickName, item.getImage());
//                                            System.out.println(data);
                                            obslist5.removeIf(s -> s.getId().equals(clickName));
                                            obslist6.add(data);
                                            Commend.otherClientService.updateGrade(id.getText(), clickName, "3");
                                        } else {
                                        }
                                    });


                                    if (Grade.equals("1")) {
                                        //群主身份
                                        contextMenu.getItems().addAll(item1, item2, item3);
                                    } else if (Grade.equals("2")) {
                                        //管理员身份
                                        //管理员不能对管理员进行操作
                                    } else if (Grade.equals("3")) {
                                        //普通成员
                                        //普通成员啥也不能干
                                    }
                                    this.setContextMenu(contextMenu);
                                }

                            });
                            fxmlLoader.setLocation(getClass().getResource("../fxml/littleson.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友列表加载错误");
                                e.printStackTrace();
                            }
                            this.setGraphic(hBox);

                            //修改对应列表的内容
                            //获得子项的Controller-
                            LittleSonController littleSonController = fxmlLoader.getController();

                            //根据item书写id处内容
                            littleSonController.setId(item.getId());
                            //判断是否有自选头像
                            if (!item.getImage().equals("无")) {
                                littleSonController.image.setImage(new Image(item.getImage(), 100, 100, true, true, true));
                            }

                        } else {
                            this.setGraphic(null);
                        }
                    }
                };
            }
        });


        OtherList.setItems(obslist6);
        OtherList.setCellFactory(new Callback<ListView<ViewList>, ListCell<ViewList>>() {
            @Override
            public ListCell<ViewList> call(ListView<ViewList> param) {
                return new ListCell<ViewList>() {
                    protected void updateItem(ViewList item, boolean empty) {
                        //以下注释部分如果开放，群资料就无法查看两次
//                        if(Grade.equals("3")){
//                            setStyle("-fx-background-color: transparent");
//                        }
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            //导入好友列表图像
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            setOnMouseClicked(click -> {
                                if (click.getButton() == MouseButton.SECONDARY) {
                                    ContextMenu contextMenu = new ContextMenu();
                                    MenuItem item1 = new MenuItem("移出群聊");
                                    MenuItem item2 = new MenuItem("转让群主");
                                    MenuItem item3 = new MenuItem("设为管理员");

                                    item1.setOnAction(event -> {
                                        String clickName = item.getId();
                                        Commend.otherClientService.DeleteGroupMember(id.getText(), clickName);
                                        obslist6.removeIf(s -> s.getId().equals(clickName));
                                    });

                                    item2.setOnAction(event -> {
                                        String clickName = item.getId();
                                        //弹窗确认
                                        int n = JOptionPane.showConfirmDialog(null, "确认要将该群的管理身份转让给该成员吗？\n恁将失去群主身份变为管理员。", "Title", JOptionPane.YES_NO_OPTION);
                                        if (n == 0) {
//                                            System.out.println("YES");
                                            Commend.otherClientService.GiveOut(id.getText(), clickName, Commend.me.getNickName());
                                            JOptionPane.showMessageDialog(null, "重新进入本页面后身份生效。");
                                            groupinfostage.close();
                                        } else {
                                            //不做处理
//                                            System.out.println("NO");
                                        }
                                    });

                                    item3.setOnAction(event -> {
                                        String clickName = item.getId();
                                        int n = JOptionPane.showConfirmDialog(null, "是否要取消该用户的管理员身份？", "Title", JOptionPane.YES_NO_OPTION);
                                        if (n == 0) {
                                            ViewList data = new ViewList(item.getId(), item.getImage());
//                                            System.out.println(data);
                                            obslist6.removeIf(s -> s.getId().equals(clickName));
                                            obslist5.add(data);
                                            Commend.otherClientService.updateGrade(id.getText(), item.getId(), "2");
                                        } else {
                                        }
                                    });

                                    if (Grade.equals("1")) {
                                        //群主身份
                                        contextMenu.getItems().addAll(item1, item2, item3);
                                    } else if (Grade.equals("2")) {
                                        //管理员身份
                                        contextMenu.getItems().addAll(item1);
                                    } else if (Grade.equals("3")) {
                                        //普通成员
                                        //普通成员啥也不能干
                                    }
                                    this.setContextMenu(contextMenu);
                                }

                            });
                            fxmlLoader.setLocation(getClass().getResource("../fxml/littleson.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友列表加载错误");
                                e.printStackTrace();
                            }
                            this.setGraphic(hBox);

                            //修改对应列表的内容
                            //获得子项的Controller-
                            LittleSonController littleSonController = fxmlLoader.getController();
                            //根据item书写id处内容
                            littleSonController.setId(item.getId());
                            //判断是否有自选头像
                            if (!item.getImage().equals("无")) {
                                littleSonController.image.setImage(new Image(item.getImage(), 100, 100, true, true, true));
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
     * 打开页面时的初始化
     * @param groupInfo
     */
    public void init(GroupInfo groupInfo) {
        //清空原本的信息列表
        manageList.getItems().clear();
        OtherList.getItems().clear();
        if (!groupInfo.getAvatar().equals("无")) {
            avatar.setImage(new Image(groupInfo.getAvatar(), 100, 100, true, true, true));
        }
        id.setText(groupInfo.getId());
        name.setText(groupInfo.getName());
        sign.setText(groupInfo.getSign());
        num.setText(groupInfo.getNum());
        if (!groupInfo.getOwnImage().equals("无")) {
            ownImage.setImage(new Image(groupInfo.getOwnImage(), 100, 100, true, true, true));
        }
        ownNick.setText(groupInfo.getOwnNick());
        LoadMangeList(groupInfo.getManageList());
        LoadOtherList(groupInfo.getOtherList());
        //获取当前用户在此群的身份
        Commend.otherClientService.CheckGrade(id.getText(),Commend.me.getUserId());
    }

    /**
     * 加载普通成员列表
     * @param otherList
     */
    private void LoadOtherList(ArrayList<ViewList> otherList) {
        for (ViewList a : otherList) {
//            System.out.println(a);
            obslist6.add(a);
        }
    }

    /**
     * 加载管理员列表
     * @param manageList
     */
    private void LoadMangeList(ArrayList<ViewList> manageList) {
        for (ViewList a : manageList) {
//            System.out.println(a);
            obslist5.add(a);
        }
    }

    /**
     * 选择头像
     * @param mouseEvent
     */
    public void select(MouseEvent mouseEvent) {

        if (Grade.equals("1") || Grade.equals("2")) {
            FileChooser fileChooser = new FileChooser(); //文件选择窗口
            fileChooser.setTitle("Open Resource File");  //窗口名
            fileChooser.setInitialDirectory(new File("D:\\avatar2")); //设置默认打开的文件路径
            File file = fileChooser.showOpenDialog(new Stage());//显示屏幕选择窗口
            if (file != null) {
                String str = file.getAbsolutePath();  //sowOpenDialog 方法返回选择文件的相对路径
                String url = "file:" + str;
                avatar.setImage(new Image(url, 127, 188, true, true, true));
                //改变群组列表中该群组的头像
                for (ViewList data : Controller.getChatController().obslist) {
                    if (data.getId().equals(name)) {
                        data.setImage(url);
                    }
                }
                //传给服务器，更改数据库
                Commend.otherClientService.SetGroupAvatar(id.getText(), url);
            }
        }
    }

    /**
     * 退出群聊
     * @param mouseEvent
     */
    public void Out(MouseEvent mouseEvent) {
        System.out.println("我点击了退出群聊");
        //弹窗确认
        int n = JOptionPane.showConfirmDialog(null, "退出之后你将从群员列表中消失，且以后不再接收该群消息", "Title", JOptionPane.YES_NO_OPTION);
        if (n == 0) {
//            System.out.println("YES");
            //tip:如果是群主，要先选择转让者（要进行一个身份判断）
            if (Grade.equals("1")) {
                JOptionPane.showMessageDialog(null, "您是本群群主，请先将群转让后再退出群聊，\n或者您可以选择直接解散该群。");
            } else {
                //传给服务端，数据库操作，并告知群组和管理员
                Commend.otherClientService.DeleteGroupMember(id.getText(), Commend.me.getUserId());
                //删除好友列表中该群组
                obslist.removeIf(s -> s.getId().equals(name.getText()));
                Controller.getChatController().init.setVisible(true);
                groupinfostage.close();
            }
        } else {
            //不做处理
//            System.out.println("NO");
        }
    }

    /**
     * 修改群资料
     * @param mouseEvent
     * @throws Exception
     */
    public void Reset(MouseEvent mouseEvent) throws Exception {
//        System.out.println("我的身份是" + Grade);
        //只有群主或群管理才能修改资料（要进行一个身份判断）
        if (Grade.equals("1") || Grade.equals("2")) {
//            System.out.println("我点击了修改资料");
            User user = new User();
            user.setUserId(id.getText());
            user.setNickName(name.getText());
            user.setSign(sign.getText());
            Commend.user2 = user;
            ChangeGroupMain.start((new Stage()));
            groupinfostage.close();

            //传给服务端，数据库处理
        } else {
            JOptionPane.showMessageDialog(null, "您不是群主或管理员，不能修改群资料哦");
        }
    }


    /**
     * 解散群聊
     * @param mouseEvent
     */
    public void Disband(MouseEvent mouseEvent) {
//        System.out.println("我的身份是" + Grade);

        if (Grade.equals("1")) {
//            System.out.println("我点击了解散功能");
            //弹窗确认
            int n = JOptionPane.showConfirmDialog(null, "解散该群后无法恢复,\n聊天记录也将无法找回", "Title", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
//                System.out.println("YES");
                //告诉服务器，该群已被解散,更改数据库（删除群组，删除记录）
                Commend.otherClientService.DeleteGroup(id.getText());
                //删除好友列表中该群组
                obslist.removeIf(s -> s.getId().equals(name.getText()));
                Controller.getChatController().init.setVisible(true);
                groupinfostage.close();
            } else {
                //不做处理
//                System.out.println("NO");
            }
        } else {
            JOptionPane.showMessageDialog(null, "您不是群主，不能进行此操作哦");
        }

    }

}
