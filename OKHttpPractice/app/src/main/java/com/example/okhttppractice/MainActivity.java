package com.example.okhttppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    // 예외처리 해줘야 됨.
    public void run(String requesturl,String server, String path, String protocol, String... param) {

        Request request = new Request.Builder()
                .url(requesturl)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override public void onFailure(Call call, IOException e) {

                e.printStackTrace();

            }

            @Override public void onResponse(Call call, Response response) {
                /*try (ResponseBody responseBody = response.body()) { // API Level 19 이상 가능
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    System.out.println(responseBody.string());
                }*/
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String src = "http://192.168.1.19/insert.php?name=Yang&country=Spanish";
                Uri uri = Uri.parse(src);
                String server = uri.getAuthority();
                String path = uri.getPath();
                String protocol = uri.getScheme();
                Set<String> args = uri.getQueryParameterNames();

                Log.d("server",server);
                Log.d("path",path);
                Log.d("protocol",protocol);
                for(String s : args) {

                    Log.d("args",s);
                    Log.d("value", uri.getQueryParameter(s));

                }


                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(protocol)
                        .host(server)
                        .addPathSegment(path)
                        .addQueryParameter("name", "Yang")
                        .addQueryParameter("country","Spanish")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

            }



        });



    }
}
