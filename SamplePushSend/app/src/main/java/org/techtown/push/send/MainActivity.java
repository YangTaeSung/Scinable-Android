package org.techtown.push.send;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    static RequestQueue requestQueue;
    static String regId = "dDjapFXorRk:APA91bFEGOvmouzkQDtVFDCeT0DYqv0QnU-uuB9-Q8e1sc2m1aPGw4fOZvVMbauryc3MCjo0WRxUY7NtdUnbz_obEldC1Xgcd_53jtlZ3qmPKJL8glajrU_4A66N6YsUuYW0naMKV80x";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                send(input);
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void send (String input) {
        JSONObject requestData = new JSONObject();

        try {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requestData.put("data", dataObj);

            JSONArray idArray = new JSONArray();
            idArray.put(0,regId);
            requestData.put("registration_ids", idArray);
        } catch(Exception e) {
            e.printStackTrace();
        }

        sendData(requestData, new SendResponseListener() {
            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted() called.");
            }

            @Override
            public void onRequestStarted() {
                println("onRequestStarted() called." );
            }

            @Override
            public void onRequestWithError(VolleyError error) {
                println("onRequestWithError() called.");
            }
        });
    }

    public void println(String data) {
        textView.append(data + "\n");
    }

    public interface SendResponseListener {
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestWithError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onRequestWithError(error);
                    }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String,String>();
                headers.put("Authorization", "key=AAAAqa8UPmw:APA91bHiZsmy6Ipqs8SO6Vti8T0C8WQ98szMawcP5YCLuEQ8-y8Z47VLy2GVdX3QtjvceG57WExd5tW0a2GqQMo60ci7Seyz3LeahOJCUuTxNe3o4a36xQdxM7_OADo_36ZGqzG3PKRV");

                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        requestQueue.add(request);

    }
}
