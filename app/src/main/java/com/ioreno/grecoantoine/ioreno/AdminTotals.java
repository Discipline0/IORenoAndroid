package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AdminTotals extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //  private DrawerLayout dl;
    private DrawerLayout dlAdmin;
    private ActionBarDrawerToggle t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_totals);

        //hamburger menu
        dlAdmin = (DrawerLayout)findViewById(R.id.drawer_layout);
        t  = new ActionBarDrawerToggle(this, dlAdmin, R.string.nav_open, R.string.nav_close);

        dlAdmin.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        //table
        TableLayout tl = (TableLayout)findViewById(R.id.TotalTable);

        //header
        TableRow tr_head = new TableRow(this);
        tr_head.setBackgroundColor(getResources().getColor(R.color.TableBlue));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        //Header***************************************************
        TextView number_payments = new TextView(this);
        number_payments.setText("Number of Payments");
        number_payments.setTextColor(Color.BLACK);
        number_payments.setPadding(5, 5, 400, 5);
        number_payments.setTypeface(null, Typeface.BOLD);
        tr_head.addView(number_payments);// add the column to the table row here

        TextView total_payments = new TextView(this);
        total_payments.setText("Total payments");
        total_payments.setTextColor(Color.BLACK);
        total_payments.setPadding(5, 5, 30, 5);
        total_payments.setTypeface(null, Typeface.BOLD);
        tr_head.addView(total_payments);// add the column to the table row here

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView labelNumPay = new TextView(this);
        TextView labelTotPay = new TextView(this);

        NumberFormat dfTotal = new DecimalFormat("#.00");
        NumberFormat dfNumber = new DecimalFormat("#.##");
        double total = getTotal();
        double number = getNumber();
        labelNumPay.setText(dfNumber.format(getNumber())+"");
        labelNumPay.setPadding(2, 0, 5, 0);
        labelNumPay.setTextColor(Color.BLACK);
        tr.addView(labelNumPay);

        labelTotPay.setText(dfTotal.format(total)+" $");
        labelTotPay.setPadding(2, 0, 5, 0);
        labelTotPay.setTextColor(Color.BLACK);
        tr.addView(labelTotPay);

        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }

    public double getNumber(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return  db.getPaymentCount();
    }
    public double getTotal(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return  db.getTotalPayments();
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
