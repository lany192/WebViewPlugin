# WebViewPlugin
init

#调用方法

    Intent intent = RePlugin.createIntent("com.github.lany192.plugin.webview", "com.github.lany192.plugin.webview.WebActivity");
    intent.putExtra("key_url","http://www.baidu.com");
    intent.putExtra("key_title","百度");
    RePlugin.startActivity(context, intent);
    
#插件jar下载地址
    https://lany192.github.io/plugins/webview.jar
