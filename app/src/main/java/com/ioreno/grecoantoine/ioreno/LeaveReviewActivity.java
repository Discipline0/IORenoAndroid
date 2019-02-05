package com.ioreno.grecoantoine.ioreno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class LeaveReviewActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);

        RatingBar ratingBarReviewValue = findViewById(R.id.ratingBarReviewValue);
        ratingBarReviewValue.setNumStars(5);
        ratingBarReviewValue.setMax(5);
        ratingBarReviewValue.setStepSize(1);
    }
}
