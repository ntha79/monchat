package vn.monpay.monchat;

import org.json.JSONException;
import org.json.JSONObject;

import vn.monpay.monchat.Utilities.F;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */

public class SessionInfo
{
    public static int CameraZoom = 16;

    private static  String userName = "";
    private static  String access_token = "";			//Y nghia:
    private static  String token_type = "";			//Y nghia:
    private static  String refresh_token = "";			//Y nghia:
    private static  int expires_in = 0;			//Y nghia:
    private static  String scope = "";			//Y nghia:
    private static  int iat = 0;			//Y nghia:
    private static  String jti = "";			//Y nghia:

    private static  int ownerId = 6;			//test
    private static  String fullName = "Mobilechat";			//test

    //===========================
    public static boolean isLogin()
    {
        return !(F.isEmpty(userName)||F.isEmpty(access_token));
    }
    //===========================
    public static void InitLogout()
    {
        access_token = "";
        token_type = "";
        refresh_token = "";
        expires_in = 0;
        scope = "";
        iat = 0;
        jti = "";
    }
    public static void InitLoginValue(JSONObject obj)
    {
        access_token = "";
        token_type = "";
        refresh_token = "";
        expires_in = 0;
        scope = "";
        iat = 0;
        jti = "";
        try {

            access_token = F.StringIsNull(obj.getString("access_token"));
            token_type = F.StringIsNull(obj.getString("token_type"));
            refresh_token = F.StringIsNull(obj.getString("refresh_token"));
            expires_in = F.IntIsNull(obj.getInt("expires_in"));
            scope = F.StringIsNull(obj.getString("scope"));
            iat = F.IntIsNull(obj.getInt("iat"));
            jti = F.StringIsNull(obj.getString("jti"));
        } catch (JSONException e) {}

    }
    public static String getUserName(){ return userName;}
    public static void setUserName(String  value){ userName = value;}



    public static String getAccess_token(){ return access_token;}
    public static void setAccess_token(String  value){ access_token = value;}
    public static String getRefresh_token(){ return refresh_token;}
    public static void setRefresh_token(String  value){ refresh_token = value;}
    public static  String getToken_type(){ return token_type;}
    public static  void setToken_type(String  value){ token_type = value;}
    public static  int getExpires_in(){ return expires_in;}
    public static  String getExpires_inString(){ return "" + expires_in;}
    public static  void setExpires_in(int  value){ expires_in = value;}
    public static  String getScope(){ return scope;}
    public static  void setScope(String  value){ scope = value;}
    public static  int getIat(){ return iat;}
    public static  String getIatString(){ return "" + iat;}
    public static  void setIat(int  value){ iat = value;}
    public static  String getJti(){ return jti;}
    public static  void setJti(String  value){ jti = value;}


    public static int getOwnerId(){ return ownerId;}
    public static void setOwnerId(int  value){ ownerId = value;}

    public static String getFullName(){ return fullName;}
    public static void setFullName(String  value){ fullName = value;}

}
