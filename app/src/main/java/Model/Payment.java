package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private int    paymentID;
    private int    contractorCONum;
    private double paymentAmount;
    private int    proposalID;
    private int    paymentStatus;
    private String paymentDate;

    public static final String PAYMENT_TABLE_NAME ="Payment";

    public static final String PAYMENT_ID                = "Payment_ID";
    public static final String PAYMENT_CONTRACTOR_CO_NUM = "Payment_Contractor_CO_Num";
    public static final String PAYMENT_AMOUNT            = "Payment_Amount";
    public static final String PAYMENT_PROPOSAL_ID       = "Payment_Proposal_ID";
    public static final String PAYMENT_STATUS            = "Payment_Status";
    public static final String PAYMENT_DATE              = "Payment_Date";

    public Payment(){};

    public Payment(int paymentID, int contractorCONum, double paymentAmount, int proposalID, int paymentStatus) {
        this.paymentID = paymentID;
        this.contractorCONum = contractorCONum;
        this.paymentAmount = paymentAmount;
        this.proposalID = proposalID;
        this.paymentStatus = paymentStatus;
        this.paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getContractorCONum() {
        return contractorCONum;
    }

    public void setContractorCONum(int contractorCONum) {
        this.contractorCONum = contractorCONum;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getProposalID() {
        return proposalID;
    }

    public void setProposalID(int proposalID) {
        this.proposalID = proposalID;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
