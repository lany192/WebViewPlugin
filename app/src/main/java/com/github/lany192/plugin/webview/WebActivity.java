package com.github.lany192.plugin.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class WebActivity extends AppCompatActivity {
    protected final String TAG = "WebActivity";
    private static final String key_url = "key_url";
    private static final String key_title = "key_title";
    private String mUrl;
    private String mTitle;

    public static void start(Context ctx, String title, String url) {
        Intent i = new Intent(ctx, WebActivity.class);
        i.putExtra(key_title, title);
        i.putExtra(key_url, url);
        ctx.startActivity(i);
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
