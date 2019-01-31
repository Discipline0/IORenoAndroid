package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import DBManager.DBSQLiteManager;
import Model.Customer;
import Model.Project;

public class GetCustomerInfoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_customer_info);

        Intent intent = getIntent();
        Project project = (Project) intent.getSerializableExtra("project");

        TextView txtProjectTitle       = findViewById(R.id.txtReadMoreProjectTitle);
        TextView txtProjectType        = findViewById(R.id.txtReadMoreProjectTypeValue);
        TextView txtProjectBudget      = findViewById(R.id.txtReadMoreProjectBudgetValue);
        TextView txtProjectDescription = findViewById(R.id.txtReadMoreProjectDescription);
        TextView txtProjectAddress     = findViewById(R.id.txtReadMoreProjectAddressValue);
        TextView txtProjectCity        = findViewById(R.id.txtReadMoreProjectCityValue);

        txtProjectTitle.setText(project.getTitle());
        txtProjectType.setText(" " + project.getProjectType());
        txtProjectBudget.setText(" " + new DecimalFormat("$#,##0.00").format(project.getProjectBudget()));
        txtProjectDescription.setText(project.getProjectDescription());
        txtProjectAddress.setText(" " + project.getAddress());
        txtProjectCity.setText(" " + project.getCity());

        ImageView imgProjectPicture = findViewById(R.id.imgReadMoreProjectPic);
        Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        imgProjectPicture.setImageBitmap(bmp);

        DBSQLiteManager manager = new DBSQLiteManager(this);
        Customer customer = manager.getCustomerFromEmail(project.getCustomerEmail());

        TextView txtCustName  = findViewById(R.id.txtGetCustInfoCustomerNameValue);
        TextView txtCustPhone = findViewById(R.id.txtGetCustInfoCustomerPhoneValue);
        TextView txtCustEmail = findViewById(R.id.txtGetCustInfoCustomerEmailValue);

        txtCustName.setText(customer.getCustomerName());
        txtCustPhone.setText("(" + customer.getCustomerPhone().substring(0, 2) + ") "
                + customer.getCustomerPhone().substring(2, 5) + "-" + customer.getCustomerPhone().substring(5, 9));
        txtCustEmail.setText(customer.getCustomerEmail());
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
