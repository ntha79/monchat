package vn.monpay.monchat;

import android.content.pm.ActivityInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import vn.monpay.monchat.Controls.MonChatWebViewClient;

/**
 * Created by mobilechatsystem@gmail.com on 06/04/2018.
 */

public class AboutActivity extends AppCompatActivity {

    private WebView webView_about_content;
    private FloatingActionButton fab_about_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        webView_about_content = (WebView)findViewById(R.id.webView_about_content);

        fab_about_back = (FloatingActionButton)findViewById(R.id.fab_about_back);
        fab_about_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String link_about ="https://monpay.vn/about";

        webView_about_content.setWebViewClient(new MonChatWebViewClient());
        webView_about_content.getSettings().setJavaScriptEnabled(true);
        webView_about_content.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView_about_content.setVisibility(View.VISIBLE);
        webView_about_content.loadUrl(link_about);

        webView_about_content.getSettings().setBuiltInZoomControls(false);
        webView_about_content.getSettings().setUseWideViewPort(true);
        webView_about_content.getSettings().setLoadWithOverviewMode(true);
        webView_about_content.setInitialScale(1);
    }
}
