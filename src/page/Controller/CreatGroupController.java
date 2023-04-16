package page.Controller;

import Util.snowIDUtil.TestSnowFlake;
import chat.Controller;
import chat.ViewList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import service.Commend;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static page.PageMain.CreatGroupMain.creatgrouptage;

public class CreatGroupController {

    public ListView friendListView;
    public TextField groupName;

    public static ObservableList<ViewList> obslist4 = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        Controller.setCreatGroupController(this);
        Commend.otherClientService.friendList("2");

//        //这里，根本没有执行
//        //初始把每个子项设为未选择
//        if(obslist4.isEmpty()){
//            System.out.println("这个时候我是空的！");
//        }
//        for(ViewList v : obslist4){
//            System.out.println("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
//            v.setCheck(false);
//            System.out.println(v.getCheck());
//        }

        friendListView.setItems(obslist4);
        //将该listview设置为多选模式
        friendListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        friendListView.setCellFactory(new Callback<ListView<ViewList>, ListCell<ViewList>>() {
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

                            fxmlLoader.setLocation(getClass().getResource("../fxml/inviteson.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友列表加载错误");
                                e.printStackTrace();
                            }
                            this.setGraphic(hBox);
                            //获得子项的Controller-
                            InviteSonController inviteSonController = fxmlLoader.getController();

                            //点击之后判断是否选择
                            setOnMouseClicked(event -> {
                                if (inviteSonController.choose.isSelected()) {
//                                    System.out.println("我选择了这一项");
                                    item.setCheck(true);
                                } else {
//                                    System.out.println("我没选择这一项");
                                    item.setCheck(false);
                                }
                            });

                            //修改对应列表的内容
                            //根据item书写id处内容
                            inviteSonController.setId(item.getId());
                            //判断是否有自选头像
                            if (!item.getImage().equals("无")) {
                                inviteSonController.image.setImage(new Image(item.getImage(), 100, 100, true, true, true));
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
        //清空原本的信息列表
        friendListView.getItems().clear();
        for (ViewList a : friendList) {
            a.setCheck(false);
            obslist4.add(a);
//            System.out.println(a);
        }
    }

    /**
     * 创建群组
     * @param mouseEvent
     */
    public void CreatGroup(MouseEvent mouseEvent) {
        if (groupName.getText().equals("")) {
            System.out.println("群名不能为空");
            JOptionPane.showMessageDialog(null, "群名称不能为空，否则将无法创建群聊，请设置群名称。");
        } else {
            //获取多选选中的人的id
            //遍历子项选择状态
            ArrayList<String> list = new ArrayList<>();
            for (ViewList v : obslist4) {
                if (v.getCheck()) {
//                    System.out.println("选好了");
                    v.setCheck(false);
                    //把被选中的人的昵称放到集合里
                    list.add(v.getId());
                }
            }
            //计算选择人数
            int num = list.size();
            if (num <= 1) {
                JOptionPane.showMessageDialog(null, "请至少选择两位好友，否则无法创建群聊。");
            } else {
                //得到一个群号码
                String groupId = TestSnowFlake.getOneID();
                Controller.getChatController().obslist.add(new ViewList(groupName.getText(), groupId, "无"));
//            for(String name : list){
//                System.out.println(name);
//            }
                //传输给服务端，更改数据库
                Commend.otherClientService.creatGroup(list, groupId, groupName.getText(), Commend.me.getUserId());
                creatgrouptage.close();
            }

        }

    }

}