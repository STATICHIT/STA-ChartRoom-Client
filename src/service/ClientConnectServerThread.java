package service;

import chat.*;
import javafx.application.Platform;
import main.Init;

import javax.swing.*;
import java.net.Socket;
import java.util.ArrayList;

import static page.Controller.ChatController.obslist;
import static service.Commend.ThreadRun;

/**
 * 专门用于接受消息的线程
 */
public class ClientConnectServerThread extends Thread {
    //该线程需要持有Socket
    private final Socket socket;

    //构造器可以接收一个Socket对象
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        //因为Thread需要再后台和服务器通信，因此我们while循环
        while (ThreadRun) {
            System.out.println("客户端线程，等待从客户端读取消息");
            Response rs = Init.receive();
            switch (rs.getResType()) {
                //登录成功
                case "1": {
                    Commend.me = (User) rs.getData();
                    Commend.state = 1;
                    Platform.runLater(() -> {
                        try {
                            Controller.getLoginController().LoginOK(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //登录失败
                case "2": {
                    Platform.runLater(() -> {
                        try {
                            Controller.getLoginController().LoginOK(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //注册成功
                case "3": {
                    //得到服务器传回来的生成的用户id
                    String id = rs.getContent();
                    //用一个自定义弹框告诉用户生成的id
                    JOptionPane.showMessageDialog(null, "注册成功！您的ID是:" + id,
                            "StaChatRoom", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\page\\picture\\happy.png"));
                    Platform.runLater(() -> {
                        try {
                            Controller.getEnrollController().EnrollOk(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //注册失败
                case "4": {
                    Platform.runLater(() -> {
                        try {
                            Controller.getEnrollController().EnrollOk(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //接收到消息
                case "5": {
                    Message message = (Message) rs.getData();
                    Controller.getChatController().GetNewMessage(message.getSender(), message.getContent(), message.getSendTime(), message.getGetter(), 1, "-");
                }
                break;
                //返回好友列表
                case "6": {
                    ArrayList<ViewList> friendList = (ArrayList<ViewList>) rs.getData();
//                    System.out.println("类型标识是" + rs.getContent());
                    if (rs.getContent().equals("1")) {
                        Platform.runLater(() -> {
                            Controller.getChatController().LoadFriendList(friendList);
                        });
                    } else if (rs.getContent().equals("2")) {
                        Platform.runLater(() -> {
                            Controller.getCreatGroupController().LoadFriendList(friendList);
                        });
                    }
                }
                break;
                //找回密码成功
                case "7": {
                    JOptionPane.showMessageDialog(null, "修改密码成功！",
                            "StaChatRoom", JOptionPane.INFORMATION_MESSAGE, null);
                    Platform.runLater(() -> {
                        try {
                            Controller.getRefindController().RefindOk(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //找回密码失败
                case "8": {
                    Platform.runLater(() -> {
                        try {
                            Controller.getRefindController().RefindOk(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //返回个人消息成功
                case "9": {
                    User user2 = (User) rs.getData();
//                    System.out.println(user2);//测试
                    if (user2.getSomething().equals("1")) {
//                        System.out.println("我进入的是个人信息页面");//测试
                        Platform.runLater(() -> {
                            Controller.getPrivateDataController().init(user2);
                        });
                    } else if (user2.getSomething().equals("2")) {
//                        System.out.println("我进入的是陌生人信息添加页面");//测试
                        Platform.runLater(() -> {
                            Controller.getAddInfoController().init(user2);
                        });
                    } else if (user2.getSomething().equals("3")) {
//                        System.out.println("我进入的是添加新好友页面");//测试
                        //将该好友加入好友列表
                        Platform.runLater(() -> {
                            obslist.add(new ViewList(user2.getNickName(), user2.getSign(), user2.getAvatar()));
                        });
                    } else if (user2.getSomething().equals("4")) {
//                        System.out.println("我进入的是好友信息页面");//测试
                        Platform.runLater(() -> {
                            Controller.getFriendDataController().init(user2);
                        });
                    }
                }
                break;
                //返回在线状态
                case "10": {
                    Boolean b = (Boolean)rs.getData();
//                    System.out.println(b);
                    Platform.runLater(() -> {
                        Controller.getLoginController().CouldLogin(b);
                    });
                }
                break;
                //返回成员等级
                case "11":{
                    String grade = rs.getContent();
                    Platform.runLater(() -> {
                        Controller.getGroupInfoController().Grade = grade;
                    });
                }
                break;
                //返回自定义常用语
                case "12":{
                    ArrayList<String> list = (ArrayList<String>)rs.getData();
                    Platform.runLater(() -> {
                        Controller.getChatController().LoadCommonPhrases(list);
                    });
                }
                break;
                //返回在线用户失败
                case "13": {
                    Platform.runLater(() -> {
                        Controller.getChatController().LoadFriendList(null);
                    });
                }
                break;
                //表示邮箱存在
                case "14": {
                    if (rs.getData().equals("Enroll")) {
                        Platform.runLater(() -> {
                            Controller.getEnrollController().EmailExist(true);
                        });
                    } else if (rs.getData().equals("Refind")) {
                        Platform.runLater(() -> {
                            Controller.getRefindController().EmailOk(true);
                        });
                    }
                }
                break;
                //表示邮箱不存在
                case "15": {
                    if (rs.getData().equals("Enroll")) {
                        Platform.runLater(() -> {
                            Controller.getEnrollController().EmailExist(false);
                        });
                    } else if (rs.getData().equals("Refind")) {
                        Platform.runLater(() -> {
                            Controller.getRefindController().EmailOk(false);
                        });
                    }
                }
                break;
                //修改密码成功
                case  "18": {
                    Platform.runLater(() -> {
                        try {
                            Controller.getResetPasswordController().ResetOk(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    //验证失败
//                    System.out.println("验证码错误！");
                }
                break;
                //查询对象存在
                case "19": {
                    Platform.runLater(() -> {
                        try {
                            Controller.getSearchController().IdExist(true, rs.getContent());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //查询对象不存在
                case "20": {
                    Platform.runLater(() -> {
                        try {
                            Controller.getSearchController().IdExist(false, rs.getContent());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                break;
                //返回申请列表成功
                case "21": {
                    ArrayList<AddList> addList = (ArrayList<AddList>) rs.getData();
                    Platform.runLater(() -> {
                        Controller.getAddController().LoadAddList(addList);
                    });
                }
                break;
                //对方已同意加你个为好友
                case "23": {
                    User user = (User) rs.getData();
                    Platform.runLater(() -> {
                        obslist.add(new ViewList(user.getNickName(), user.getSign(), user.getAvatar()));
                    });
                }
                break;
                //返回聊天信息列表
                case "24": {
                    ArrayList messageList = (ArrayList) rs.getData();
                    Platform.runLater(() -> {
                        Controller.getChatController().LoadMessageList(messageList);
                    });
                }
                break;
                //你被删了！
                case "25": {
                    User user = (User) rs.getData();//这是删掉你的人的信息
                    JOptionPane.showMessageDialog(null, user.getNickName() + "把你删了！");
                    //稍后处理
                    Platform.runLater(() -> {
                        obslist.removeIf(s -> s.getId().equals(user.getNickName()));
                    });
                    Controller.getChatController().init.setVisible(true);
                }
                break;
                //返回群组列表
                case "26": {
                    ArrayList<ViewList> groupList = (ArrayList<ViewList>) rs.getData();
                    Platform.runLater(() -> {
                        Controller.getChatController().LoadGroupList(groupList);
                    });
                }
                break;
                //被添加进了群组
                case "27": {
                    //如果正在群组页面，将新群组加入列表
                    if (Commend.state == 2) {
                        //提取群组信息
                        GroupInfo groupInfo = new GroupInfo();

                        ViewList data = new ViewList(groupInfo.getName(), groupInfo.getId());
                        Platform.runLater(() -> {
                            Controller.getChatController().obslist.add(data);
                        });
                    }
                }
                break;
                //返回群组资料
                case "28": {
                    Platform.runLater(() -> {
                        Controller.getGroupInfoController().init((GroupInfo) rs.getData());
                    });
                }
                break;
                //接收到群消息
                case "29": {
                    Message message = (Message) rs.getData();
                    String groupName = rs.getContent();
                    Controller.getChatController().GetNewMessage(message.getSender(), message.getContent(), message.getSendTime(), message.getGetter(), 2, groupName);
                }
                break;
                //返回群组最基本的信息
                case "30": {
                    Platform.runLater(() -> {
                        Controller.getAddGroupInfoController().init((GroupInfo) rs.getData());
                    });
                }
                break;
                //返回申请列表
                case "31": {
                    ArrayList<AddList> addList = (ArrayList<AddList>) rs.getData();
                    Platform.runLater(() -> {
                        Controller.getAddController().LoadAddGroupList(addList);
                    });
                }
                break;
                //昵称查重结果
                case "32":{
                    Boolean b = (Boolean) rs.getData();
                    System.out.println("查询结果是"+b);
                    if(b){
                        JOptionPane.showMessageDialog(null, "该昵称已经存在，请重新设置！");
                    }
                }break;
            }
        }
    }
}

