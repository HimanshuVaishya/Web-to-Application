package com.tech.webtoappconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tech.webtoappconverter.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.detail.setVisibility(View.VISIBLE);
                binding.hide.setVisibility(View.VISIBLE);
            }
        });

        binding.hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.detail.setVisibility(View.GONE);
                binding.hide.setVisibility(View.GONE);
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = binding.txtUrl.getText().toString();
                binding.webView.loadUrl(url);
            }
        });

        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.progressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressbar.setVisibility(View.GONE);
            }
        });

        binding.webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if (event.getAction()== KeyEvent.ACTION_DOWN){
                    switch (i){
                        case KeyEvent.KEYCODE_BACK:
                            if (binding.webView.canGoBack()){
                                binding.webView.goBack();
                            }
                    }
                }
                return true;
            }
        });

    }
}