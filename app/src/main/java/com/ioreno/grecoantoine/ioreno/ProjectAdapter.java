package com.ioreno.grecoantoine.ioreno;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import Model.Project;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>
{

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTitle;
        TextView txtPrice;

        public ViewHolder(View itemView)
        {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtProjectTitle);
            txtPrice = itemView.findViewById(R.id.txtProjectPrice);
        }
    }

    private List<Project> mProjects;

    public ProjectAdapter(List<Project> projects)
    {
        mProjects = projects;
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_customer_project_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.ViewHolder viewHolder, int position)
    {
        final Project project = mProjects.get(position);

        viewHolder.txtTitle.setText(project.getTitle());
        final String priceStr = new DecimalFormat("$#,##0.00").format(project.getProjectBudget());
        viewHolder.txtPrice.setText(priceStr);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount()
    {
        return mProjects.size();
    }
}