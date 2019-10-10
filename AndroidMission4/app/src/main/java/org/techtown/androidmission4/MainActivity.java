package org.techtown.androidmission4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {


    private final int LIMIT = 80;
    private EditText editText;
    private TextView byteLabel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    editText = (EditText)findViewById(R.id.editText);
    byteLabel = (TextView)findViewById(R.id.bytenumber);
    Button send = (Button)findViewById(R.id.send);
    Button exit = (Button)findViewById(R.id.exit);

        editText.addTextChangedListener(new TextWatcher(){
        String beforeText;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after){
            beforeText = charSequence.toString();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count){

        }

        @Override

        public void afterTextChanged(Editable editable){
            int length = showBytes();
            if(length > LIMIT)
                editText.setText(beforeText);
        }

    });

        send.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            Toast.makeText(getApplicationContext(), "전송되었습니다.", Toast.LENGTH_LONG).show();
        }
    });

        exit.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            MainActivity.this.finish();
        }
    });
    showBytes();
}
    private int showBytes(){
        try{
            int length = editText.getText().toString().getBytes("EUC-KR").length;
            byteLabel.setText(length + " / 80 바이트");
            return length;
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return -1;
    }
}

