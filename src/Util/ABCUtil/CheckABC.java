package Util.ABCUtil;

public class CheckABC {
    public static Boolean Check(String password){
        Boolean b = false;
        for(int i=0;i<password.length();i++){

            if((password.charAt(i)>='a' && password.charAt(i)<='z') || (password.charAt(i)>='A' && password.charAt(i)<='Z') ){
                b = true;
                break;
            }
        }
        return b;
    }
}
