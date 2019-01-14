package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.Fragments.about_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.contact_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.index_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.terms_of_use_frag;

public class CustomerCreateNewProject extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_new_project);


 /*       NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);*/

    }

    public void onGoBack(View v){
        Intent i = new Intent(this, CustomerHome.class);
        startActivity(i);
    }
    public void onConfirm(View v){
        EditText projTitleIn = (EditText)findViewById(R.id.editCreateNewProjectTitle);
        EditText projDescriptionIn = (EditText) findViewById(R.id.editCreateNewProjectDescription);
        Spinner  projTypeIn = (Spinner)findViewById(R.id.spinnerCreateNewProjectType);
        EditText projAddressIn = (EditText) findViewById(R.id.editCreateNewProjectAddress);
        EditText projCityIn = (EditText) findViewById(R.id.editCreateNewProjectCity);
        EditText projBudgetyIn = (EditText) findViewById(R.id.numCreateNewProjectBudget);
       // Image    projImageIn = (Image) findViewById(R.id.imgProjectImage);


    }



}
