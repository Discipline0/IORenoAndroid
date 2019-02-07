package com.ioreno.grecoantoine.ioreno.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contractor implements Serializable {
    private int     contractorCONum;
    private String  contractorCOName;
    private String  contractorPhone;
    private String  contractorEmail;
    private String  contractorContactName;
    private String  contractorPassword;
    private String  contractorDateRegistered;
    //set 0 for pending, 1 is approved, 2 is denied
    private int     approved;



    public static boolean isLoggedIn = false;
    public static String currUser="";

    public static final String CONTRACTOR_TABLE_NAME = "Contractor";

    public static final String CONTRACTOR_COL_CO_NUM = "Contractor_CO_Num";
    public static final String CONTRACTOR_COL_CO_NAME = "Contractor_CO_Name";
    public static final String CONTRACTOR_COL_PHONE = "Contractor_Phone";
    public static final String CONTRACTOR_COL_EMAIL= "Contractor_Email";
    public static final String CONTRACTOR_COL_CONTACT_NAME = "Contractor_Contact_Name";
    public static final String CONTRACTOR_COL_PASSWORD = "Contractor_Password";
    public static final String CONTRACTOR_COL_DATE_REGISTERED = "Contractor_Date_Registered";
    public static final String CONTRACTOR_COL_APPROVED = "Approved";

    public Contractor(){};

    public Contractor(int contractorCONum, String contractorCOName, String contractorPhone, String contractorEmail, String contractorContactName, String contractorPassword) {
        this.contractorCONum = contractorCONum;
        this.contractorCOName = contractorCOName;
        this.contractorPhone = contractorPhone;
        this.contractorEmail = contractorEmail;
        this.contractorContactName = contractorContactName;
        this.contractorPassword = contractorPassword;
        this.contractorDateRegistered = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //set 0 for pending, 1 is approved, 2 is denied
        this.approved = 0;
    }

    public int getContractorCONum() {
        return contractorCONum;
    }

    public void setContractorCONum(int contractorCONum) {
        this.contractorCONum = contractorCONum;
    }

    public String getContractorCOName() {
        return contractorCOName;
    }

    public void setContractorCOName(String contractorCOName) {
        this.contractorCOName = contractorCOName;
    }

    public String getContractorPhone() {
        return contractorPhone;
    }

    public void setContractorPhone(String contractorPhone) {
        this.contractorPhone = contractorPhone;
    }

    public String getContractorEmail() {
        return contractorEmail;
    }

    public void setContractorEmail(String contractorEmail) {
        this.contractorEmail = contractorEmail;
    }

    public String getContractorContactName() {
        return contractorContactName;
    }

    public void setContractorContactName(String contractorContactName) {
        this.contractorContactName = contractorContactName;
    }

    public String getContractorPassword() {
        return contractorPassword;
    }

    public void setContractorPassword(String contractorPassowrd) {
        this.contractorPassword = contractorPassowrd;
    }

    public String getContractorDateRegistered() {
        return contractorDateRegistered;
    }

    public void setContractorDateRegistered(String contractorDateRegistered) {
        this.contractorDateRegistered = contractorDateRegistered;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }
}
