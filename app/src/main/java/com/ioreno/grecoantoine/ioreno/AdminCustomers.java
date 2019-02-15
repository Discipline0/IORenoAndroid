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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Customer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdminCustomers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //  private DrawerLayout dl;
    private DrawerLayout dlAdmin;
    private ActionBarDrawerToggle t;
    private TableLayout tl;
    private ArrayList<Customer> cust_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customers);

        //hamburger menu
        dlAdmin = (DrawerLayout)findViewById(R.id.drawer_layout);
        t  = new ActionBarDrawerToggle(this, dlAdmin, R.string.nav_open, R.string.nav_close);

        dlAdmin.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        //table
        tl = (TableLayout)findViewById(R.id.CustomerTable);

        //get customer list
        cust_list = getCustList();
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerAdminCustomerTime);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItem().toString().equals("All-Time")){
                    cust_list = getCustList();
                    tl.removeAllViews();
                    drawTable();
                }
                else if(spinner.getSelectedItem().toString().equals("Last 7 Days")){
                    cust_list = getCustListLastWeek();
                    tl.removeAllViews();
                    drawTable();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void drawTable()
    {
        //header
        TableRow tr_head = new TableRow(this);
        tr_head.setBackgroundColor(getResources().getColor(R.color.TableBlue));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        //Header***************************************************
        TextView customer_id = new TextView(this);
        customer_id.setText("#");
        customer_id.setTextColor(Color.BLACK);
        customer_id.setPadding(5, 5, 30, 5);
        customer_id.setTypeface(null, Typeface.BOLD);
        tr_head.addView(customer_id);// add the column to the table row here

        TextView customer_name = new TextView(this);
        customer_name.setText("Customer Name");
        customer_name.setTextColor(Color.BLACK);
        customer_name.setPadding(5, 5, 30, 5);
        customer_name.setTypeface(null, Typeface.BOLD);
        tr_head.addView(customer_name);// add the column to the table row here

        TextView customer_phone = new TextView(this);
        customer_phone.setText("Customer Phone");
        customer_phone.setTextColor(Color.BLACK);
        customer_phone.setPadding(5, 5, 30, 5);
        customer_phone.setTypeface(null, Typeface.BOLD);
        tr_head.addView(customer_phone);// add the column to the table row here

        TextView customer_email = new TextView(this);
        customer_email.setText("Customer Email");
        customer_email.setTextColor(Color.BLACK);
        customer_email.setPadding(5, 5, 300, 5);
        customer_email.setTypeface(null, Typeface.BOLD);
        tr_head.addView(customer_email);// add the column to the table row here

        TextView customer_date = new TextView(this);
        customer_date.setText("Date registered");
        customer_date.setTextColor(Color.BLACK);
        customer_date.setPadding(5, 5, 30, 5);
        customer_date.setTypeface(null, Typeface.BOLD);
        tr_head.addView(customer_date);// add the column to the table row here

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        int count = 0;
        for(Customer c : cust_list){
            TableRow tr = new TableRow(this);
            if(count%2!=0) tr.setBackgroundColor(getResources().getColor(R.color.TableBlue));
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            TextView labelNum = new TextView(this);
            TextView labelName = new TextView(this);
            TextView labelPhone = new TextView(this);
            TextView labelEmail = new TextView(this);
            TextView labelDate = new TextView(this);

            labelNum.setText(c.getCustomerID()+"");
            labelNum.setPadding(2, 0, 5, 0);
            labelNum.setTextColor(Color.BLACK);
            tr.addView(labelNum);

            labelName.setText(c.getCustomerName());
            labelName.setPadding(2, 0, 5, 0);
            labelName.setTextColor(Color.BLACK);
            tr.addView(labelName);

            labelPhone.setText(c.getCustomerPhone());
            labelPhone.setPadding(2, 0, 5, 0);
            labelPhone.setTextColor(Color.BLACK);
            tr.addView(labelPhone);

            labelEmail.setText(c.getCustomerEmail());
            labelEmail.setPadding(2, 0, 5, 0);
            labelEmail.setTextColor(Color.BLACK);
            tr.addView(labelEmail);

            labelDate.setText( c.getCustomerDateRegistered().substring(0,10));
            labelDate.setPadding(2, 0, 5, 0);
            labelDate.setTextColor(Color.BLACK);
            tr.addView(labelDate);

            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            count++;
        }
    }

    public ArrayList<Customer> getCustList(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return  db.getCustomerList();
    }

    public ArrayList<Customer> getCustListLastWeek(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return  db.getCustomerListLastWeek();
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
            Contractor.currUser = "";
            Customer.currUser = "";
            AdminHome.isAdminLoggedIn = false;

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
