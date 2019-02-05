package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import DBManager.DBSQLiteManager;
import Model.Contractor;
import Model.Customer;
import Model.Project;
import Model.Proposal;

public class CustomerSeeEstimatesActivity extends AppCompatActivity {

    private ArrayList<Proposal> proposals;
    private DBSQLiteManager manager;
    private CustomerSeeEstimatesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_see_estimates);

        TextView txtNoEstimate = findViewById(R.id.txtCustSeeEstimateNoEstimate);
        LinearLayout layoutAcceptedEstimate = findViewById(R.id.layoutAcceptedEstimate);
        layoutAcceptedEstimate.setVisibility(View.GONE);

        Intent intent = getIntent();
        Project project = (Project) intent.getSerializableExtra("project");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCustEstimates);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        manager = new DBSQLiteManager(this);

        proposals = manager.getProposalListForProject(project);

        if (proposals.size() == 0)
        {
            txtNoEstimate.setVisibility(View.VISIBLE);
        }
        else
        {
            txtNoEstimate.setVisibility(View.GONE);

            boolean isThereAProposalAccepted = false;
            for (Proposal p : proposals)
            {
                if (p.getProposalApproved() == 1)
                {
                    final Contractor contractor = manager.getContractorFromCoNum(p.getContractorCONum());

                    TextView txtAcceptedEstimate = findViewById(R.id.txtCustSeeEstimateSelectedEstimate);
                    TextView txtAcceptedContractor = findViewById(R.id.txtCustSeeEstimateSelectedCon);
                    Button btnLeaveReview = findViewById(R.id.btnCustSeeEstimateLeaveReview);

                    txtAcceptedEstimate.setText(new DecimalFormat("$#,##0.00").format(p.getProjectEstimate()));
                    txtAcceptedContractor.setText(contractor.getContractorCOName());

                    btnLeaveReview.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Intent i = new Intent(v.getContext(), LeaveReviewActivity.class);
                            i.putExtra("contractor", contractor);

                            startActivity(i);
                        }
                    });

                    layoutAcceptedEstimate.setVisibility(View.VISIBLE);

                    isThereAProposalAccepted = true;
                    break;
                }
            }

            if(!isThereAProposalAccepted)
            {
                adapter = new CustomerSeeEstimatesAdapter(proposals);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        }
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
