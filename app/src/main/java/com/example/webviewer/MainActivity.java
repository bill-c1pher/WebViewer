package com.example.webviewer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private EditText mUrlBarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.web_view);
        mUrlBarEditText = (EditText) findViewById(R.id.editText);

        mWebView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String url = uri.toString();
            mUrlBarEditText.setText(url);
            mWebView.loadUrl(url);
        }

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(false); //XSS vulnerability
    }

    public void displayWebpage(View view) {
       String url = mUrlBarEditText.getText().toString();
       mWebView.setWebViewClient(new WebViewClient());
       mWebView.loadUrl(url);

       WebSettings webSettings = mWebView.getSettings();
       webSettings.setJavaScriptEnabled(false); //XSS vulnerability
    }


    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }


}