package com.ioreno.grecoantoine.ioreno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Proposal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ContractorSeeEstimatesActivity extends AppCompatActivity {
    private ArrayList<Proposal> proposals;
    private DBSQLiteManager manager;
    private ContractorSeeEstimatesAdapter adapter;
    private MultiSelectionSpinner spinner;
    private RecyclerView recyclerView;
    private TextView txtNoEstimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_see_estimates);

        spinner = findViewById(R.id.spinnerEstimateStatus);
        spinner.setItems(new String[] {"Approved", "Pending", "Denied"});

        txtNoEstimate = findViewById(R.id.txtConSeeEstimatesNoEstimatesYet);

        recyclerView = findViewById(R.id.recyclerViewConSeeEstimates);

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        manager = new DBSQLiteManager(this);

        proposals = manager.getProposalListForContractor(manager.getContractorFromEmail(Contractor.currUser));

        if (proposals.size() == 0)
        {
            recyclerView.setVisibility(View.GONE);
            txtNoEstimate.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);
            txtNoEstimate.setVisibility(View.GONE);

            adapter = new ContractorSeeEstimatesAdapter(proposals);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void btnStatusSearch_onClick(View v)
    {
        List<String> selectedItems = spinner.getSelectedStrings();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectedItems.size(); i++)
        {
            if (!selectedItems.get(i).matches(" *")) //empty string are ""; " "; "  "; and so on
            {
                if ("Approved".equals(selectedItems.get(i)))
                {
                    sb.append("1");
                }
                else if ("Pending".equals(selectedItems.get(i)))
                {
                    sb.append("2");
                }
                else if ("Denied".equals(selectedItems.get(i)))
                {
                    sb.append("0");
                }

                if (i < selectedItems.size() - 1)
                {
                    sb.append("', '");
                }
            }
        }

        String selectedStatus = sb.toString();

        proposals = selectedStatus.equals("")
                ? manager.getProposalListForContractor(manager.getContractorFromEmail(Contractor.currUser))
                : manager.getProposalListForContractorAndStatus(manager.getContractorFromEmail(Contractor.currUser), selectedStatus);

        if (proposals.size() == 0)
        {
            recyclerView.setVisibility(View.GONE);
            txtNoEstimate.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);
            txtNoEstimate.setVisibility(View.GONE);

            adapter = new ContractorSeeEstimatesAdapter(proposals);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
