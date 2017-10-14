# WebViewPlugin

360插件框架RePlugin的通用android WebView插件

#调用方法

    Intent intent = RePlugin.createIntent("com.github.lany192.plugin.webview", 
           "com.github.lany192.plugin.webview.WebActivity");
    intent.putExtra("key_url","http://www.baidu.com");
    intent.putExtra("key_title","百度");
    RePlugin.startActivity(context, intent);
    
#插件jar下载地址

    https://lany192.github.io/plugins/webview.jar
#用自带签名文件后的签名是
    MD5: AB:68:F3:2A:CF:AA:23:C2:DB:64:2C:4F:32:61:16:D3
    SHA1: 7E:C3:3D:84:C3:ED:21:54:EB:57:79:E2:77:39:A2:56:BC:33:AD:28
如果要签名传入RePlugin的签名是md5要去掉':'符号