package com.github.lany192.plugin.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.qihoo360.replugin.loader.a.PluginFragmentActivity;

public class WebActivity extends PluginFragmentActivity {
    protected final String TAG = "WebActivity";
    private static final String key_url = "key_url";
    private static final String key_title = "key_title";
    private String mUrl;
    private String mTitle;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static void start(Context ctx, String title, String url) {
        Intent intent = new Intent(ctx, WebActivity.class);
        intent.putExtra(key_title, title);
        intent.putExtra(key_url, url);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(key_url);
            mTitle = bundle.getString(key_title);
        } else {
            Log.e(TAG, "onCreate:请检查传单参数");
            finish();
        }
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.web_progressbar);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.loadUrl(mUrl);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    public class MyWebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                if (mProgressBar.getVisibility() == View.GONE) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
