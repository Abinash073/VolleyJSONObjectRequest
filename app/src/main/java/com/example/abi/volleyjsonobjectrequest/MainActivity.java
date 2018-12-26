package com.example.abi.volleyjsonobjectrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView status,msg;
    Button reqBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status_tv);
        msg = findViewById(R.id.msg_tv);
        reqBtn = findViewById(R.id.req_btn);

        reqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonObjectRequest();
            }
        });
    }

    private void jsonObjectRequest() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://13.127.227.155/mapa/api/user";

        Map<String, String> params = new HashMap<>();
        params.put("email","a@mapa.com");
        params.put("mobile", "1234567890");

        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d(TAG, "onResponse: " + response);

                try {
                    String rspStatus = response.getString("status");
                    String rspMsg = response.getString("message");

                    status.setText(rspStatus);
                    msg.setText(rspMsg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(objectRequest);
    }
}
