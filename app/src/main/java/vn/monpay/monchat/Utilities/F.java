package vn.monpay.monchat.Utilities;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */

public class F {
    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value) || value==null || value.equals("");
    }

    //++Json===================================
    public static JSONObject NewJSONObject(Object... paras) {
        JSONObject obj = new JSONObject();
        try {
            int nn = (int) (paras.length / 2);
            Integer i = 0;
            while (i < nn) {
                if(paras[2 * i + 1] == null)
                    obj.put(paras[2 * i].toString(), "null");
                else
                    obj.put(paras[2 * i].toString(), paras[2 * i + 1]);
                i = i + 1;
            }
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return obj;
    }
    public static String GetStringFromJSONObject(JSONObject object, String key) {
        if (TextUtils.isEmpty(key) || object == null)
            return "";
        String value = null;

        try {
            value = object.getString(key);
            if(TextUtils.isEmpty(value)||value.equals("null"))
                value ="";
        }
        catch (JSONException e) {
        }
        return value;
    }
    public static String GetStringFromJSONString(String objectString, String key) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(objectString))
            return "";
        JSONObject object = null;
        try {
            object = (JSONObject) new JSONObject(objectString);
        } catch (JSONException e) {
        }
        if (object == null)
            return "";
        String value = null;
        try {
            value = object.getString(key);
            if(TextUtils.isEmpty(value)||value.equals("null"))
                value ="";
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return value;
    }
    //--Json===================================

    public static String StringIsNull(Object value) {
        if (value == null)
            return "";
        try {
            String result = value.toString();
            if (result.equals("null"))
                result = "";
            return result;
        } catch (Exception ex) {
            // Handle parse error.
        }
        return "";
    }
    public static Integer IntIsNull(Object value) {
        if (value == null)
            return 0;
        try {
            int result = 0;
            if (value instanceof Integer) {
                result = (int) value;
                return result;
            }
            if (value instanceof Double) {
                result =((Double) value).intValue();
                return result;
            }
            if (value instanceof Float) {
                result =((Float) value).intValue();
                return result;
            }
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException nfe) {
            // Handle parse error.
        } catch (Exception nfe) {
            return 0;
        }
        return 0;
    }


    //++Toast==================================
    public static void ToastShort(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void ToastLong(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    //--Toast==================================


}
