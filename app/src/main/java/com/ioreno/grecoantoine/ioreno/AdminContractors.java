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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Customer;

import java.util.ArrayList;

public class AdminContractors extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    //  private DrawerLayout dl;
    private DrawerLayout dlAdmin;
    private ActionBarDrawerToggle t;
    private ArrayList<Contractor> con_list;
    private TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contractors);

        //hamburger menu
        dlAdmin = (DrawerLayout)findViewById(R.id.drawer_layout);
        t  = new ActionBarDrawerToggle(this, dlAdmin, R.string.nav_open, R.string.nav_close);

        dlAdmin.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        //table
        tl = (TableLayout)findViewById(R.id.ContractorTable);



        //get contractor list
        con_list = getConList();
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerAdminContractorTime);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItem().toString().equals("All-Time")){
                    con_list = getConList();
                    tl.removeAllViews();
                    drawTable();
                }
                else if(spinner.getSelectedItem().toString().equals("Last 7 Days")){
                    con_list = getConListLastWeek();
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
        TextView contractor_company_num = new TextView(this);
        contractor_company_num.setText("Company #");
        contractor_company_num.setTextColor(Color.BLACK);
        contractor_company_num.setPadding(5, 5, 30, 5);
        contractor_company_num.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_company_num);// add the column to the table row here

        TextView contractor_company_name = new TextView(this);
        contractor_company_name.setText("Company Name");
        contractor_company_name.setTextColor(Color.BLACK);
        contractor_company_name.setPadding(5, 5, 30, 5);
        contractor_company_name.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_company_name);// add the column

/*
        TextView contractor_name = new TextView(this);
        contractor_name.setText("Contractor Name");
        contractor_name.setTextColor(Color.BLACK);
        contractor_name.setPadding(5, 5, 30, 5);
        contractor_name.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_name);// add the column
*/

        TextView contractor_phone = new TextView(this);
        contractor_phone.setText("Contractor Phone");
        contractor_phone.setTextColor(Color.BLACK);
        contractor_phone.setPadding(5, 5, 30, 5);
        contractor_phone.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_phone);// add the column

        TextView contractor_email = new TextView(this);
        contractor_email.setText("Contractor Email");
        contractor_email.setTextColor(Color.BLACK);
        contractor_email.setPadding(5, 5, 300, 5);
        contractor_email.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_email);// add the column

        TextView contractor_date_registered = new TextView(this);
        contractor_date_registered.setText("Date Registered");
        contractor_date_registered.setTextColor(Color.BLACK);
        contractor_date_registered.setPadding(5, 5, 30, 5);
        contractor_date_registered.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_date_registered);// add the column

        TextView contractor_approve = new TextView(this);
        contractor_approve.setText("Approve");
        contractor_approve.setTextColor(Color.BLACK);
        contractor_approve.setPadding(5, 5, 30, 5);
        contractor_approve.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_approve);// add the column

        TextView contractor_deny = new TextView(this);
        contractor_deny.setText("Deny");
        contractor_deny.setTextColor(Color.BLACK);
        contractor_deny.setPadding(5, 5, 30, 5);
        contractor_deny.setTypeface(null, Typeface.BOLD);
        tr_head.addView( contractor_deny);// add the column

        if (AdminHome.isAdminLoggedIn)
        {
            TextView reviews_header = new TextView(this);
            reviews_header.setText("Reviews");
            reviews_header.setTextColor(Color.BLACK);
            reviews_header.setPadding(20, 5, 150, 5);
            reviews_header.setTypeface(null, Typeface.BOLD);
            tr_head.addView(reviews_header);// add the column
        }

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        for(int i=0; i<con_list.size(); i++){
            final Contractor c = con_list.get(i);

            TableRow tr = new TableRow(this);
            if(i%2!=0) tr.setBackgroundColor(getResources().getColor(R.color.TableBlue));
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView labelCompNum = new TextView(this);
            TextView labelCompName = new TextView(this);
            //  TextView labelConName = new TextView(this);
            TextView labelConPhone = new TextView(this);
            TextView labelConEmail = new TextView(this);
            TextView labelConDate = new TextView(this);
            Button btnConApprove = new Button(this);
            Button btnConDeny = new Button(this);
            ImageView imgConApprove = new ImageView(this);
            ImageView imgConDeny = new ImageView(this);
            Button btnSeeReviews = new Button(this);
            TextView labelNoReview = new TextView(this);


            labelCompNum.setText((c.getContractorCONum()+""));
            labelCompNum.setPadding(2, 0, 5, 0);
            labelCompNum.setTextColor(Color.BLACK);
            tr.addView(labelCompNum);

            labelCompName.setText((c.getContractorCOName()));
            labelCompName.setPadding(2, 0, 5, 0);
            labelCompName.setTextColor(Color.BLACK);
            tr.addView(labelCompName);

/*            labelConName.setText((c.getContractorName()));
            labelConName.setPadding(2, 0, 5, 0);
            labelConName.setTextColor(Color.BLACK);
            tr.addView(labelConName);*/

            labelConPhone.setText((c.getContractorPhone()));
            labelConPhone.setPadding(2, 0, 5, 0);
            labelConPhone.setTextColor(Color.BLACK);
            tr.addView(labelConPhone);

            labelConEmail.setText((c.getContractorEmail()));
            labelConEmail.setPadding(2, 0, 5, 0);
            labelConEmail.setTextColor(Color.BLACK);
            tr.addView(labelConEmail);

            labelConDate.setText((c.getContractorDateRegistered().substring(0,10)));
            labelConDate.setPadding(2, 0, 5, 0);
            labelConDate.setTextColor(Color.BLACK);
            tr.addView(labelConDate);

            if(c.getApproved() == 0)
            {
                btnConApprove.setText("Approve");
                btnConApprove.setPadding(2, 0, 5, 0);
                btnConApprove.setBackgroundTintList(this.getResources().getColorStateList(R.color.CheckMarkGreen));
                btnConApprove.setTextColor(Color.WHITE);
                tr.addView(btnConApprove);

                final DBSQLiteManager db = new DBSQLiteManager(this);

                btnConApprove.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        db.approveContractor(c);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                btnConDeny.setText("Deny");
                btnConDeny.setPadding(2, 0, 5, 0);
                btnConDeny.setBackgroundTintList(this.getResources().getColorStateList(R.color.LegendCustomer));
                btnConDeny.setTextColor(Color.WHITE);

                btnConDeny.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        db.denyContractor(c);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                tr.addView(btnConDeny);
            }
            else
            {
                //they arent black its a jebait
                //forgot to change name :^)
                imgConApprove.setImageResource(R.drawable.ic_check_black_24dp);
                tr.addView(imgConApprove);
                imgConDeny.setImageResource(R.drawable.ic_clear_black_24dp);
                tr.addView(imgConDeny);
                if(c.getApproved() == 1){
                    imgConDeny.setVisibility(View.INVISIBLE);
                }
                else if(c.getApproved() == 2){
                    imgConApprove.setVisibility(View.INVISIBLE);
                }

            }

            if (AdminHome.isAdminLoggedIn)
            {
                DBSQLiteManager manager = new DBSQLiteManager(this);
                boolean isThereReview = !manager.getReviewListForContractor(c).isEmpty();

                if (isThereReview)
                {
                    btnSeeReviews.setText("See Reviews");
                    btnSeeReviews.setPadding(0, 0, 0, 0);
                    btnSeeReviews.setBackgroundTintList(this.getResources().getColorStateList(R.color.DarkerBlue));
                    btnSeeReviews.setTextColor(Color.WHITE);

                    btnSeeReviews.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            Intent intent = new Intent(v.getContext(), SeeReviewsActivity.class);
                            intent.putExtra("contractor", c);

                            startActivity(intent);
                        }
                    });

                    tr.addView(btnSeeReviews);
                }
                else
                {
                    labelNoReview.setText("There is no review available.");
                    labelNoReview.setPadding(2, 0, 5, 0);
                    labelNoReview.setTextColor(Color.BLACK);
                    tr.addView(labelNoReview);
                }

            }

            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    public ArrayList<Contractor> getConList(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return db.getContractorList();
    }

    public ArrayList<Contractor> getConListLastWeek(){
        DBSQLiteManager db = new DBSQLiteManager(this);
        return db.getContractorListLastWeek();
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
