package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Project;
import com.ioreno.grecoantoine.ioreno.Model.Customer;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;

public class ContractorHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //hamburger menu
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    private ArrayList<Project> projects;
    private ProjectContractorHomeAdapter adapter;
    private DBSQLiteManager manager;
    private RecyclerView recyclerView;
    private MultiSelectionSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_home);

        //hamburger******
        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, R.string.nav_open, R.string.nav_close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        //if user not logged in hide
        if(Customer.currUser.equals("") && Contractor.currUser.equals("")) {
            hideItem();
        }
        //if logged in change menu item depending on role
        else{
            Menu menu = nv.getMenu();
            MenuItem item = menu.findItem(R.id.user_home);
            if(!Customer.currUser.equals("")){
                item.setTitle("Customer Home");
                //hide home
                NavigationView nvHome = findViewById(R.id.nav_view);
                Menu nav_Menu = nvHome.getMenu();
                nav_Menu.findItem(R.id.nav_index).setVisible(false);
            }
            else if(!Contractor.currUser.equals("")){
                item.setTitle("Contractor Home");
                //hide home
                NavigationView nvHome = findViewById(R.id.nav_view);
                Menu nav_Menu = nvHome.getMenu();
                nav_Menu.findItem(R.id.nav_index).setVisible(false);
            }
        }


        //*****

        spinner = (MultiSelectionSpinner) findViewById(R.id.spinnerFilters);
        spinner.setItems(getApplicationContext().getResources().getStringArray(R.array.type_arrays));

        recyclerView = findViewById(R.id.recyclerViewConHomeProjects);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        manager = new DBSQLiteManager(this);

        projects = manager.getPendingProjectList();

        adapter = new ProjectContractorHomeAdapter(projects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void btnSearch_onClick(View v)
    {
        String selectedTypes = spinner.getSelectedItemsAsString();
        // change manager.getProjectList() to the only show projects that weren't approved
        projects = selectedTypes.equals("") ? manager.getPendingProjectList() : manager.getProjectListFromTypes(selectedTypes);

        adapter = new ProjectContractorHomeAdapter(projects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void btnSeeYourEstimates(View v)
    {
        Intent i = new Intent(this, ContractorSeeEstimatesActivity.class);
        startActivity(i);
    }

    //Hamburger*******************************
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    //Hamburger menu on navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        int checkedId = menuItem.getItemId();

        //call diff fragment based on id selected

        if(checkedId == R.id.user_home){
            //already here
           // Intent i = new Intent(this, CustomerHome.class);
           // startActivity(i);
        }
        if (checkedId == R.id.nav_index) {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        if (checkedId == R.id.nav_about) {

            Intent i = new Intent(this, About.class);
            startActivity(i);
        }
        if (checkedId == R.id.nav_terms_of_use) {
            Intent i = new Intent(this, TermsOfUse.class);
            startActivity(i);
        }
        if (checkedId == R.id.nav_contact) {
            Intent i = new Intent(this, ContactUs.class);
            startActivity(i);
        }
        if (checkedId == R.id.nav_sign_out) {
            Contractor.currUser = "";
            Customer.currUser = "";

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        if (checkedId == R.id.nav_exit) {
            finish();
            System.exit(0);
        }
        return false;
    }

    //hide menu item
    private void hideItem()
    {
        NavigationView nv = findViewById(R.id.nav_view);
        Menu nav_Menu = nv.getMenu();
        nav_Menu.findItem(R.id.user_home).setVisible(false);
    }
    //************************
}
