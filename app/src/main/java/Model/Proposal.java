package Model;

public class Proposal {
    private int    proposalID;
    private int    contractorCONum;
    private int    projectID;
    private double projectEstimate;
    private int    proposalApproved;

    public static final String PROPOSAL_TABLE_NAME ="Proposal";

    public static final String PROPOSAL_ID                = "Proposal_ID";
    public static final String PROPOSAL_CONTRACTOR_CO_NUM = "Proposal_Contractor_CO_Num";
    public static final String PROPOSAL_PROJECT_ID        = "Proposal_Project_ID";
    public static final String PROPOSAL_PROJECT_ESTIMATE  = "Proposal_Project_Estimate";
    public static final String PROPOSAL_APPROVED          = "Proposal_Approved";

    public Proposal(){};

    public Proposal(int proposalID, int contractorCONum, int projectID, double projectEstimate, int proposalApproved) {
        this.proposalID = proposalID;
        this.contractorCONum = contractorCONum;
        this.projectID = projectID;
        this.projectEstimate = projectEstimate;
        //not approved by default;
        this.proposalApproved = 0;
    }

    public int getProposalID() {
        return proposalID;
    }

    public void setProposalID(int proposalID) {
        this.proposalID = proposalID;
    }

    public int getContractorCONum() {
        return contractorCONum;
    }

    public void setContractorCONum(int contractorCONum) {
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
