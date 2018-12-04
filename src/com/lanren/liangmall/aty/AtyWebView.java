package com.lanren.liangmall.aty;

import java.util.ArrayList;

import com.lanren.liangmall.R;
import com.lanren.liangmall.R.id;
import com.lanren.liangmall.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class AtyWebView extends Activity {
	
	private WebView webView;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_webview);
		url = getIntent().getStringExtra("url");
		webView = (WebView)findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient() {
            //����shouldOverrideUrlLoading ����
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

		webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        //���� ����ģʽ
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // ���� DOM storage API ����
        webView.getSettings().setDomStorageEnabled(true);
		webView.loadUrl(url);
	}
}
