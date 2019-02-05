package com.ioreno.grecoantoine.ioreno;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Project;
import com.ioreno.grecoantoine.ioreno.Model.Proposal;

public class ContractorSeeEstimatesAdapter extends RecyclerView.Adapter<ContractorSeeEstimatesAdapter.ViewHolder>
{
    DBSQLiteManager manager;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgProjectImage;
        TextView txtProjectTitle;
        TextView txtEstimate;
        TextView txtStatus;
        Button btnGetCustInfo;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imgProjectImage = itemView.findViewById(R.id.imgConSeeEstimatesProjectPic);
            txtProjectTitle = itemView.findViewById(R.id.txtConSeeEstimatesProjectTitle);
            txtEstimate = itemView.findViewById(R.id.txtConSeeEstimatesEstimate);
            txtStatus = itemView.findViewById(R.id.txtConSeeEstimatesStatus);
            btnGetCustInfo = itemView.findViewById(R.id.btnConSeeEstimatesGetCustInfo);
        }
    }

    private List<Proposal> mProposals;

    public ContractorSeeEstimatesAdapter(List<Proposal> proposals)
    {
        mProposals = proposals;
    }

    @Override
    public ContractorSeeEstimatesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_contractor_see_estimates_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContractorSeeEstimatesAdapter.ViewHolder viewHolder, int position)
    {
        final Proposal proposal = mProposals.get(position);
        manager = new DBSQLiteManager(viewHolder.txtProjectTitle.getContext());
        final Project project = manager.getProjectFromId(proposal.getProjectID());

        viewHolder.txtProjectTitle.setText(project.getTitle());
        final String estimateStr = new DecimalFormat("$#,##0.00").format(proposal.getProjectEstimate());
        viewHolder.txtEstimate.setText(estimateStr);

        // Converting the Byte Array into Bitmap -----------------------------------------------
        Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        viewHolder.imgProjectImage.setImageBitmap(bmp);

        viewHolder.btnGetCustInfo.setVisibility(View.GONE);

        switch (proposal.getProposalApproved())
        {
            case 0:
                viewHolder.txtStatus.setText("Denied");
                viewHolder.txtStatus.setTextColor(Color.parseColor("#FF0000"));
                break;
            case 1:
                viewHolder.txtStatus.setText("Approved");
                viewHolder.txtStatus.setTextColor(Color.parseColor("#00FF00"));
                viewHolder.btnGetCustInfo.setVisibility(View.VISIBLE);
                break;
            case 2:
                viewHolder.txtStatus.setText("Pending");
                viewHolder.txtStatus.setTextColor(Color.parseColor("#EC971F"));
                break;
        }

        // Get Customer Information Button ---------------------------------------------------------------
        viewHolder.btnGetCustInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), GetCustomerInfoActivity.class);
                intent.putExtra("project", project);

                v.getContext().startActivity(intent);
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