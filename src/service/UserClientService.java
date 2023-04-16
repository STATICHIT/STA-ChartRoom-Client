package service;

import chat.Request;
import chat.RequestType;
import chat.User;
import main.Init;

import java.security.MessageDigest;

public class UserClientService {


    /**
     * 判断用户是否在线
     * @param IdOrEmile
     */
    public void CheckOnline(String IdOrEmile){
        Request request = new Request();
        request.setData(IdOrEmile);
        request.setMesType(RequestType.REQUEST_CHECK_ONLINE);
//        System.out.println(request);
        Init.send(request);
    }

    public void AddOnlineUser(String IdOrEmile) {
        Request request = new Request();
        request.setData(IdOrEmile);
        request.setMesType(RequestType.REQUEST_ADD_ONLINE);
//        System.out.println(request);
        Init.send(request);
    }

    public void DeleteOnlineUser(String userId){
        Request request = new Request();
        request.setData(userId);
        request.setMesType(RequestType.REQUEST_DELETE_ONLINE);
//        System.out.println(request);
        Init.send(request);
    }
    /**
     * 登录
     * 根据账号密码到服务器验证用户是否存在的方法
     */
    public void checkUser(String id, String password) {
        //创建对象
        User user = new User();
        user.setUserId(id);
        user.setPasswd(MD5Encode(password, "utf8"));//一次加密
        Commend.theUser = id;
        //泛型使用
        Request<User> request = new Request<User>();
        request.setData(user);
        request.setMesType(RequestType.REQUEST_LOGIN);
        Init.send(request);
    }

    /**
     * 注册
     * 根据验证码是否匹配判断能否注册成功
     */
    public void checkUser(String nickname, String password, String vcode, String email) {
        Request<User> request = new Request<User>();
        request.setMesType(RequestType.REQUEST_ENROLL);
        //创建新用户对象
        User user = new User();
        user.setNickName(nickname);
        user.setPasswd(MD5Encode(password, "utf8"));//客户端传到服务端 一次加密
        user.setVcode(vcode);
        user.setemail(email);
        request.setData(user);
        Init.send(request);
    }

    /**
     * 找回密码
     * 根据验证码到服务器验证能否修改密码成功
     */
    public void couldResetPassword(String vvcode, String email, String newPassWord) {
        User user = new User();
        Request<User> request = new Request<User>();
        request.setMesType(RequestType.REQUEST_COULD_RESET_PASSWORD);
        user.setVcode(vvcode);
        user.setemail(email);
        user.setPasswd(MD5Encode(newPassWord, "utf8"));//一次加密
        request.setData(user);
        Init.send(request);
    }

    /**
     * 请求发送验证码的方法(用于辅助注册和找回密码)
     */
    public void sendEmail(String email) {
        User user = new User();
        Request<User> request = new Request<User>();
        request.setMesType(RequestType.REQUEST_SEND_EMAIL);
        user.setemail(email);
        request.setData(user);
        Init.send(request);
    }

    /**
     * 邮箱查重
     * 存在返回true
     * 不存在返回false
     * @param email
     * @return
     */
    public void checkAgain(String email, String state) {
        Request<User> request = new Request<User>();
        User user = new User();
        user.setemail(email);
        user.setSomething(state);
        request.setData(user);
        request.setMesType(RequestType.REQUEST_CHECK_AGAIN);
        Init.send(request);
    }

    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * MD5加密
     *
     * @param origin      字符
     * @param charsetname 编码
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charsetname || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception e) {
        }
        return resultString;
    }

    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

}
