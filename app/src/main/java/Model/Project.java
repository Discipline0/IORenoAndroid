package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project implements Serializable {
    private int    projectID;
    private String customerEmail;
    private String projectDescription;
    private String projectType;
    private double projectBudget;
    private String title;
    private String address;
    private String city;
    private byte[] image;
    private String datePosted;

public static final String PROJECT_TABLE_NAME      = "Project";

public static final String PROJECT_COL_ID          = "Project_ID";
public static final String PROJECT_COL_CUST_EMAIL  = "Project_Customer_Email";
public static final String PROJECT_COL_DESCRIPTION = "Project_Description";
public static final String PROJECT_COL_TYPE        = "Project_Type";
public static final String PROJECT_COL_BUDGET      = "Project_Budget";
public static final String PROJECT_COL_TITLE       = "Project_Title";
public static final String PROJECT_COL_ADDRESS     = "Project_Address";
public static final String PROJECT_COL_CITY        = "Project_City";
public static final String PROJECT_COL_IMAGE       = "Project_Image";
public static final String PROJECT_COL_DATE_POSTED = "Project_Date_Posted";

public Project(){};


    public Project(String customerEmail, String projectDescription, String projectType, double projectBudget, String title, String address, String city, byte[] image) {
        this.projectID = 0;
        this.customerEmail = customerEmail;
        this.projectDescription = projectDescription;
        this.projectType = projectType;
        this.projectBudget = projectBudget;
        this.title = title;
        this.address = address;
        this.city = city;
        this.image = image;
        this.datePosted = new SimpleDateFormat("yyyy-MM-dd:mm:ss").format(new Date());
    }

    public Project(int projectID, String customerEmail, String projectDescription, String projectType, double projectBudget, String title, String address, String city, byte[] image) {
        this.projectID = projectID;
        this.customerEmail = customerEmail;
        this.projectDescription = projectDescription;
        this.projectType = projectType;
        this.projectBudget = projectBudget;
        this.title = title;
        this.address = address;
        this.city = city;
        this.image = image;
        this.datePosted = new SimpleDateFormat("yyyy-MM-dd:mm:ss").format(new Date());
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(double projectBudget) {
        this.projectBudget = projectBudget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }
}
