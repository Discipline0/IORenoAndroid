package com.ioreno.grecoantoine.ioreno;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Review;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SeeReviewsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_reviews);

        TextView txtContractorCompanyName = findViewById(R.id.txtSeeReviewConCompName);
        TextView txtStarRating = findViewById(R.id.txtSeeReviewRating);
        RatingBar ratingBarAverageRating = findViewById(R.id.ratingBarSeeReviewAverage);
        TextView txtNumRating = findViewById(R.id.txtSeeReviewNumberRating);

        Intent intent = getIntent();
        Contractor contractor = (Contractor) intent.getSerializableExtra("contractor");

        final DBSQLiteManager manager = new DBSQLiteManager(this);
        double[] reviewCountRating = manager.getReviewCountAndRatingForContractor(contractor);

        txtContractorCompanyName.setText(contractor.getContractorCOName());

        String txtStarRatingText = new DecimalFormat("0.00").format(reviewCountRating[1]);
        txtStarRatingText += reviewCountRating[1] > 1 ? " stars" : " star";
        txtStarRatingText += " out of 5 (" + (int) reviewCountRating[0];
        txtStarRatingText += reviewCountRating[0] > 1 ? " ratings)" : " rating)";
        txtStarRating.setText(txtStarRatingText);

        ratingBarAverageRating.setIsIndicator(true);
        ratingBarAverageRating.setRating((float) reviewCountRating[1]);

        String txtNumRatingText = "Reviews (" + (int) reviewCountRating[0];
        txtNumRatingText += reviewCountRating[0] > 1 ? " reviews):" : " review):";
        txtNumRating.setText(txtNumRatingText);

        //table
        TableLayout tl = (TableLayout)findViewById(R.id.ReviewTable);

        //header
        TableRow tr_head = new TableRow(this);
        tr_head.setBackgroundColor(getResources().getColor(R.color.TableBlue));
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        //Header***************************************************
        TextView txtCustomerName = new TextView(this);
        txtCustomerName.setText("Customer Name");
        txtCustomerName.setTextColor(Color.BLACK);
        txtCustomerName.setPadding(5, 5, 30, 5);
        txtCustomerName.setTypeface(null, Typeface.BOLD);
        tr_head.addView(txtCustomerName);// add the column to the table row here

        TextView txtReviewText = new TextView(this);
        txtReviewText.setText("Comment");
        txtReviewText.setTextColor(Color.BLACK);
        txtReviewText.setPadding(5, 5, 30, 5);
        txtReviewText.setTypeface(null, Typeface.BOLD);
        tr_head.addView(txtReviewText);// add the column to the table row here

        if(AdminHome.isAdminLoggedIn)
        {
            TextView txtDeleteComment = new TextView(this);
            txtDeleteComment.setText("Delete Comment?");
            txtDeleteComment.setTextColor(Color.BLACK);
            txtDeleteComment.setPadding(5, 5, 30, 5);
            txtDeleteComment.setTypeface(null, Typeface.BOLD);
            tr_head.addView(txtDeleteComment);// add the column to the table row here
        }

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        //get review list for contractor
        ArrayList<Review> reviewList = manager.getReviewListForContractor(contractor);


        //use forloop to make changes to inner object
        for(int i = 0; i < reviewList.size(); i++){
            final Review review = reviewList.get(i);

            TableRow tr = new TableRow(this);
            if(i%2!=0) tr.setBackgroundColor(getResources().getColor(R.color.TableBlue));
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView lblCustName = new TextView(this);
            TextView lblReviewText = new TextView(this);
            Button btnDeleteReview = new Button(this);

            lblCustName.setText(manager.getCustomerFromId(review.getCustomerId()).getCustomerName());
            lblCustName.setPadding(2, 0, 5, 0);
            lblCustName.setTextColor(Color.BLACK);
            tr.addView(lblCustName);

            lblReviewText.setText(review.getReviewText());
            lblReviewText.setPadding(2, 0, 5, 0);
            lblReviewText.setTextColor(Color.BLACK);
            tr.addView(lblReviewText);

            if(AdminHome.isAdminLoggedIn)
            {
                btnDeleteReview.setText("Delete Review");
                btnDeleteReview.setPadding(2, 0, 5, 0);
                btnDeleteReview.setTextColor(Color.WHITE);
                btnDeleteReview.setBackgroundTintList(getResources().getColorStateList(R.color.Red));
                btnDeleteReview.setOnClickListener(new View.OnClickListener()
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
                                        manager.deleteReview(review);
                                        Toast.makeText(getApplicationContext(), "The reivew has successfully been delete.", Toast.LENGTH_LONG).show();

                                        // Refresh the CustomerHome page
                                        SeeReviewsActivity seeReviewsActivity = (SeeReviewsActivity) context;
                                        seeReviewsActivity.finish();
                                        seeReviewsActivity.startActivity(seeReviewsActivity.getIntent());
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        // The No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Are you sure?\n\nAre you sure you want to delete this review?")
                                .setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    }
                });

                tr.addView(btnDeleteReview);
            }

            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
