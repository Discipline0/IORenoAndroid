package com.ioreno.grecoantoine.ioreno.Model;

public class Review
{
    private int contractorNo;
    private int customerId;
    private int rating;
    private String reviewText;

    public static final String REVIEW_TABLE_NAME      = "Review";

    public static final String REVIEW_COL_CON_NO      = "Contractor_CO_Num";
    public static final String REVIEW_COL_CUST_ID     = "Customer_ID";
    public static final String REVIEW_COL_Rating      = "Review_Rating";
    public static final String REVIEW_COL_REVIEW_TEXT = "Review_Text";

    public Review(int contractorNo, int customerId, int rating, String reviewText)
    {
        this.contractorNo = contractorNo;
        this.customerId   = customerId;
        this.rating       = rating;
        this.reviewText   = reviewText;
    }

    public int getContractorNo()
    {
        return contractorNo;
    }

    public void setContractorNo(int contractorNo)
    {
        this.contractorNo = contractorNo;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getReviewText()
    {
        return reviewText;
    }

    public void setReviewText(String reviewText)
    {
        this.reviewText = reviewText;
    }
}
