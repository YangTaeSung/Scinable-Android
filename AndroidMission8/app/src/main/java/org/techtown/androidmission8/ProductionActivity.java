package org.techtown.androidmission8;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductionActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_BACK1 = 401;
    public static final int REQUEST_CODE_BACK2 = 402;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("message", "result message is OK!");

                startActivityForResult(intent, REQUEST_CODE_BACK1);
                setResult(Activity.RESULT_OK, intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("message", "result message is OK!");

                startActivityForResult(intent, REQUEST_CODE_BACK2);
                setResult(Activity.RESULT_OK, intent);
            }
        });
    }
}

