package com.ioreno.grecoantoine.ioreno.Model;

public class Proposal {
    private int    proposalID;
    private long   contractorCONum;
    private int    projectID;
    private double projectEstimate;
    private int    proposalApproved; // 0 => Denied, 1 => Accepted, 2 => Pending

    public static final String PROPOSAL_TABLE_NAME ="Proposal";

    public static final String PROPOSAL_ID                = "Proposal_ID";
    public static final String PROPOSAL_CONTRACTOR_CO_NUM = "Proposal_Contractor_CO_Num";
    public static final String PROPOSAL_PROJECT_ID        = "Proposal_Project_ID";
    public static final String PROPOSAL_PROJECT_ESTIMATE  = "Proposal_Project_Estimate";
    public static final String PROPOSAL_APPROVED          = "Proposal_Approved";

    public Proposal(){};

    public Proposal(int proposalID, long contractorCONum, int projectID, double projectEstimate) {
        this.proposalID = proposalID;
        this.contractorCONum = contractorCONum;
        this.projectID = projectID;
        this.projectEstimate = projectEstimate;
        // pending by default;
        this.proposalApproved = 2;
    }

    public int getProposalID() {
        return proposalID;
    }

    public void setProposalID(int proposalID) {
        this.proposalID = proposalID;
    }

    public long getContractorCONum() {
        return contractorCONum;
    }

    public void setContractorCONum(long contractorCONum) {
        this.contractorCONum = contractorCONum;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public double getProjectEstimate() {
        return projectEstimate;
    }

    public void setProjectEstimate(double projectEstimate) {
        this.projectEstimate = projectEstimate;
    }

    public int getProposalApproved() {
        return proposalApproved;
    }

    public void setProposalApproved(int proposalApproved) {
        this.proposalApproved = proposalApproved;
    }
}
