package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import DBManager.DBSQLiteManager;
import Model.Customer;

public class CustomerSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

    }

    public void onCustSignUp(View v){
        EditText custNameIn            = (EditText)findViewById(R.id.editCustName);
        EditText custEmailIn           = (EditText)findViewById(R.id.editCustEmail);
        EditText custPhoneIn           = (EditText)findViewById(R.id.editCustPhone);
        EditText custPasswordIn        = (EditText)findViewById(R.id.editCustPassword);
        EditText custPasswordConfirmIn = (EditText)findViewById(R.id.editCustPasswordConfirm);

        TextView custErrorIn           = (TextView)findViewById(R.id.txtCustError);
        custErrorIn.setSingleLine(false);

        String custName            = custNameIn.getText().toString().trim();
        String custEmail           = custEmailIn.getText().toString().trim();
        String custPhone           = custPhoneIn.getText().toString().trim();
        String custPassword        = custPasswordIn.getText().toString().trim();
        String custPasswordConfirm = custPasswordConfirmIn .getText().toString().trim();

        DBSQLiteManager db = new DBSQLiteManager(this);

        String error = "";
        if(TextUtils.isEmpty(custName)){
            error+="\nName can't be empty";
        }
        else {
            if (!custName.matches("^[a-zA-Z ]*$")) {
                error += "\nName can only contain letters";
            }
        }

        if(!custPhone.matches("^(\\d[\\s-]?)?[\\(\\[\\s-]{0,2}?\\d{3}[\\)\\]\\s-]{0,2}?\\d{3}[\\s-]?\\d{4}$")){
            error+="\nInvalid phone number";
        }

        if(!custEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            error+="\nInvalid email format";
        }
        else {
            if (db.checkIfEmailExists(custEmail)) {
                error += "\nEmail already exists";
            }
        }

        if(!custPassword.equals(custPasswordConfirm)){
            error+="\nPasswords don't match";
        }
        else{
            if(!custPassword.matches("^(?=.*\\d)(?=.*[a-zA-Z]).{8,}$")){
                error+="\nPassword must contain at least 1 letter and 1 number and be 8 characters long";
            }
            else{
                if(custPassword.length() > 8){
                    error+="\nPassword must be at least 8 characters";
                }
            }
        }

        if(error.equals("")){
            Customer c = new Customer(custName, custEmail, custPhone, custPassword);
            db.addCustomer(c);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else{
            custErrorIn.setText(error);
        }



        error="";

    }
}
