package org.techtown.sampleorientation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("OnCreate gogo");

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editText.getText().toString();
                showToast("JeoJang ok " + name);
            }
        });

        if(savedInstanceState != null) {
            name = savedInstanceState.getString("name");
            showToast("bokwon OK " + name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //showToast("OnStart gogo");
    }

    @Override
    protected void onStop() {
        super.onStop();

        showToast("OnStop gogo");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        showToast("OnDestroy gogo");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name",name);
    }

    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
