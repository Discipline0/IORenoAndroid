package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;

public class ContractorSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_sign_up);
    }

    public void OnConSignUp(View v){
        EditText conCONameIn            = (EditText)findViewById(R.id.editConName);
        EditText conPhoneIn           = (EditText)findViewById(R.id.editConPhone);
        EditText conEmailIn           = (EditText)findViewById(R.id.editConEmail);
        EditText conContactNameIn     = (EditText)findViewById(R.id.editConContactName);
        EditText conPasswordIn        = (EditText)findViewById(R.id.editConPassword);
        EditText conPasswordConfirmIn = (EditText)findViewById(R.id.editConPasswordConfirm);

        TextView conErrorIn           = (TextView)findViewById(R.id.txtConError);
        conErrorIn.setSingleLine(false);

        String conCOName            = conCONameIn.getText().toString().trim();
        String conPhone           = conPhoneIn.getText().toString().trim();
        String conEmail           = conEmailIn.getText().toString().trim();
        String conContactName         = conContactNameIn.getText().toString().trim();
        String conPassword        = conPasswordIn.getText().toString().trim();
        String conPasswordConfirm = conPasswordConfirmIn.getText().toString().trim();

        DBSQLiteManager db = new DBSQLiteManager(this);

        String error = "";

        if(TextUtils.isEmpty(conCOName)){
            error+="\nName can't be empty";
        }
        else {
            if (!conCOName.matches("^[a-zA-Z ]*$")) {
                error += "\nName can only contain letters";
            }
        }

        if(!conPhone.matches("^(\\d[\\s-]?)?[\\(\\[\\s-]{0,2}?\\d{3}[\\)\\]\\s-]{0,2}?\\d{3}[\\s-]?\\d{4}$")){
            error+="\nInvalid phone number";
        }

        if(!conEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            error+="\nInvalid email format";
        }
        else {
            if (db.checkIfEmailExists(conEmail)) {
                error += "\nEmail already exists";
            }
        }

        if(TextUtils.isEmpty(conContactName)){
            error+="\nContact Name can't be empty";
        }
        else {
            if (!conCOName.matches("^[a-zA-Z ]*$")) {
                error += "\nContact Name can only contain letters";
            }
        }

        if(!conPassword.equals(conPasswordConfirm)){
            error+="\nPasswords don't match";
        }
        else{
            if(!conPassword.matches("^(?=.*\\d)(?=.*[a-zA-Z]).{8,}$")){
                error+="\nPassword must contain at least a letter, a number and be 8 characters long";
            }
            else{
                if(conPassword.length() > 8){
                    error+="\nPassword must be at least 8 characters";
                }
            }
        }

        if(error.equals("")){
            String emailPassword = conEmail + conPassword;
            String hashPassword = Sha1Hashing.sha1(emailPassword);

            //contractorCONum set 0, will be updated when inserted in database
            Contractor c = new Contractor(0, conCOName, conPhone, conEmail, conContactName, hashPassword);
            db.addContractor(c);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else{
            conErrorIn.setText(error);
        }

        error="";

    }
}
