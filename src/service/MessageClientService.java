package service;

import chat.Message;
import chat.Request;
import chat.RequestType;
import main.Init;

import java.util.Date;

/**
 * 与发送消息相关的方法
 */
public class MessageClientService {

    /**
     * @param content  消息内容
     * @param sendId   发送用户id
     * @param getterId 接受用户id
     */
    public void sendMessageToOne(String content, String sendId, String getterId) {
        //构建message
        Message message = new Message(sendId, getterId, content, new Date().toString());
        Request<Message> request = new Request<Message>(RequestType.REQUEST_SEND_MESSAGE_TO_ONE, message);

//        System.out.println(sendId + "对" + getterId + "说" + content);
        Init.send(request);
    }

    public void sendMessageToGroup(String content, String sendId, String groupId) {
        //构建message
        Message message = new Message(sendId, groupId, content, new Date().toString());
        Request<Message> request = new Request<Message>(RequestType.REQUEST_SEND_MESSAGE_TO_GROUP, message);

//        System.out.println(sendId + "在" + groupId + "说" + content);
        Init.send(request);

    }

    /**
     * 加载消息列表
     *
     * @param user1
     * @param user2
     */
    public void GetMessageList(String user1, String user2) {
        Request<Message> request = new Request<Message>();
        Message message = new Message();
        if (Commend.state == 1) {
            request.setMesType(RequestType.REQUEST_GET_MESSAGE_LIST);
            message.setSender(user1);//我的账号
            message.setGetter(user2);//对方昵称
        } else if (Commend.state == 2) {
            request.setMesType(RequestType.REQUEST_GET_GROUP_MESSAGE_LIST);
            message.setGetter(user2);//群组昵称
        }
        request.setData(message);
        Init.send(request);

    }

    /**
     * 储存设置的常用语
     * @param userId
     * @param phrases
     */
    public void CommonPhrases(String userId, String phrases) {
        Request<String> request = new Request<>();
        request.setData(userId);
        request.setContent(phrases);
        request.setMesType(RequestType.REQUEST_COMMON_PHRASES);
        Init.send(request);
    }

    /**
     * 加载当前用户添加的常用语
     */
    public void LoadPhrases() {
        Request request = new Request();
        request.setData(Commend.me.getUserId());
        request.setMesType(RequestType.REQUEST_LOAD_COMMON_PHRASES);
        Init.send(request);
    }
}
