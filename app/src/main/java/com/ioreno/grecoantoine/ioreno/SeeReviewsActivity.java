package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;

import java.text.DecimalFormat;

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

        DBSQLiteManager manager = new DBSQLiteManager(this);
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
    }
}
