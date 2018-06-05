package vn.monpay.monchat.Controls;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by mobilechatsystem@gmail.com on 06/04/2018.
 */
public class MonChatWebViewClient extends WebViewClient {

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error)
    {
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon)
    {

    }
    @Override
    public void onPageFinished(WebView view, String url)
    {

    }
}
