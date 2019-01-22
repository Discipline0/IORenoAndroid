package com.ioreno.grecoantoine.ioreno;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import DBManager.DBSQLiteManager;
import Model.Project;

public class ProjectContractorHomeAdapter extends RecyclerView.Adapter<ProjectContractorHomeAdapter.ViewHolder>
{
    DBSQLiteManager manager;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTitle;
        TextView txtDescription;
        TextView txtBudget;
        ImageView projectImage;
        Button btnReadMore;

        public ViewHolder(View itemView)
        {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtConHomeProjectTitle);
            txtDescription = itemView.findViewById(R.id.txtConHomeProjectDescription);
            txtBudget = itemView.findViewById(R.id.txtConHomeProjectBudget);
            projectImage = itemView.findViewById(R.id.imgConHomeProjectPic);
            btnReadMore = itemView.findViewById(R.id.btnConHomeReadMore);
        }
    }

    private List<Project> mProjects;

    public ProjectContractorHomeAdapter(List<Project> projects)
    {
        mProjects = projects;
    }

    @Override
    public ProjectContractorHomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.activity_contractor_project_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProjectContractorHomeAdapter.ViewHolder viewHolder, int position)
    {
        final Project project = mProjects.get(position);
        manager = new DBSQLiteManager(viewHolder.projectImage.getContext());

        viewHolder.txtTitle.setText(project.getTitle());
        viewHolder.txtDescription.setText(project.getProjectDescription());
        final String priceStr = new DecimalFormat("$#,##0.00").format(project.getProjectBudget());
        viewHolder.txtBudget.setText(priceStr);

        // Converting the Byte Array into Bitmap -----------------------------------------------
        Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        viewHolder.projectImage.setImageBitmap(bmp);

        // Read More Button --------------------------------------------------------------------
        viewHolder.btnReadMore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                Intent intent = new Intent(v.getContext(), ViewProjectActivity.class);
//                intent.putExtra("project", project);

//                v.getContext().startActivity(intent);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount()
    {
        return mProjects.size();
    }
}