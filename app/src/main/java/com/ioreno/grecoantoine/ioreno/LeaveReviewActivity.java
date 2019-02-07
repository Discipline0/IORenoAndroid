package com.ioreno.grecoantoine.ioreno;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.DBManager.DBSQLiteManager;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Customer;
import com.ioreno.grecoantoine.ioreno.Model.Review;

public class LeaveReviewActivity extends AppCompatActivity
{
    private Contractor contractor;
    private Customer customer;
    private DBSQLiteManager manager;
    private RatingBar ratingBarReviewValue;
    private EditText editReviewText;
    private TextView txtErrorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_review);

        manager = new DBSQLiteManager(this);

        Intent intent = getIntent();
        contractor = (Contractor) intent.getSerializableExtra("contractor");
        customer = manager.getCustomerFromEmail(Customer.currUser);
        ratingBarReviewValue = findViewById(R.id.ratingBarReviewValue);
        editReviewText = findViewById(R.id.editReviewText);
        txtErrorMsg = findViewById(R.id.txtLeaveReviewError);
        txtErrorMsg.setVisibility(View.GONE);
    }

    public void btnSubmitReview_onClick(View v)
    {
        final Context context = v.getContext();

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        // The Yes button clicked
                        if (ratingBarReviewValue.getRating() < 1 || ratingBarReviewValue.getRating() > 5)
                        {
                            txtErrorMsg.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            txtErrorMsg.setVisibility(View.GONE);

                            Review review = new Review(
                                contractor.getContractorCONum(),
                                customer.getCustomerID(),
                                (int) ratingBarReviewValue.getRating(),
                                editReviewText.getText().toString());

                            manager.insertOrReplaceReview(review);
                            Toast.makeText(getApplicationContext(), "Your review has been added", Toast.LENGTH_LONG).show();

                            LeaveReviewActivity leaveReviewActivity = (LeaveReviewActivity) context;
                            leaveReviewActivity.finish();
                        }

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // The No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Are you sure?\n\nYou are about to leave a review. If you already left a review for this contractor it will replace the existing one.\nDo you want to continue?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void btnGoBack_onClick(View v)
    {
        super.finish();
    }
}
