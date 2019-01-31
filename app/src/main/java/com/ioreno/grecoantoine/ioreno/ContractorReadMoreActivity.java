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
import android.widget.Toast;

import java.text.DecimalFormat;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Project;
import com.ioreno.grecoantoine.ioreno.Model.Proposal;

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
        TextView txtError = findViewById(R.id.txtReadMoreError);
        txtError.setText("");

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
            DBSQLiteManager manager = new DBSQLiteManager(this);
            Contractor contractor = manager.getContractorFromEmail(Contractor.currUser);

            Proposal alreadyExistingProposal = manager.didContractorAlreadyMadeProposal(contractor, project);

            if(alreadyExistingProposal == null)
            {
                Proposal proposal = new Proposal(0, contractor.getContractorCONum(), project.getProjectID(), budget);
                manager.addProposal(proposal);
                Toast.makeText(getApplicationContext(), "The estimate has been submitted", Toast.LENGTH_LONG).show();
            }
            else
            {
                Proposal proposal = new Proposal(alreadyExistingProposal.getProposalID(), contractor.getContractorCONum(), project.getProjectID(), budget);
                manager.replaceProposal(proposal);
                Toast.makeText(getApplicationContext(), "The estimate has been updated", Toast.LENGTH_LONG).show();
            }
            
            super.finish();
        }
        else
        {
            txtError.setText("The Budget cannot be smaller than 0");
        }
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
