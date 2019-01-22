package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import Model.Project;

public class ContractorReadMoreActivity extends AppCompatActivity {

    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_read_more);

        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra("project");

        TextView txtProjectTitle = findViewById(R.id.txtReadMoreProjectTitle);
        TextView txtProjectType = findViewById(R.id.txtReadMoreProjectTypeValue);
        TextView txtProjectBudget = findViewById(R.id.txtReadMoreProjectBudgetValue);
        TextView txtProjectDescription = findViewById(R.id.txtReadMoreProjectDescription);
        TextView txtProjectAddress = findViewById(R.id.txtReadMoreProjectAddressValue);
        TextView txtProjectCity = findViewById(R.id.txtReadMoreProjectCityValue);

        txtProjectTitle.setText(project.getTitle());
        txtProjectType.setText(" " + project.getProjectType());
        txtProjectBudget.setText(" " + new DecimalFormat("$#,##0.00").format(project.getProjectBudget()));
        txtProjectDescription.setText(project.getProjectDescription());
        txtProjectAddress.setText(" " + project.getAddress());
        txtProjectCity.setText(" " + project.getCity());

        ImageView imgProjectPicture = findViewById(R.id.imgReadMoreProjectPic);
        Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        imgProjectPicture.setImageBitmap(bmp);
    }

    public void btnSubmit_onClick(View v)
    {
        EditText editBudget = findViewById(R.id.editReadMoreBudget);
        double budget = 0;
        try
        {
            budget = Double.parseDouble(editBudget.getText().toString());
        }
        catch (Exception e)
        {
        }

        if (budget > 0)
        {

        }
        else
        {

        }
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
