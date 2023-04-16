package service;

import chat.*;
import main.Init;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;
import static service.UserClientService.MD5Encode;

public class OtherClientService {

    /**
     * 向服务端请求好友用户列表
     */
    public void friendList(String state) {

        Request request = new Request();
        request.setMesType(RequestType.REQUEST_GET_FRIENDS);
        request.setData(Commend.theUser);
        request.setContent(state);
        Init.send(request);
    }

    /**
     * 无异常退出程序
     */
    public void goOut() {
        Commend.userClientService.DeleteOnlineUser(Commend.me.getUserId());
        User user = new User();
        Request<User> request = new Request<User>();
        request.setMesType(RequestType.REQUEST_CLIENT_EXIT);
        user.setUserId(Commend.theUser);
        System.out.println("退出User的id:" + Commend.theUser);
        request.setData(user);
        Init.send(request);
        Commend.ThreadRun = false;
        exit(0);
    }

    /**
     * 显示个人资料
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void privatemessage(String id, String state) {
        Request request = new Request();
        request.setMesType(RequestType.REQUEST_PRIVATE_MESSAGE);
        User user = new User();
        user.setUserId(id);
        user.setSomething(state);
        request.setData(user);
        Init.send(request);
    }

    /**
     * 请求修改密码的方法
     * @param vvcode
     * @param email
     * @param newpasword
     * @throws IOException
     */
    public void resetPassword(String vvcode, String email, String newpasword) throws IOException {
        boolean b = false;
        User user = new User();
        Request<User> request = new Request<User>();
        request.setMesType(RequestType.REQUEST2_RESET_PASSWORD);
        user.setVcode(vvcode);
        user.setemail(email);
        user.setPasswd(MD5Encode(newpasword, "utf8"));//一次加密
        request.setData(user);
        Init.send(request);
    }

    /**
     * 修改资料
     *
     * @param nickName
     * @param sex
     * @param birthday
     * @param sign
     */
    public void resetPrivateData(String nickName, String sex, String birthday, String sign) {
        User user = new User();
        user.setUserId(Commend.theUser);
        user.setNickName(nickName);
        user.setSex(sex);
        user.setBirth(birthday);
        user.setSign(sign);

        Request<User> request = new Request<>();
        request.setMesType(RequestType.REQUEST_RESET_PRIVATE_DATA);
        request.setData(user);
        Init.send(request);
    }

    /**
     * 发送加好友的请求
     *
     * @param message
     */
    public void addRequest(Message message) {
        Request<Message> request = new Request<Message>();
        request.setMesType(RequestType.REQUEST_ADD_NEW);
        request.setData(message);
        Init.send(request);
    }

    /**
     * 发送添加群组的请求
     *
     * @param message
     */
    public void addGroupRequest(Message message) {
        Request<Message> request = new Request<Message>();
        request.setMesType(RequestType.REQUEST_ADD_NEW_MEMBER);
        request.setData(message);
        Init.send(request);
    }

    /**
     * 同意添加好友或群组，在数据库添加关系
     *
     * @param sendId
     * @param getId
     */
    public void getnew(String sendId, String getId) {
        Request<Message> request = new Request<Message>();
        Message message = new Message();
        message.setSender(sendId);
        message.setGetter(getId);
        request.setMesType(RequestType.REQUEST_AGREE_ADD_NEW);
        request.setData(message);
        Init.send(request);
    }

    /**
     * 拒绝添加
     */
    public void refusegetnew(String getter, String sender) {
        Request<Message> request = new Request<>();
        Message message = new Message();
        message.setSender(sender);
        message.setGetter(getter);
        request.setMesType(RequestType.REQUEST_REFUSE_ADD_NEW);
        request.setData(message);
        Init.send(request);
    }

    /**
     * 查询用户或群组
     *
     * @param objectId
     */
    public void search(String objectId) {
        Request<String> request = new Request<>();
        request.setData(objectId);
        request.setContent(Commend.me.getUserId());
        request.setMesType(RequestType.REQUEST_SEARCH);
        Init.send(request);
    }

    /**
     * 加载好友申请列表
     *
     * @return
     */
    public void addList() {
        Request<String> request = new Request<String>();
        request.setMesType(RequestType.REQUEST_GET_ADD_LIST);
        request.setData(Commend.me.getUserId());
        Init.send(request);
    }

    /**
     * 加载群组申请列表
     */
    public void addGroupList() {
        Request<String> request = new Request<String>();
        request.setMesType(RequestType.REQUEST_GET_GROUP_ADD_LIST);
        request.setData(Commend.me.getUserId());
        Init.send(request);
    }

    /**
     * 删除好友
     *
     * @param user1
     * @param user2
     */
    public void Delete(String user1, String user2) {
        Request<Message> request = new Request<Message>();
        request.setMesType(RequestType.REQUEST_DELETE_FRIEND);
        Message message = new Message();
        message.setSender(user1);
        message.setGetter(user2);
        request.setData(message);
        Init.send(request);
    }

    /**
     * 设置头像
     *
     * @param Id
     * @param url
     */
    public void SetAvatar(String Id, String url) {
        Request<String> request = new Request<String>();
        request.setData(Id);
        request.setContent(url);
        request.setMesType(RequestType.REQUEST_SET_AVATAR);
        Init.send(request);
    }

    /**
     * 设置群头像
     *
     * @param Id
     * @param url
     */
    public void SetGroupAvatar(String Id, String url) {
        Request<String> request = new Request<String>();
        request.setData(Id);
        request.setContent(url);
        request.setMesType(RequestType.REQUEST_SET_GROUP_AVATAR);
        Init.send(request);
    }

    /**
     * 加载群组列表
     *
     * @param Id
     */
    public void GetGroupList(String Id) {
        Request<String> request = new Request<>();
        request.setMesType(RequestType.REQUEST_GET_GROUP_LIST);
        request.setData(Id);
        Init.send(request);
    }

    /**
     * 加载群资料页面需要的所有信息
     *
     * @param groupName
     */
    public static void LoadGroupInfo(String groupName, String userId) {
        Request request = new Request();
        request.setMesType(RequestType.REQUEST_LOAD_GROUP_INFO);
        request.setData(groupName);
        request.setContent(userId);
        Init.send(request);
    }

    /**
     * 加载群最基本的信息
     * @param groupId
     */
    public void LoadGroupBasicInfo(String groupId) {
        Request request = new Request();
        request.setMesType(RequestType.REQUEST_LOAD_GROUP_BASIC_INFO);
        request.setData(groupId);
        Init.send(request);
    }

    /**
     * 创建群组
     * @param list
     * @param groupId
     * @param groupName
     * @param ownId
     */
    public void creatGroup(ArrayList<String> list, String groupId, String groupName, String ownId) {
        Request request = new Request();
        request.setMesType(RequestType.REQUEST_CREAT_GROUP);
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setName(groupName);
        groupInfo.setId(groupId);
        groupInfo.setOwnNick(ownId);
        groupInfo.setContent(list);
        request.setData(groupInfo);
        Init.send(request);
    }

    /**
     * 登录前退出
     */
    public void Out() {
        Request<User> request = new Request<User>();
        request.setMesType(RequestType.REQUEST_EXIT);
        Init.send(request);
        Commend.ThreadRun = false;
        exit(0);
    }

    /**
     * 将某人从某群中移除
     * @param groupId
     * @param userName
     */
    public void DeleteGroupMember(String groupId, String userName) {
        Request request = new Request();
        request.setMesType(RequestType.REQUEST_DELETE_MEMBER);
        request.setData(groupId);
        request.setContent(userName);
        Init.send(request);
    }

    /**
     * 改变某人再某群的地位等级
     * @param groupId
     * @param userName
     * @param grade
     */
    public void updateGrade(String groupId, String userName, String grade) {
        Request request = new Request();
        Message message = new Message();
        message.setSender(userName);
        message.setGetter(groupId);
        message.setContent(grade);
        request.setMesType(RequestType.UPDATE_GRADE);
        request.setData(message);
        Init.send(request);
    }

    /**
     * 修改群组信息
     * @param name
     * @param sign
     * @param groupId
     */
    public void resetGroupData(String name, String sign, String groupId) {
        if (name != null || sign != null) {
            Request request = new Request();
            Message message = new Message();
            message.setSender(name);
            message.setGetter(sign);
            request.setMesType(RequestType.REQUEST_RESET_GROUP_DATA);
            request.setData(message);
            request.setContent(groupId);
            Init.send(request);
        } else { }
    }

    /**
     * 删除群组
     * @param groupId
     */
    public void DeleteGroup(String groupId) {
        Request request = new Request();
        request.setData(groupId);
        request.setMesType(RequestType.REQUEST_DELETE_GROUP);
        Init.send(request);
    }

    /**
     * 拒绝用户加入群组
     * @param userName
     * @param groupName
     */
    public void RefuseGroupNew(String userName, String groupName) {
        Request request = new Request();
        request.setData(userName);
        request.setContent(groupName);
        request.setMesType(RequestType.REQUEST_REFUSE_GROUP_NEW);
        Init.send(request);
    }

    /**
     * 同意用户加入群组
     * @param userName
     * @param groupNames
     */
    public void GetGroupNew(String userName, String groupNames) {
        Request request = new Request();
        request.setData(userName);
        request.setContent(groupNames);
        request.setMesType(RequestType.AGREE_REFUSE_GROUP_NEW);
        Init.send(request);
    }

    /**
     * 群主转让该群
     * @param groupId
     * @param newOwnName
     * @param oldOwnName
     */
    public void GiveOut(String groupId, String newOwnName, String oldOwnName) {
        Request request = new Request();
        Message message = new Message();
        message.setSender(oldOwnName);
        message.setGetter(newOwnName);
        message.setContent(groupId);
        request.setData(message);
        request.setMesType(RequestType.REQUEST_GIVE_OUT_GROUP);
        Init.send(request);

    }

    /**
     * 查找群成员在当前群组的身份
     * @param groupId
     * @param userId
     */
    public void CheckGrade(String groupId, String userId){
        Request request = new Request();
        request.setData(groupId);
        request.setContent(userId);
        request.setMesType(RequestType.REQUEST_CHECK_GRADE);
        Init.send(request);
    }

    public void CheckName(String name){
        Request request = new Request();
        request.setData(name);
        request.setMesType(RequestType.REQUEST_CHECK_NAME);
        Init.send(request);
    }
}
