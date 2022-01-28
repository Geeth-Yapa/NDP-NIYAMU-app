package com.greenarmy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {

    private TextView textView;
    private RequestQueue queue;
    private String task;
    private int taskid;
    private EditText edit_comment;
    private Spinner taskDrpDwn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        queue = Volley.newRequestQueue(this);

        edit_comment = findViewById(R.id.comment);

        taskDrpDwn = findViewById(R.id.drpTasks);

    }


    public void submitTask(View view) {

        final String comment = edit_comment.getText().toString();
        final String task = taskDrpDwn.getSelectedItem().toString();
        final int taskid = taskDrpDwn.getSelectedItemPosition();


        Boolean proceed = true;

        if (comment.isEmpty()) {
            edit_comment.setError("A comment is required");
            proceed = false;
        }

        if (proceed) {
            final Dialog dialog = Util.showProgress(AddTaskActivity.this);
            dialog.show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.CONTEXT_PATH + "/addtask.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog.dismiss();
                            // response
                            Log.d("Response : ", response);
                            if (response.equals("[]")) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddTaskActivity.this);
                                builder1.setMessage("Failed to add task, Please retry !");
                                builder1.setCancelable(true);

                                AlertDialog alert = builder1.create();
                                alert.show();
                            } else {
                                try {
                                    Constants.POINTS = response;
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddTaskActivity.this);
                                    builder1.setTitle("Well done !");
                                    builder1.setMessage("Thank you for taking your time on making our university a better place. \n" +
                                            "Please feel free to send related photos to our official Facebook Page within 24 hours if not the points will be reversed by the admin");
                                    builder1.setCancelable(true);
                                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            startActivity(new Intent(AddTaskActivity.this, Home.class));
                                            finish();
                                        }
                                    });

                                    AlertDialog alert = builder1.create();
                                    alert.show();

                                } catch (Exception e) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AddTaskActivity.this);
                                    builder1.setMessage("Failed to add task, Please retry !");
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
                    params.put("userid", Constants.PERSON_ID.toString());
                    params.put("task", task);
                        params.put("taskid", String.valueOf(taskid));
                    params.put("comment", comment);

                    return params;
                }
            };
            queue.add(postRequest);
        }
    }
    }

