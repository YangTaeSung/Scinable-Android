package org.techtown.sampleparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static org.techtown.sampleparcelable.MainActivity.KEY_SIMPLE_DATA;

public class MenuActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");
                setResult(RESULT_OK,intent);

                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            Simpledata data = bundle.getParcelable(KEY_SIMPLE_DATA);
            if(intent != null) {
                textView.setText("transmisioned data \nNumber : " + data.number + "\nMessage :" +
                        data.message);
            }
        }
    }
}
