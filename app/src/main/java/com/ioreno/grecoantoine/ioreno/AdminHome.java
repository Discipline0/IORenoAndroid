package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.Fragments.about_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.contact_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.index_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.terms_of_use_frag;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import DBManager.DBSQLiteManager;
import Model.Contractor;
import Model.Customer;

public class AdminHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
  //  private DrawerLayout dl;
    private DrawerLayout dlAdmin;
    private ActionBarDrawerToggle t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        //hamburger menu
        dlAdmin = (DrawerLayout)findViewById(R.id.drawer_layout);
        t  = new ActionBarDrawerToggle(this, dlAdmin, R.string.nav_open, R.string.nav_close);

        dlAdmin.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);



        drawPie();
        final Spinner spinner = (Spinner) findViewById(R.id.adminSpinnerTime);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItem().toString().equals("All-Time")){
                    drawPie();

                }
                else if(spinner.getSelectedItem().toString().equals("Last 7 Days")){
                    drawPieLastSevenDays();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


}


public void drawPie(){
    AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
    AnimatedPieViewConfig config = new AnimatedPieViewConfig();
    int custCount = getCustCount();
    int conCount = getConCount();

    config.startAngle(-90)// Starting angle offset
            //put row values here, does math antumatically
            .addData(new SimplePieInfo(custCount, Color.parseColor("#FF0055"), custCount+""))//Data (bean that implements the IPieInfo interface)
            .addData(new SimplePieInfo(conCount, Color.parseColor("#03C0FF"), conCount+"")).drawText(true)
      .duration(2000).textSize(50).canTouch(true);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
    mAnimatedPieView.applyConfig(config);
    mAnimatedPieView.start();
    }

    public void drawPieLastSevenDays(){
        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        int custCount = getCustCountLastSevenDays();
        int conCount = getConCountLastSevenDays();

        config.startAngle(-90)// Starting angle offset
                //put row values here, does math antumatically
                .addData(new SimplePieInfo(custCount, Color.parseColor("#FF0055"), custCount+"a"))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(conCount, Color.parseColor("#03C0FF"), conCount+"a")).drawText(true)
                .duration(2000).textSize(50).canTouch(true);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }

    public int getCustCount(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return db.getCustomerCount();
    }

    public int getConCount(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return  db.getContractorCount();
    }

    public int getCustCountLastSevenDays(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return db.getCustomerCountLastSevenDays();
    }

    public int getConCountLastSevenDays(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return  db.getContractorCountLastSevenDays();
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





