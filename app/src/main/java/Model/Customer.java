package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private int    customerID;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerPassword;
    private String customerDateRegistered;

    public static boolean isLoggedIn = false;
    public static String currUser;

    public static final String CUSTOMER_TABLE_NAME          = "Customer";

    public static final String CUSTOMER_COL_ID              = "Customer_ID";
    public static final String CUSTOMER_COL_NAME            = "Customer_Name";
    public static final String CUSTOMER_COL_EMAIL           = "Customer_Email";
    public static final String CUSTOMER_COL_PHONE           = "Customer_Phone";
    public static final String CUSTOMER_COL_PASSWORD        = "Customer_Password";
    public static final String CUSTOMER_COL_DATE_REGISTERED = "Customer_Date_Registered";

    public Customer(){}


    public Customer(int customerID, String customerName, String customerEmail, String customerPhone, String customerPassword) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerPassword = customerPassword;
        //CURRENT_DATE assigned upon
        this.customerDateRegistered = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerDateRegistered() {
        return customerDateRegistered;
    }

    public void setCustomerDateRegistered(String customerDateRegistered) {
        this.customerDateRegistered = customerDateRegistered;
    }

}
