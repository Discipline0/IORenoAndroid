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

import Model.Project;

public class ViewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Intent intent = getIntent();
        Project project = (Project) intent.getSerializableExtra("project");

        TextView txtProjectTitle = findViewById(R.id.txtViewProjectProjectTitle);
        TextView txtProjectType = findViewById(R.id.txtViewProjectProjectTypeValue);
        TextView txtProjectBudget = findViewById(R.id.txtViewProjectProjectBudgetValue);
        TextView txtProjectDescription = findViewById(R.id.txtViewProjectProjectDescription);
        TextView txtProjectAddress = findViewById(R.id.txtViewProjectProjectAddressValue);
        TextView txtProjectCity = findViewById(R.id.txtViewProjectProjectCityValue);

        txtProjectTitle.setText(project.getTitle());
        txtProjectType.setText(" " + project.getProjectType());
        txtProjectBudget.setText(" " + new DecimalFormat("$#,##0.00").format(project.getProjectBudget()));
        txtProjectDescription.setText(project.getProjectDescription());
        txtProjectAddress.setText(" " + project.getAddress());
        txtProjectCity.setText(" " + project.getCity());

        ImageView imgProjectPicture = findViewById(R.id.imgViewProjectProjectPic);
        Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        imgProjectPicture.setImageBitmap(bmp);
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
