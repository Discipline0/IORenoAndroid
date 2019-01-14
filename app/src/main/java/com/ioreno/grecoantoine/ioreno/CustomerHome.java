package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
    }

    public void onCreateNewProject(View v){
        Intent i = new Intent(this, CustomerCreateNewProject.class);
        startActivity(i);


    }
}
