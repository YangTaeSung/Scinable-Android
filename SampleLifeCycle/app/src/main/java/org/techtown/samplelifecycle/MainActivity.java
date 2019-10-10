package org.techtown.samplelifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        println("onCreate called");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }


    public void println(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        Log.d("Main", data);
    }



    //@Override
    //protected void onStart() {
    //    super.onStart();
    //    setContentView(R.layout.activity_main);
    //    println("onStart called");
    //}

    @Override
    protected void onStop() {
        super.onStop();
        println("onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        println("onDestroy called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        println("onPause called");
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        println("onResume called");
        restoreState();
    }

    protected void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if ((pref != null) && (pref.contains("name"))) {
            String name = pref.getString("name","");
            nameInput.setText(name);
        }
    }

    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", nameInput.getText().toString());
        editor.commit();
    }

    protected void clearState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
