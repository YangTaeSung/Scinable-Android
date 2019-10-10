package org.techtown.push;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,
                new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult result) {
                        String newToken = result.getToken();

                        println("Registered ID : " + newToken);
                    }
                });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instanceId = FirebaseInstanceId.getInstance().getId();
                println("Verified instance ID : " + instanceId);
            }
        });
    }

    public void println(String data) {
        textView2.append(data + "\n");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        println("onNewIntent called");

        if(intent != null) {
            processIntent(intent);
        }

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        String from = intent.getStringExtra("from");
        if (from == null) {
            println("from is null.");
            return ;
        }

        String contents = intent.getStringExtra("contents");
      //  String title = intent.getStringExtra("title");
       // String msg = intent.getStringExtra("msg");

        println("DATA : " + from + ", " + contents);
        textView.setText("received date from [" + from + "] : " + contents);


    }
}
