package vn.monpay.monchat;

import vn.monpay.monchat.Utilities.F;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */

public class SessionInfo
{
    private static String userName = "";
    private static String access_token = "";
    private static String refresh_token = "";

    //===========================
    public static boolean isLogin()
    {
        return !(F.isEmpty(userName)||F.isEmpty(access_token));
    }
    //===========================

    public static String getUserName(){ return userName;}
    public static void setUserName(String  value){ userName = value;}

    public static String getAccess_token(){ return access_token;}
    public static void setAccess_token(String  value){ access_token = value;}
    public static String getRefresh_token(){ return refresh_token;}
    public static void setRefresh_token(String  value){ refresh_token = value;}
}
