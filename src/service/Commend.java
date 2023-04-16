package service;

import chat.User;

public class Commend {
    public static String theUser;//用户id或邮箱
    public static User me;//我的资料加载
    public static String object;//操作对方id
    public static User user;//临时
    public static User user2;//临时2
    public static Boolean ThreadRun;//线程状态
    public static int state;//页面状态
    public static UserClientService userClientService = new UserClientService();
    public static OtherClientService otherClientService = new OtherClientService();
    public static MessageClientService messageClientService = new MessageClientService();
}
