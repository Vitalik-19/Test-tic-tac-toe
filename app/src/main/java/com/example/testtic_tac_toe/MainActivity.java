package com.example.testtic_tac_toe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testtic_tac_toe.api.AppService;
import com.example.testtic_tac_toe.api.model.FlagDTO;

import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.http.HttpHeaders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View fragment = findViewById(R.id.fragment);
        final WebView webView = findViewById(R.id.webView);
        final TextView textView = findViewById(R.id.text_view);

        CookieManager.getInstance().setAcceptCookie(true);
        webView.loadUrl("https://html5test.com/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setVisibility(View.GONE);
        fragment.setVisibility(View.GONE);


        AppService.getInstance()
                .getDataApi()
                .getFlag()
                .enqueue(new Callback<FlagDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<FlagDTO> call, @NonNull Response<FlagDTO> response) {
                        FlagDTO flag = response.body();

                        if (flag.getFlag()) {
                            webView.setVisibility(View.VISIBLE);
                        }else{
                           fragment.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<FlagDTO> call, @NonNull Throwable t) {
                        textView.setText("Error");
                        t.printStackTrace();
                    }
                });
    }

//    enum Status {ERROR, LOADING, SUCCESS}
}