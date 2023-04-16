package page.Controller;

import chat.AddList;
import chat.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import service.Commend;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddController {


    public static ObservableList<AddList> obslist3 = FXCollections.observableArrayList();
    public static ObservableList<AddList> obslist7 = FXCollections.observableArrayList();
    public ListView addListView;
    public ListView addGroupList;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
        //打开界面时把当前界面的Controller赋给对应静态Controller
        Controller.setAddController(this);
        Commend.otherClientService.addList();
        //加载群申请消息
        Commend.otherClientService.addGroupList();

        addListView.setItems(obslist3);

        addListView.setCellFactory(new Callback<ListView<AddList>, ListCell<AddList>>() {
            @Override
            public ListCell<AddList> call(ListView<AddList> param) {
                return new ListCell<AddList>() {
                    protected void updateItem(AddList item, boolean empty) {
                        setStyle("-fx-background-color: white");
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            //导入好友列表图像
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/addSon.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友消息加载错误");
                                e.printStackTrace();
                            }
                            this.setGraphic(hBox);

                            //修改对应列表的内容
                            //获得子项的Controller
                            AddSonController addSonController = fxmlLoader.getController();
                            //根据item书写id处内容
                            addSonController.setNickname(item.getNickName());
                            addSonController.setId(item.getId());
                            addSonController.setAge(item.getAge());
                            addSonController.setSex(item.getSex());
                            addSonController.setText(item.getText());
                            addSonController.setTime(item.getTime());
                            //判断是否有自选头像
                            if (!item.getImage().equals("无")) {
                                addSonController.image.setImage(new Image(item.getImage(), 100, 100, true, true, true));
                            }
                        } else {
                            this.setGraphic(null);
                        }
                    }
                };
            }
        });


        addGroupList.setItems(obslist7);

        addGroupList.setCellFactory(new Callback<ListView<AddList>, ListCell<AddList>>() {
            @Override
            public ListCell<AddList> call(ListView<AddList> param) {
                return new ListCell<AddList>() {
                    protected void updateItem(AddList item, boolean empty) {
                        setStyle("-fx-background-color: white");
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox();
                            //导入好友列表图像
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../fxml/addgroupson.fxml"));
                            try {
                                hBox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友消息加载错误");
                                e.printStackTrace();
                            }
                            this.setGraphic(hBox);

                            //修改对应列表的内容
                            //获得子项的Controller
                            AddGroupSonController addGroupSonController = fxmlLoader.getController();
                            //根据item书写id处内容
                            addGroupSonController.setNickname(item.getNickName());//用户昵称
                            addGroupSonController.setAge(item.getAge());//用户生日
                            addGroupSonController.setSex(item.getSex());//用户性别
                            addGroupSonController.setText(item.getText());//验证消息
                            addGroupSonController.setTime(item.getTime());//请求时间
                            addGroupSonController.setGroupName(item.getContent());//群组名字

                            //判断是否有自选头像
                            if (!item.getImage().equals("无")) {
                                addGroupSonController.image.setImage(new Image(item.getImage(), 100, 100, true, true, true));
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
     * 加载好友申请列表
     * @param list
     */
    public void LoadAddList(ArrayList<AddList> list) {
        //清空原本的申请列表
        addListView.getItems().clear();

        for (AddList a : list) {
//            System.out.println(a);
            obslist3.add(a);
        }
    }

    /**
     * 加载群组申请列表
     * @param list
     */
    public void LoadAddGroupList(ArrayList<AddList> list) {
        //清空原本的申请列表
        addGroupList.getItems().clear();

        for (AddList a : list) {
//            System.out.println(a);
            obslist7.add(a);
        }
    }

}
