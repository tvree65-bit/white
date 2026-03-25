package com.mycompany.white;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        webView.setVisibility(View.INVISIBLE);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setLoadsImagesAutomatically(true);
        webView.setLayerType(WebView.LAYER_TYPE_HARDWARE, null);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setVisibility(View.VISIBLE);
            }
        });

        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        // التحقق إذا كان WebView يمكنه الرجوع للخلف
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            // إذا كان في الصفحة الرئيسية، عرض موديل تأكيد الخروج
            showExitDialog();
        }
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("تأكيد الخروج");
        builder.setMessage("هل تريد الخروج من التطبيق؟");
        
        // زر نعم
        builder.setPositiveButton("نعم", (dialog, which) -> {
            finishAffinity(); // إغلاق التطبيق بالكامل
        });
        
        // زر لا
        builder.setNegativeButton("لا", (dialog, which) -> {
            dialog.dismiss(); // إغلاق الموديل فقط
        });
        
        // إظهار الموديل
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}