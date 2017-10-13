package com.github.lany192.plugin.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qihoo360.replugin.loader.a.PluginAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends PluginAppCompatActivity {
    private final String TAG = "WebViewPlugin";
    @BindView(R.id.web_toolbar_title_text)
    TextView mTitleText;
    @BindView(R.id.web_toolbar_close_btn)
    ImageButton mCloseBtn;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.web_progressbar)
    ProgressBar mProgressBar;

    private String mUrl;
    private String mTitle;
    private String mHeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString("key_url");
            mTitle = bundle.getString("key_title");
            mHeaders = bundle.getString("key_headers");
        } else {
            Log.e(TAG, "请检查传单参数");
            finish();
        }
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.clearCache(true);

        final WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setLoadWithOverviewMode(true);// 自动适应手机屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        if (TextUtils.isEmpty(mTitle)) {
            mTitleText.setText("");
        } else {
            mTitleText.setText(mTitle);
        }
        mWebView.setWebViewClient(webViewClient);
        mWebView.loadUrl(mUrl);
    }

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mWebView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mWebView.loadUrl("about:blank");
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "url:" + url);
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @OnClick(R.id.web_toolbar_back_btn)
    public void onBackClicked() {
        onCustomBackPressed();
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mProgressBar != null) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE)
                        mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private void onCustomBackPressed() {
        if (mWebView.canGoBack()) {
            mCloseBtn.setVisibility(View.VISIBLE);
            mWebView.goBack();
            return;
        }
        onBackPressed();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onCustomBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.web_toolbar_close_btn)
    public void onCloseClicked() {
        finish();
    }
}
