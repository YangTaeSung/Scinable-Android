package org.techtown.androidmission8;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CUSTOMER = 201;
    public static final int REQUEST_CODE_SALES = 202;
    public static final int REQUEST_CODE_PRODUCTION = 203;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //process received intent
        Intent receivedIntent = getIntent();
        String username = receivedIntent.getStringExtra("username");
        String password = receivedIntent.getStringExtra("password");

        Toast.makeText(this,"username " + username + ", password : " + password,
                Toast.LENGTH_LONG).show();

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                intent.putExtra("titleMsg", "Customer display");

                startActivityForResult(intent, REQUEST_CODE_CUSTOMER);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SalesActivity.class);
                intent.putExtra("titleMsg","Sales display");

                startActivityForResult(intent,REQUEST_CODE_SALES);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductionActivity.class);
                intent.putExtra("titleMsg","Production display");

                startActivityForResult(intent, REQUEST_CODE_PRODUCTION);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(intent != null) {
            if(requestCode == REQUEST_CODE_CUSTOMER) {
                String message = intent.getStringExtra("message");

                if(message != null){
                    Toast toast = Toast.makeText(getBaseContext(), "Response CustomerAdministration, result code : "
                    + resultCode + ", message : " + message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            else if (requestCode == REQUEST_CODE_SALES) {
                String message = intent.getStringExtra("message");

                if(message != null){
                    Toast toast = Toast.makeText(getBaseContext(), "Response SalesAdministration, result code : "
                            + resultCode + ", message : " + message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            else if (requestCode == REQUEST_CODE_PRODUCTION) {
                String message = intent.getStringExtra("message");

                if(message != null){
                    Toast toast = Toast.makeText(getBaseContext(), "Response ProductionAdministration, result code : "
                            + resultCode + ", message : " + message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
    }
}
