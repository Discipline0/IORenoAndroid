package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import DBManager.DBSQLiteManager;
import Model.Customer;
import Model.Project;

public class CustomerHome extends AppCompatActivity {

    private ArrayList<Project> projects;
    private ProjectAdapter adapter;
    private DBSQLiteManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProjects);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        manager = new DBSQLiteManager(this);

        projects = manager.getProjectListForCustomerEmail(Customer.currUser);


        adapter = new ProjectAdapter(projects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onCreateNewProject(View v){
        Intent i = new Intent(this, CustomerCreateNewProject.class);
        startActivity(i);


    }
}
