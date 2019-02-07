package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import Model.Contractor;
import Model.Customer;

public class ContactUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

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
    }

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
            if(!Customer.currUser.equals("")){
                Intent i = new Intent(this,CustomerHome.class);
                startActivity(i);
            }
            else if(!Contractor.currUser.equals("")){
                Intent i = new Intent(this, ContractorHome.class);
                startActivity(i);
            }
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
            //already here
            //Intent i = new Intent(this, ContactUs.class);
            //startActivity(i);
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

    private void hideItem()
    {
        NavigationView nv = findViewById(R.id.nav_view);
        Menu nav_Menu = nv.getMenu();
        nav_Menu.findItem(R.id.user_home).setVisible(false);
    }
}
