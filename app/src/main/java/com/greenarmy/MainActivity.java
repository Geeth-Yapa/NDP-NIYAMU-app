package com.greenarmy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue ;
    EditText username;
    EditText password;
    TextView marqueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueText  = findViewById(R.id.marquetext);
        marqueText.setSelected(true);

        queue = Volley.newRequestQueue(this);
    }

    public void test(View view){
        startActivity(new Intent(MainActivity.this,Home.class));

    }

    public void login(View view) {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Boolean proceed = true;

        if (username.getText().toString().isEmpty()) {
            username.setError("Username cannot be empty");
            proceed = false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Password cannot be empty");
            proceed = false;
        }

        if (proceed) {
            final Dialog dialog = Util.showProgress(MainActivity.this);
            dialog.show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.CONTEXT_PATH+"/login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog.dismiss();
                            // response
                            Log.d("Response : ", response);
                            if (response.equals("[]")) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                builder1.setMessage("Invalid Username or Password");
                                builder1.setCancelable(true);

                                AlertDialog alert = builder1.create();
                                alert.show();
                            }else{
                                try {
                                    Constants.PERSON_ID = Integer.parseInt(new JSONObject(response).get("id").toString());
                                    Constants.NAME = new JSONObject(response).getString("name");
                                    Constants.POINTS = new JSONObject(response).getString("points");
                                    startActivity(new Intent(MainActivity.this,Home.class));
                                    finish();
                                } catch (JSONException e) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                    builder1.setMessage("Cannot Login");
                                    builder1.setCancelable(true);

                                    AlertDialog alert = builder1.create();
                                    alert.show();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
                            // error
                            Log.d("Error.Response", error.getMessage());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username.getText().toString());
                    params.put("password", password.getText().toString());

                    return params;
                }
            };
            queue.add(postRequest);
        }
    }
}
