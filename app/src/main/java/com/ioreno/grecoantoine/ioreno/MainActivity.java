package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.Fragments.about_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.contact_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.index_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.terms_of_use_frag;

import DBManager.DBSQLiteManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //load index fragment by default
        loadFragment(new index_frag());

        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t  = new ActionBarDrawerToggle(this, dl, R.string.nav_open, R.string.nav_close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }

    public void onCustomerSignUpClick(View v){
        Intent i = new Intent(this, CustomerSignUp.class);
        startActivity(i);
    }

    public void onContractorSignUpClick(View v){
        Intent i = new Intent(this, ContractorSignUp.class);
        startActivity(i);
    }

    public void onSignInClick(View v){
        EditText editEmailLoginIn      = (EditText)findViewById(R.id.editEmailLogin);
        EditText editPasswordLoginIn   = (EditText)findViewById(R.id.editPasswordLogin);
        TextView txtLoginError         = (TextView)findViewById(R.id.txtLoginError);

        String editEmailLogin = editEmailLoginIn.getText().toString();
        String editPasswordLogin = editPasswordLoginIn.getText().toString();
        DBSQLiteManager db = new DBSQLiteManager(this);


        if (db.checkForCustomer(editEmailLogin, editPasswordLogin))
        {
        //    User.setCurrUser(newUser.getUsername());
        //    User.setIsLoggedIn(true);
            Intent i = new Intent(this,CustomerHome.class);
            startActivity(i);

        }
        else if(db.checkForContractor(editEmailLogin, editPasswordLogin)){
            Intent i = new Intent(this, ContractorHome.class);
            startActivity(i);

        }
        else
        {
            txtLoginError.setText("Invalid Username or Password");
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

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
        if(checkedId == R.id.nav_index){
            loadFragment(new index_frag());
        }
        if(checkedId == R.id.nav_about){
            loadFragment(new about_frag());
        }
        if(checkedId == R.id.nav_terms_of_use){
            loadFragment(new terms_of_use_frag());
        }
        if(checkedId == R.id.nav_contact){
            loadFragment(new contact_frag());
        }
        if(checkedId == R.id.nav_sign_out){
           //sign out
        }
        if(checkedId == R.id.nav_exit){
            finish();
            System.exit(0);
        }
        return false;
    }

    //load fragment method
    public void loadFragment(Fragment fg){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLay, fg );
        ft.commit();
    }




}
