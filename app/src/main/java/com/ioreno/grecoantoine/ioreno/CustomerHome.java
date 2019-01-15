package com.ioreno.grecoantoine.ioreno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import DBManager.DBSQLiteManager;
import Model.Project;

public class CustomerHome extends AppCompatActivity {

    private ArrayList<Project> products;
    private ProjectAdapter adapter;
    private DBSQLiteManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProjects);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        manager = new DBSQLiteManager(this);

        // Change the String value to the logged in user once it is implemented ****************
        products = manager.getProjectListForCustomerEmail("");

        adapter = new ProjectAdapter(products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onCreateNewProject(View v){

    }
}
