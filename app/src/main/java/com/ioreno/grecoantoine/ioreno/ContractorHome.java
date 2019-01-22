package com.ioreno.grecoantoine.ioreno;

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

public class ContractorHome extends AppCompatActivity {

    private ArrayList<Project> projects;
    private ProjectContractorHomeAdapter adapter;
    private DBSQLiteManager manager;
    private RecyclerView recyclerView;
    private MultiSelectionSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_home);

        spinner = (MultiSelectionSpinner) findViewById(R.id.spinnerFilters);
        spinner.setItems(getApplicationContext().getResources().getStringArray(R.array.type_arrays));

        recyclerView = findViewById(R.id.recyclerViewConHomeProjects);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        manager = new DBSQLiteManager(this);

        // Change this for project that weren't accepted
        projects = manager.getProjectList();

        adapter = new ProjectContractorHomeAdapter(projects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void btnSearch_onClick(View v)
    {
        String selectedTypes = spinner.getSelectedItemsAsString();
        // change manager.getProjectList() to the only show projects that weren't approved
        projects = selectedTypes.equals("") ? manager.getProjectList() : manager.getProjectListFromTypes(selectedTypes);

        adapter = new ProjectContractorHomeAdapter(projects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void btnSeeYourEstimates(View v)
    {

    }
}
