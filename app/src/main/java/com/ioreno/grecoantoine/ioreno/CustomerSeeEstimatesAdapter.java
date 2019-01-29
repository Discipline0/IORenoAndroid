package com.ioreno.grecoantoine.ioreno;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import DBManager.DBSQLiteManager;
import Model.Contractor;
import Model.Proposal;

public class CustomerSeeEstimatesAdapter extends RecyclerView.Adapter<CustomerSeeEstimatesAdapter.ViewHolder>
{
    DBSQLiteManager manager;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtCOName;
        TextView txtEstimate;
        Button btnSeeReviews;
        Button btnSelect;

        public ViewHolder(View itemView)
        {
            super(itemView);
            txtCOName = itemView.findViewById(R.id.txtCustSeeEstimateConName);
            txtEstimate = itemView.findViewById(R.id.txtCustSeeEstimateConEstimate);
            btnSeeReviews = itemView.findViewById(R.id.btnCustSeeEstimateSeeReviews);
            btnSelect = itemView.findViewById(R.id.btnCustSeeEstimateSelect);
        }
    }

    private List<Proposal> mProposals;

    public CustomerSeeEstimatesAdapter(List<Proposal> proposals)
    {
        mProposals = proposals;
    }

    @Override
    public CustomerSeeEstimatesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_customer_see_estimates_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomerSeeEstimatesAdapter.ViewHolder viewHolder, int position)
    {
        final Proposal proposal = mProposals.get(position);
        manager = new DBSQLiteManager(viewHolder.txtCOName.getContext());
        final Contractor contractor = manager.getContractorFromCoNum(proposal.getContractorCONum());

        viewHolder.txtCOName.setText(contractor.getContractorCOName());
        final String estimateStr = new DecimalFormat("$#,##0.00").format(proposal.getProjectEstimate());
        viewHolder.txtEstimate.setText(estimateStr);

        // See Reviews Button ---------------------------------------------------------------
        viewHolder.btnSeeReviews.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // To Implement once we do the Reviews
//                Intent intent = new Intent(v.getContext(), ContractorReadMoreActivity.class);
//                intent.putExtra("project", project);

//                v.getContext().startActivity(intent);
            }
        });

        // Select Button --------------------------------------------------------------------
        viewHolder.btnSelect.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Context context = v.getContext();

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // The Yes button clicked
                                manager.acceptProposal(proposal);
                                Toast.makeText(
                                    context,
                                    "You have confirmed your estimate! The contractor will contact you soon.",
                                    Toast.LENGTH_LONG).show();

                                CustomerSeeEstimatesActivity activity = (CustomerSeeEstimatesActivity) context;
                                activity.finish();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                // The No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure?\n\nAre you sure this is the estimate you wish to select?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount()
    {
        return mProposals.size();
    }
}