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
import com.ioreno.grecoantoine.ioreno.Model.Payment;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Customer;

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

        //table
        TableLayout tl = (TableLayout)findViewById(R.id.DeniedTable);

        //header
        TableRow tr_head = new TableRow(this);
        tr_head.setBackgroundColor(getResources().getColor(R.color.TableBlue));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        //Header***************************************************
        TextView payment_id = new TextView(this);
        payment_id.setText("Payment ID");
        payment_id.setTextColor(Color.BLACK);
        payment_id.setPadding(5, 5, 30, 5);
        payment_id.setTypeface(null, Typeface.BOLD);
        tr_head.addView(payment_id);// add the column to the table row here

        TextView company_id = new TextView(this);
        company_id.setText("Company #");
        company_id.setTextColor(Color.BLACK);
        company_id.setPadding(5, 5, 30, 5);
        company_id.setTypeface(null, Typeface.BOLD);
        tr_head.addView(company_id);// add the column to the table row here

        TextView payment_amt = new TextView(this);
        payment_amt.setText("Payment Amount");
        payment_amt.setTextColor(Color.BLACK);
        payment_amt.setPadding(5, 5, 30, 5);
        payment_amt.setTypeface(null, Typeface.BOLD);
        tr_head.addView(payment_amt);// add the column to the table row here

        TextView proposal_id = new TextView(this);
        proposal_id.setText("Proposal ID");
        proposal_id.setTextColor(Color.BLACK);
        proposal_id.setPadding(5, 5, 30, 5);
        proposal_id.setTypeface(null, Typeface.BOLD);
        tr_head.addView(proposal_id);// add the column to the table row here

        TextView payment_status = new TextView(this);
        payment_status.setText("Payment Status");
        payment_status.setTextColor(Color.BLACK);
        payment_status.setPadding(5, 5, 30, 5);
        payment_status.setTypeface(null, Typeface.BOLD);
        tr_head.addView(payment_status);// add the column to the table row here

        TextView payment_date = new TextView(this);
        payment_date.setText("Payment Status");
        payment_date.setTextColor(Color.BLACK);
        payment_date.setPadding(5, 5, 30, 5);
        payment_date.setTypeface(null, Typeface.BOLD);
        tr_head.addView(payment_date);// add the column to the table row here

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        int count = 0;
        ArrayList<Payment> pay_list = getDeniedPaymentList();
        for(Payment p : pay_list){
            TableRow tr = new TableRow(this);
            if (count % 2 != 0)
                tr.setBackgroundColor(getResources().getColor(R.color.TableBlue));
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            TextView paymentID = new TextView(this);
            TextView companyNum = new TextView(this);
            TextView paymentAMT = new TextView(this);
            TextView proposalID = new TextView(this);
            TextView paymentStatus = new TextView(this);
            TextView paymentDate = new TextView(this);

            NumberFormat df = new DecimalFormat("#.00");

            paymentID.setText(p.getPaymentID() + "");
            paymentID.setPadding(2, 0, 5, 0);
            paymentID.setTextColor(Color.BLACK);
            tr.addView(paymentID);

            companyNum.setText(p.getContractorCONum() + "");
            companyNum.setPadding(2, 0, 5, 0);
            companyNum.setTextColor(Color.BLACK);
            tr.addView(companyNum);

            paymentAMT.setText(df.format(p.getPaymentAmount()) + " ");
            paymentAMT.setPadding(2, 0, 5, 0);
            paymentAMT.setTextColor(Color.BLACK);
            tr.addView(paymentAMT);

            proposalID.setText(p.getProposalID() + "");
            proposalID.setPadding(2, 0, 5, 0);
            proposalID.setTextColor(Color.BLACK);
            tr.addView(proposalID);

            paymentStatus.setText(p.getPaymentStatus() + "");
            paymentStatus.setPadding(2, 0, 5, 0);
            paymentStatus.setTextColor(Color.BLACK);
            tr.addView(paymentStatus);

            paymentDate.setText(p.getPaymentDate().substring(0, 10));
            paymentDate.setPadding(2, 0, 5, 0);
            paymentDate.setTextColor(Color.BLACK);
            tr.addView(paymentDate);

            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            count++;
        }
    }

    public ArrayList<Payment> getDeniedPaymentList(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return db.getDeniedPaymentList();
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
