package com.ioreno.grecoantoine.ioreno;

import android.content.Context;
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

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>
{
    DBSQLiteManager manager;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTitle;
        TextView txtPrice;
        ImageView projectImage;
        Button btnView;
        Button btnSeeEstimates;
        Button btnEdit;
        Button btnDelete;

        public ViewHolder(View itemView)
        {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtProjectTitle);
            txtPrice = itemView.findViewById(R.id.txtProjectPrice);
            projectImage = itemView.findViewById(R.id.imageProjectPic);
            btnView = itemView.findViewById(R.id.btnView);
            btnSeeEstimates = itemView.findViewById(R.id.btnSeeEstimates);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
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
        manager = new DBSQLiteManager(viewHolder.projectImage.getContext());

        viewHolder.txtTitle.setText(project.getTitle());
        final String priceStr = new DecimalFormat("$#,##0.00").format(project.getProjectBudget());
        viewHolder.txtPrice.setText(priceStr);

        // Converting the Byte Array into Bitmap -----------------------------------------------
        Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
        viewHolder.projectImage.setImageBitmap(bmp);

        // Setting the onclick action of each button -------------------------------------------
        // View Button
        viewHolder.btnView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        // See Estimates Button
        viewHolder.btnSeeEstimates.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        // Edit Button
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        // Delete Button
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(manager.deleteProject(project))
                {
                    Toast.makeText(
                        v.getContext(),
                        project.getTitle() + " was successfully deleted.",
                        Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(
                        v.getContext(),
                        "An error occurred while trying to delete: " + project.getTitle(),
                        Toast.LENGTH_LONG).show();
                }

                // Refresh the CustomerHome page
                CustomerHome customerHome = (CustomerHome) v.getContext();
                customerHome.finish();
                customerHome.startActivity(customerHome.getIntent());
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