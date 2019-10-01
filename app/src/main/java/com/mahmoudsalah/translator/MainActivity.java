package com.mahmoudsalah.translator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
TextView resultText;
EditText wordText,tranlateText;
Button translateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordText = findViewById(R.id.wordText);
        tranlateText = findViewById(R.id.translateText);
        resultText = findViewById(R.id.resultText);
    }

    public void translate(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.mymemory.translated.net/get?q="+wordText.getText().toString()+"&langpair=en|ar";
        JsonObjectRequest request = new JsonObjectRequest(url,null,this,this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            String tran = response.getJSONObject("responseData").getString("translatedText");
            tranlateText.setText(tran);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
