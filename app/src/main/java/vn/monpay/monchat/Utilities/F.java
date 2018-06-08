package vn.monpay.monchat.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

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

//Cac ham phu tro

public class F {


    public static void CrashlyticsLogUser(String localClassName) {
        Crashlytics.setUserIdentifier("MONCHAT");
        Crashlytics.setUserEmail("mobilechatsystem@gmail.com");
        Crashlytics.setUserName(localClassName);
    }

    //++Layout Function ==================================
    public static void EndEditing(AppCompatActivity appCompatActivity)
    {
        View myIput = appCompatActivity.getCurrentFocus();
        if (myIput != null) {
            InputMethodManager imm = (InputMethodManager)appCompatActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(myIput.getWindowToken(), 0);
        }
    }
    public static void EndEditingView(AppCompatActivity appCompatActivity,View view)
    {
        InputMethodManager in = (InputMethodManager)appCompatActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    //--Layout Function ==================================

    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value) || value==null || value.equals("");
    }
    public static String NewLine() {
        return "\n";
        //return System.getProperty("line.separator");
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
    public static boolean BoolIsNull(Object value) {
        if (value == null)
            return false;
        try {
            boolean result = Boolean.parseBoolean(value.toString());
            return result;
        } catch (Exception ex) {
            // Handle parse error.
        }
        return false;
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

    public static double DoubleIsNull(Object value) {
        if (value == null)
            return 0;
        try {
            Double result = 0.0;
            if (value instanceof Double) {
                result = (Double) value;
                return result;
            }
            if (value instanceof Integer) {
                result = (Double) value;
                return result;
            }
            if (value instanceof Float) {
                result = (Double) value;
                return result;
            }
            String strValue = value.toString();
            if(isEmpty(strValue))
                return 0;
            strValue = strValue.replace(",","");
            result = Double.parseDouble(strValue);
            strValue = value.toString();
            if(!strValue.equals("0.0") && !strValue.equals("0") && !strValue.equals("0,0") && result==0)
            {
                result = Double.parseDouble(strValue);
            }
            return result;
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

    //++Bitmap function =======================
    public static Bitmap GetBitmapCricleFromBitmap(Bitmap bitmap, int ww, int hh, int borderColor) {
        try {

            if (bitmap == null)
                return bitmap;
            int imgw = bitmap.getWidth();
            int imgh = bitmap.getHeight();
            float scalew = (imgw < 1 ? 1 : (float) ((float) ww / (float) imgw));
            float scaleh = (imgh < 1 ? 1 : (float) ((float) hh / (float) imgh));
            scalew = Math.max(scalew, scaleh);
            //scalew = Math.max(scalew,1);
            imgw = Math.max((int) (imgw * scalew), 50);
            imgh = Math.max((int) (imgh * scalew), 50);

            bitmap = Bitmap.createScaledBitmap(bitmap, (int) imgw, (int) imgh, true);

            Bitmap output = Bitmap.createBitmap(ww, hh, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            Paint paint = new Paint();
            Rect rectSource = new Rect((imgw-ww)/2, (imgh-hh)/2, ww, hh);
            Rect rect = new Rect(0, 0, ww, hh);
            RectF rectF = new RectF(rect);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.RED);
            canvas.drawOval(rectF, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rectSource, rect, paint);

            rectF = new RectF(0.5f, 0.5f, ww - 1, hh - 1);
            paint.setColor(borderColor);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawOval(rectF, paint);

            bitmap.recycle();

            return output;
        } catch (Exception e) {
            return null;
        }
    }
    //--Bitmap function =======================

    //++Format================================
    public static String FormatDistance(Number value) {
        Number vl = value;
        if (vl.doubleValue() < 1000.0) {
            return "" + vl.intValue() + " m";
        } else {
            Integer ii = ((Integer) vl.intValue()) / 10;
            Double db = ii.doubleValue() / 100;
            return db.toString() + " km";
        }
    }
    //--Format================================
}
