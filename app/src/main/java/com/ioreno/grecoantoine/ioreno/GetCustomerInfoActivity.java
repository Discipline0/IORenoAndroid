package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Customer;
import com.ioreno.grecoantoine.ioreno.Model.Project;

import java.text.DecimalFormat;


public class GetCustomerInfoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_customer_info);

        Intent intent = getIntent();
        Project project = (Project) intent.getSerializableExtra("project");

        TextView txtProjectTitle       = findViewById(R.id.txtGetCustInfoProjectTitle);
        TextView txtProjectType        = findViewById(R.id.txtGetCustInfoProjectTypeValue);
        TextView txtProjectBudget      = findViewById(R.id.txtGetCustInfoProjectBudgetValue);
        TextView txtProjectDescription = findViewById(R.id.txtGetCustInfoProjectDescription);
        TextView txtProjectAddress     = findViewById(R.id.txtGetCustInfoProjectAddressValue);
        TextView txtProjectCity        = findViewById(R.id.txtGetCustInfoProjectCityValue);

        txtProjectTitle.setText(project.getTitle());
        txtProjectType.setText(" " + project.getProjectType());
        txtProjectBudget.setText(" " + new DecimalFormat("$#,##0.00").format(project.getProjectBudget()));
        txtProjectDescription.setText(project.getProjectDescription());
        txtProjectAddress.setText(" " + project.getAddress());
        txtProjectCity.setText(" " + project.getCity());

        ImageView imgProjectPicture = findViewById(R.id.imgGetCustInfoProjectPic);
        Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        imgProjectPicture.setImageBitmap(bmp);

        DBSQLiteManager manager = new DBSQLiteManager(this);
        Customer customer = manager.getCustomerFromEmail(project.getCustomerEmail());

        TextView txtCustName  = findViewById(R.id.txtGetCustInfoCustomerNameValue);
        TextView txtCustPhone = findViewById(R.id.txtGetCustInfoCustomerPhoneValue);
        TextView txtCustEmail = findViewById(R.id.txtGetCustInfoCustomerEmailValue);

        txtCustName.setText(" " + customer.getCustomerName());
        String phoneNumber = customer.getCustomerPhone();
        txtCustPhone.setText(" (" + phoneNumber.substring(0, 3) + ") "+ phoneNumber.substring(3, 6)
                + "-" + phoneNumber.substring(6, 10));
        txtCustEmail.setText(" " + customer.getCustomerEmail());
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
