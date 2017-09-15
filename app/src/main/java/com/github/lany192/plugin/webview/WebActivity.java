package com.github.lany192.plugin.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qihoo360.replugin.loader.a.PluginFragmentActivity;

public class WebActivity extends PluginFragmentActivity {
    protected final String TAG = "WebActivity";
    private static final String key_url = "key_url";
    private static final String key_title = "key_title";
    private String mUrl;
    private String mTitle;

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
    }
}
