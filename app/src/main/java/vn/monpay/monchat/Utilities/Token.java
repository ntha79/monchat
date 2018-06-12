package vn.monpay.monchat.Utilities;

import android.text.TextUtils;
import android.util.Base64;

import com.android.volley.AuthFailureError;

import java.util.HashMap;
import java.util.Map;

import vn.monpay.monchat.SessionInfo;

/**
 * Created by mobilechatsystem@gmail.com on 06/05/2018.
 */

public class Token {
    public final static String X_XSRF_TOKEN = "abcnnnd";

    public static Map<String, String> getHeaders(){
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("cache-control", "no-cache");
        headers.put("X-XSRF-TOKEN", Token.X_XSRF_TOKEN);
        headers.put("Cookie", "Idea-d0fd0ee6=4bf9a6bc-4dc4-4a06-aeba-6b1af486b719; _ga=GA1.1.855509286.1523590451; io=Xq7P4BWpCunPpKLLAAAF; XSRF-TOKEN="+Token.X_XSRF_TOKEN);
        headers.put("content-type", "application/json; charset=utf-8");
        // add headers <key,value>
        String credentials = "client"+":"+"secret";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
        headers.put("Authorization", auth);
        return headers;
    }

    public static Map<String, String> getHeaders_BearerToken(){

        Map<String,String> headers = new HashMap<String, String>();
        headers.put("cache-control", "no-cache");
        headers.put("X-XSRF-TOKEN", Token.X_XSRF_TOKEN);
        headers.put("Cookie", "Idea-d0fd0ee6=4bf9a6bc-4dc4-4a06-aeba-6b1af486b719; _ga=GA1.1.855509286.1523590451; io=Xq7P4BWpCunPpKLLAAAF; XSRF-TOKEN="+Token.X_XSRF_TOKEN);
        headers.put("content-type", "application/json; charset=utf-8");
        String auth = "Bearer " + SessionInfo.getAccess_token();
        headers.put("Authorization", auth);
        return headers;
    }
}
