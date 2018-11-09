package me.simonfong.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WebRequests extends AppCompatActivity {

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_requests);

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
    }

    /** Called when the user taps the Request button */
    public void sendRequest(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.http_url);
        String url = editText.getText().toString();

        Log.e("HTTP","URL: '" + url + "'");

        final TextView mTextView = (TextView) findViewById(R.id.text_response);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText(String.format("That didn't work!%s", error));
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    /** Called when the user taps the JSON Request button */
    public void sendJSONRequest(View view) {
        // Do something in response to button
        EditText edit_text_url;
        String url;

        edit_text_url = (EditText) findViewById(R.id.http_url);
        url = edit_text_url.getText().toString();

        Log.e("HTTP","URL: '" + url + "'");

        final TextView text_view_response = (TextView) findViewById(R.id.text_response);

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Convert JSON object to string.
                        text_view_response.setText("Response is: "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text_view_response.setText(String.format("That didn't work!%s", error));
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);

    }
}
