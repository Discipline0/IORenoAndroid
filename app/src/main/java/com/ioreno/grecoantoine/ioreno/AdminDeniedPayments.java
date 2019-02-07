package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class AdminDeniedPayments extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //  private DrawerLayout dl;
    private DrawerLayout dlAdmin;
    private ActionBarDrawerToggle t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_denied_payments);

        //hamburger menu
        dlAdmin = (DrawerLayout)findViewById(R.id.drawer_layout);
        t  = new ActionBarDrawerToggle(this, dlAdmin, R.string.nav_open, R.string.nav_close);

        dlAdmin.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    //Hamburger menu on navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        int checkedId = menuItem.getItemId();

        //call diff fragment based on id selected
        if(checkedId == R.id.nav_admin_home){
            Intent i = new Intent(this, AdminHome.class);
            startActivity(i);
        }
        if(checkedId == R.id.nav_admin_customers){
            Intent i = new Intent(this, AdminCustomers.class);
            startActivity(i);
        }
        if(checkedId == R.id.nav_admin_contractors){
            Intent i = new Intent(this, AdminContractors.class);
            startActivity(i);
        }
        if(checkedId == R.id.nav_admin_payments){
            Intent i = new Intent(this, AdminPayments.class);
            startActivity(i);
        }
        if(checkedId == R.id.nav_admin_denied_payments){
            Intent i = new Intent(this, AdminDeniedPayments.class);
            startActivity(i);
        }
        if(checkedId == R.id.nav_admin_totals){
            Intent i = new Intent(this, AdminTotals.class);
            startActivity(i);
        }
        if(checkedId == R.id.nav_sign_out){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        if(checkedId == R.id.nav_exit){
            finish();
            System.exit(0);
        }
        return false;
    }

}
