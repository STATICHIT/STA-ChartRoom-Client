package chat;

/**
 * 表示请求类型
 */
public interface RequestType {

    String REQUEST_LOGIN = "1";//表示登录请求
    String REQUEST_ENROLL = "2";//表示注册请求
    String REQUEST_SEND_EMAIL = "3";//表示发邮件请求
    String REQUEST_GET_FRIENDS = "4";//要求返回在线用户列表
    String REQUEST_CLIENT_EXIT = "5";//客户端请求退出
    String REQUEST_CHECK_ONLINE = "6";//判断用户是否在线
    String REQUEST_COULD_RESET_PASSWORD = "7";//表示验证能否找回密码请求
    String REQUEST_SEND_MESSAGE_TO_ONE = "8";//表示发送私聊消息请求
    String REQUEST_PRIVATE_MESSAGE = "9";//请求返回个人信息
    String REQUEST_CHECK_AGAIN = "10";//邮箱查重请求
    String REQUEST2_RESET_PASSWORD = "11";//表示修改密码请求
    String REQUEST_RESET_PRIVATE_DATA = "12";//请求修改个人资料
    String REQUEST_ADD_NEW = "13";//加好友或加群的请求
    String REQUEST_AGREE_ADD_NEW = "14";//同意添加
    String REQUEST_SEARCH = "15";//查询用户或群组请求
    String REQUEST_GET_ADD_LIST = "16";//加载好友申请列表
    String REQUEST_REFUSE_ADD_NEW = "17";//拒绝申请
    String REQUEST_GET_MESSAGE_LIST = "18";//加载消息列表
    String REQUEST_DELETE_FRIEND = "19";//删除好友
    String REQUEST_SET_AVATAR = "20";//设置头像
    String REQUEST_GET_GROUP_LIST = "21";//加载群组列表
    String REQUEST_LOAD_GROUP_INFO = "22";//加载群资料页面全部数据
    String REQUEST_CREAT_GROUP = "23";//创建群聊
    String REQUEST_SET_GROUP_AVATAR = "24";//设置群头像
    String REQUEST_SEND_MESSAGE_TO_GROUP = "25";//群发消息
    String REQUEST_GET_GROUP_MESSAGE_LIST = "26";//加载群消息
    String REQUEST_EXIT = "27";//登录前退出
    String REQUEST_DELETE_MEMBER = "28";//某人退出群聊或被移除
    String UPDATE_GRADE = "29";//改变群员等级
    String REQUEST_RESET_GROUP_DATA = "30";//修改群资料
    String REQUEST_DELETE_GROUP = "31";//解散群组
    String REQUEST_LOAD_GROUP_BASIC_INFO = "32";//加载群组最基本的信息
    String REQUEST_ADD_NEW_MEMBER = "33";//加群申请
    String REQUEST_GET_GROUP_ADD_LIST = "34";//加载群组申请列表
    String REQUEST_REFUSE_GROUP_NEW = "35";//拒绝用户加入群组
    String AGREE_REFUSE_GROUP_NEW = "36";//同意用户加入群组
    String REQUEST_GIVE_OUT_GROUP = "37";//群主转让该群
    String REQUEST_CHECK_GRADE = "38";//查询身份
    String REQUEST_COMMON_PHRASES = "39";//添加常用语
    String REQUEST_LOAD_COMMON_PHRASES = "40";//加载常用语
    String REQUEST_ADD_ONLINE = "41";//增加在线状态
    String REQUEST_DELETE_ONLINE = "42";//删除在线状态
    String REQUEST_CHECK_NAME = "43";//查看名字是否重复
}
