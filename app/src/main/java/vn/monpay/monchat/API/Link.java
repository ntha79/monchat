package vn.monpay.monchat.API;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
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

import vn.monpay.monchat.Utilities.F;

/**
 * Created by mobilechatsystem@gmail.com on 06/03/2018.
 */

public class Link {
    public static final String link_public = "http://35.198.246.74:8080/";//http://35.198.245.187:8080/";//auth/login

    public static final String link_auth_login = "auth/login";
    public static final String link_uaa_api_users = "uaa/api/users";

    public static String getLink(String function)
    {
        return link_public+function;
    }

    //++http POST==============================
    private static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {

                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                        // not implemented
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                        // not implemented
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                }
        };

        try {

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }

            });
            SSLContext sc = SSLContext.getInstance("SSL");//TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            javax.net.ssl.SSLSocketFactory factory = sc.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(factory);
            SSLSocketFactory mSchemeSocketFactory=org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
            mSchemeSocketFactory.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static HttpClient getNewHttpClient() {
        return new DefaultHttpClient();
    }
    public static String httpPost(String xmlUrl, String accessToken, JSONObject paraJSONObject) {
        if(TextUtils.isEmpty(xmlUrl))
            return "";
        String xmlString = null;
        try {
            disableSSLCertificateChecking();
            HttpClient httpClient = getNewHttpClient();
            HttpPost httppost = new HttpPost(xmlUrl);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");
            if (!TextUtils.isEmpty(accessToken) && accessToken != null && !accessToken.equals("")) {
                httppost.setHeader("Authorization", "Bearer " + accessToken);
            }
            if (paraJSONObject != null) {
                String json = paraJSONObject.toString();
                if(!TextUtils.isEmpty(json))
                    json = json.replace(":\"null\"",":null");
                StringEntity entity = new StringEntity(json, "UTF-8");//HTTP.UTF_8
                entity.setContentType("application/json");
                entity.setContentEncoding("UTF-8");
                httppost.setEntity(entity);
            }

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            xmlString = httpClient.execute(httppost, responseHandler);
        } catch (UnsupportedEncodingException e) {
            xmlString = "";
            ////e.printStackTrace();
        } catch (ClientProtocolException e) {
            xmlString ="";
            ////e.printStackTrace();
        } catch (IOException e) {
            xmlString = "";
            //e.printStackTrace();
        }
        return xmlString;
    }
    public static String httpGet(String xmlUrl, String accessToken) {
        if(TextUtils.isEmpty(xmlUrl))
            return "";
        String xmlString = null;
        try {
            disableSSLCertificateChecking();
            HttpClient httpClient = getNewHttpClient();
            HttpGet httppost = new HttpGet(xmlUrl);
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");
            if (!TextUtils.isEmpty(accessToken) && accessToken != null && !accessToken.equals("")) {
                httppost.setHeader("Authorization", "Bearer " + accessToken);
            }


            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            xmlString = httpClient.execute(httppost, responseHandler);
        } catch (UnsupportedEncodingException e) {
            xmlString = "";
            ////e.printStackTrace();
        } catch (ClientProtocolException e) {
            xmlString ="";
            ////e.printStackTrace();
        } catch (IOException e) {
            xmlString = "";
            //e.printStackTrace();
        }
        return xmlString;
    }
    public static void LogResult(String functionID,String putData,String resultData, String fromIP, String deviceId)
    {
        JSONObject jsonObject = F.NewJSONObject("functionID",functionID,"putData",putData,"resultData",resultData,"fromIP",fromIP,"deviceId",deviceId);
        Log.d(functionID,jsonObject.toString());
    }
    //--http POST==============================
}
