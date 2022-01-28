package com.greenarmy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Home extends AppCompatActivity {

    RequestQueue queue;
    TextView txtName;
    TextView txtPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        queue = Volley.newRequestQueue(this);

        txtName = findViewById(R.id.txtName);

        txtName.setText("Welcome "+Constants.NAME);

        txtPoints = findViewById(R.id.txtPoints);
        txtPoints.setText(Constants.POINTS);
    }



    public void openaddtask(View view) {
        startActivity(new Intent(Home.this,AddTaskActivity.class));
    }

    public void open(View view)
    {
//        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/නියමු-Project-green-Agni-112638110175982/"));
//        startActivity(browserIntent);

        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/112638110175982")));
    }
}
