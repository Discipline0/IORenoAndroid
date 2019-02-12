package com.ioreno.grecoantoine.ioreno.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.ioreno.grecoantoine.ioreno.Model.Customer;
import com.ioreno.grecoantoine.ioreno.Model.Contractor;
import com.ioreno.grecoantoine.ioreno.Model.Payment;
import com.ioreno.grecoantoine.ioreno.Model.Project;
import com.ioreno.grecoantoine.ioreno.Model.Proposal;
import com.ioreno.grecoantoine.ioreno.Model.Review;
import com.ioreno.grecoantoine.ioreno.R;

public class DBSQLiteManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "IOReno_Android_SQLite_DB";
    public DBSQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Customer *************************************************************************************
    public static final String CREATE_CUSTOMER_TABLE_QUERY = "CREATE TABLE " + Customer.CUSTOMER_TABLE_NAME + " (" +
            Customer.CUSTOMER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Customer.CUSTOMER_COL_NAME + " TEXT," +
            Customer.CUSTOMER_COL_EMAIL + " TEXT," +
            Customer.CUSTOMER_COL_PHONE + " TEXT," +
            Customer.CUSTOMER_COL_PASSWORD + " TEXT," +
            Customer.CUSTOMER_COL_DATE_REGISTERED + " TEXT" +
            ")";

    public void addCustomer(Customer c)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Customer.CUSTOMER_COL_NAME, c.getCustomerName());
        vals.put(Customer.CUSTOMER_COL_EMAIL, c.getCustomerEmail());
        vals.put(Customer.CUSTOMER_COL_PHONE, c.getCustomerPhone());
        vals.put(Customer.CUSTOMER_COL_PASSWORD, c.getCustomerPassword());
        vals.put(Customer.CUSTOMER_COL_DATE_REGISTERED, c.getCustomerDateRegistered());

        db.insert(Customer.CUSTOMER_TABLE_NAME,null, vals);

        db.close();
    }

    public ArrayList<Customer> getCustomerList()
    {
        ArrayList<Customer> list = new ArrayList<Customer>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Customer.CUSTOMER_TABLE_NAME+";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Customer cus = new Customer();
                cus.setCustomerID(c.getInt(c.getColumnIndex(Customer.CUSTOMER_COL_ID)));
                cus.setCustomerName(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_NAME)));
                cus.setCustomerEmail(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_EMAIL)));
                cus.setCustomerPhone(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_PHONE)));
                cus.setCustomerPassword(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_PASSWORD)));
                cus.setCustomerDateRegistered(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_DATE_REGISTERED)));

                list.add(cus);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public Customer getCustomerFromEmail(String email)
    {
        Customer customer = new Customer();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Customer.CUSTOMER_TABLE_NAME+" WHERE " + Customer.CUSTOMER_COL_EMAIL
                + " = '" + email + "';";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            customer.setCustomerID(c.getInt(c.getColumnIndex(Customer.CUSTOMER_COL_ID)));
            customer.setCustomerName(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_NAME)));
            customer.setCustomerEmail(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_EMAIL)));
            customer.setCustomerPhone(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_PHONE)));
            customer.setCustomerPassword(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_PASSWORD)));
        }
        c.close();
        db.close();
        return customer;
    }

    public Customer getCustomerFromId(int customerId)
    {
        Customer customer = new Customer();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Customer.CUSTOMER_TABLE_NAME+" WHERE " + Customer.CUSTOMER_COL_ID
                + " = " + customerId + ";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            customer.setCustomerID(c.getInt(c.getColumnIndex(Customer.CUSTOMER_COL_ID)));
            customer.setCustomerName(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_NAME)));
            customer.setCustomerEmail(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_EMAIL)));
            customer.setCustomerPhone(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_PHONE)));
            customer.setCustomerPassword(c.getString(c.getColumnIndex(Customer.CUSTOMER_COL_PASSWORD)));
        }
        c.close();
        db.close();
        return customer;
    }

    public boolean checkForCustomer(String email, String password)
    {
        ArrayList<Customer> list = getCustomerList();
        boolean valid = false;
        for (int i = 0; i < list.size(); i++)
        {
            if (email.equals(list.get(i).getCustomerEmail()))
            {
                if (password.equals(list.get(i).getCustomerPassword()))
                {
                    valid = true;
                }
            }
        }
        return valid;

    }

    //Contractor ***********************************************************************************
    public static final String CREATE_CONTRACTOR_TABLE_QUERY = "CREATE TABLE " + Contractor.CONTRACTOR_TABLE_NAME + " (" +
            Contractor.CONTRACTOR_COL_CO_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Contractor.CONTRACTOR_COL_CO_NAME + " TEXT," +
            Contractor.CONTRACTOR_COL_PHONE + " TEXT," +
            Contractor.CONTRACTOR_COL_EMAIL + " TEXT," +
            Contractor.CONTRACTOR_COL_CONTACT_NAME + " TEXT," +
            Contractor.CONTRACTOR_COL_PASSWORD + " TEXT," +
            Contractor.CONTRACTOR_COL_DATE_REGISTERED + " TEXT," +
            Contractor.CONTRACTOR_COL_APPROVED + " INTEGER" +
            ")";

    public void addContractor(Contractor c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Contractor.CONTRACTOR_COL_CO_NAME, c.getContractorCOName());
        vals.put(Contractor.CONTRACTOR_COL_PHONE, c.getContractorPhone());
        vals.put(Contractor.CONTRACTOR_COL_EMAIL, c.getContractorEmail());
        vals.put(Contractor.CONTRACTOR_COL_CONTACT_NAME, c.getContractorContactName());
        vals.put(Contractor.CONTRACTOR_COL_PASSWORD, c.getContractorPassword());
        vals.put(Contractor.CONTRACTOR_COL_DATE_REGISTERED, c.getContractorDateRegistered());
        vals.put(Contractor.CONTRACTOR_COL_APPROVED, c.getApproved());

        db.insert(Contractor.CONTRACTOR_TABLE_NAME, null, vals);
        db.close();
    }

    public ArrayList<Contractor> getContractorList()
    {
        ArrayList<Contractor> list = new ArrayList<Contractor>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Contractor.CONTRACTOR_TABLE_NAME+";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Contractor con = new Contractor();
                con.setContractorCONum(c.getInt(c.getColumnIndex(Contractor.CONTRACTOR_COL_CO_NUM)));
                con.setContractorCOName(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_CO_NAME)));
                con.setContractorPhone(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_PHONE)));
                con.setContractorEmail(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_EMAIL)));
                con.setContractorContactName(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_CONTACT_NAME)));
                con.setContractorPassword(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_PASSWORD)));
                con.setContractorDateRegistered(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_DATE_REGISTERED)));
                con.setApproved(c.getInt(c.getColumnIndex(Contractor.CONTRACTOR_COL_APPROVED)));


                list.add(con);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public Contractor getContractorFromEmail(String email)
    {
        Contractor contractor = new Contractor();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Contractor.CONTRACTOR_TABLE_NAME+" WHERE " + Contractor.CONTRACTOR_COL_EMAIL
                + " = '" + email + "';";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                contractor.setContractorCONum(c.getInt(c.getColumnIndex(Contractor.CONTRACTOR_COL_CO_NUM)));
                contractor.setContractorCOName(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_CO_NAME)));
                contractor.setContractorPhone(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_PHONE)));
                contractor.setContractorEmail(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_EMAIL)));
                contractor.setContractorContactName(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_CONTACT_NAME)));
                contractor.setContractorPassword(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_PASSWORD)));
                contractor.setContractorDateRegistered(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_DATE_REGISTERED)));
                contractor.setApproved(c.getInt(c.getColumnIndex(Contractor.CONTRACTOR_COL_APPROVED)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return contractor;
    }

    public Contractor getContractorFromCoNum(int coNum)
    {
        Contractor contractor = new Contractor();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Contractor.CONTRACTOR_TABLE_NAME+" WHERE " + Contractor.CONTRACTOR_COL_CO_NUM
                + " = " + coNum + ";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                contractor.setContractorCONum(c.getInt(c.getColumnIndex(Contractor.CONTRACTOR_COL_CO_NUM)));
                contractor.setContractorCOName(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_CO_NAME)));
                contractor.setContractorPhone(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_PHONE)));
                contractor.setContractorEmail(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_EMAIL)));
                contractor.setContractorContactName(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_CONTACT_NAME)));
                contractor.setContractorPassword(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_PASSWORD)));
                contractor.setContractorDateRegistered(c.getString(c.getColumnIndex(Contractor.CONTRACTOR_COL_DATE_REGISTERED)));
                contractor.setApproved(c.getInt(c.getColumnIndex(Contractor.CONTRACTOR_COL_APPROVED)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return contractor;
    }

    public boolean checkForContractor(String email, String password)
    {
        ArrayList<Contractor> list = getContractorList();
        boolean valid = false;
        for (int i = 0; i < list.size(); i++)
        {
            if (email.equals(list.get(i).getContractorEmail()))
            {
                if (password.equals(list.get(i).getContractorPassword()))
                {
                    valid = true;
                }
            }
        }
        return valid;

    }

    public void approveContractor(Contractor con){
        Contractor contractor = new Contractor();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Contractor.CONTRACTOR_COL_APPROVED, 1);

        db.update(Contractor.CONTRACTOR_TABLE_NAME, vals, Contractor.CONTRACTOR_COL_CO_NUM+"="+ con.getContractorCONum(), null);

        db.close();
    }

    public void denyContractor(Contractor con){
        Contractor contractor = new Contractor();
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Contractor.CONTRACTOR_COL_APPROVED, 2);

        db.update(Contractor.CONTRACTOR_TABLE_NAME, vals, Contractor.CONTRACTOR_COL_CO_NUM+"="+ con.getContractorCONum(), null);

        db.close();
    }

    //Project**************************************************************************************
    public static final String CREATE_PROJECT_TABLE_QUERY = "CREATE TABLE " + Project.PROJECT_TABLE_NAME + " (" +
            Project.PROJECT_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Project.PROJECT_COL_CUST_EMAIL + " TEXT," +
            Project.PROJECT_COL_DESCRIPTION + " TEXT," +
            Project.PROJECT_COL_TYPE + " TEXT," +
            Project.PROJECT_COL_BUDGET + " REAL," +
            Project.PROJECT_COL_TITLE + " TEXT," +
            Project.PROJECT_COL_ADDRESS + " TEXT," +
            Project.PROJECT_COL_CITY + " TEXT," +
            Project.PROJECT_COL_IMAGE + " BLOB," +
            Project.PROJECT_COL_DATE_POSTED + " TEXT" +
            ")";

    public void addProject(Project p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Project.PROJECT_COL_CUST_EMAIL, p.getCustomerEmail());
        vals.put(Project.PROJECT_COL_DESCRIPTION, p.getProjectDescription());
        vals.put(Project.PROJECT_COL_TYPE, p.getProjectType());
        vals.put(Project.PROJECT_COL_BUDGET, p.getProjectBudget());
        vals.put(Project.PROJECT_COL_TITLE, p.getTitle());
        vals.put(Project.PROJECT_COL_ADDRESS, p.getAddress());
        vals.put(Project.PROJECT_COL_CITY, p.getCity());
        vals.put(Project.PROJECT_COL_IMAGE, p.getImage());
        vals.put(Project.PROJECT_COL_DATE_POSTED, p.getDatePosted());

        db.insert(Project.PROJECT_TABLE_NAME, null, vals);
        db.close();
    }

    public void editProject(Project p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Project.PROJECT_COL_CUST_EMAIL, p.getCustomerEmail());
        vals.put(Project.PROJECT_COL_DESCRIPTION, p.getProjectDescription());
        vals.put(Project.PROJECT_COL_TYPE, p.getProjectType());
        vals.put(Project.PROJECT_COL_BUDGET, p.getProjectBudget());
        vals.put(Project.PROJECT_COL_TITLE, p.getTitle());
        vals.put(Project.PROJECT_COL_ADDRESS, p.getAddress());
        vals.put(Project.PROJECT_COL_CITY, p.getCity());
        vals.put(Project.PROJECT_COL_IMAGE, p.getImage());
        vals.put(Project.PROJECT_COL_DATE_POSTED, p.getDatePosted());

        db.update(Project.PROJECT_TABLE_NAME, vals, Project.PROJECT_COL_ID+"="+p.getProjectID(), null);
        db.close();


    }

    public Project getProjectFromId(int projectId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Project.PROJECT_TABLE_NAME+" WHERE " + Project.PROJECT_COL_ID
                + " = " + projectId + ";";
        Cursor c = db.rawQuery(GET_LIST,null);
        Project project = new Project();

        if (c.moveToFirst())
        {
                project.setProjectID(c.getInt(c.getColumnIndex(Project.PROJECT_COL_ID)));
                project.setCustomerEmail(c.getString(c.getColumnIndex(Project.PROJECT_COL_CUST_EMAIL)));
                project.setProjectDescription(c.getString(c.getColumnIndex(Project.PROJECT_COL_DESCRIPTION)));
                project.setProjectType(c.getString(c.getColumnIndex(Project.PROJECT_COL_TYPE)));
                project.setProjectBudget(c.getDouble(c.getColumnIndex(Project.PROJECT_COL_BUDGET)));
                project.setTitle(c.getString(c.getColumnIndex(Project.PROJECT_COL_TITLE)));
                project.setAddress(c.getString(c.getColumnIndex(Project.PROJECT_COL_ADDRESS)));
                project.setCity(c.getString(c.getColumnIndex(Project.PROJECT_COL_CITY)));
                project.setImage(c.getBlob(c.getColumnIndex(Project.PROJECT_COL_IMAGE)));
                project.setDatePosted(c.getString(c.getColumnIndex(Project.PROJECT_COL_DATE_POSTED)));
        }
        c.close();
        db.close();
        return project;
    }

    public ArrayList<Project> getProjectList()
    {
        ArrayList<Project> list = new ArrayList<Project>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Project.PROJECT_TABLE_NAME+";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Project proj = new Project();
                proj.setProjectID(c.getInt(c.getColumnIndex(Project.PROJECT_COL_ID)));
                proj.setCustomerEmail(c.getString(c.getColumnIndex(Project.PROJECT_COL_CUST_EMAIL)));
                proj.setProjectDescription(c.getString(c.getColumnIndex(Project.PROJECT_COL_DESCRIPTION)));
                proj.setProjectType(c.getString(c.getColumnIndex(Project.PROJECT_COL_TYPE)));
                proj.setProjectBudget(c.getDouble(c.getColumnIndex(Project.PROJECT_COL_BUDGET)));
                proj.setTitle(c.getString(c.getColumnIndex(Project.PROJECT_COL_TITLE)));
                proj.setAddress(c.getString(c.getColumnIndex(Project.PROJECT_COL_ADDRESS)));
                proj.setCity(c.getString(c.getColumnIndex(Project.PROJECT_COL_CITY)));
                proj.setImage(c.getBlob(c.getColumnIndex(Project.PROJECT_COL_IMAGE)));
                proj.setDatePosted(c.getString(c.getColumnIndex(Project.PROJECT_COL_DATE_POSTED)));

                list.add(proj);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public ArrayList<Project> getPendingProjectList()
    {
        ArrayList<Project> list = new ArrayList<Project>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Project.PROJECT_TABLE_NAME + " EXCEPT "
            + "SELECT "+ Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_ID + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_CUST_EMAIL + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_DESCRIPTION + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_TYPE + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_BUDGET + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_TITLE + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_ADDRESS + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_CITY + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_IMAGE + ","
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_DATE_POSTED
            + " FROM " + Project.PROJECT_TABLE_NAME + "," + Proposal.PROPOSAL_TABLE_NAME
            + " WHERE " + Proposal.PROPOSAL_TABLE_NAME + "." + Proposal.PROPOSAL_PROJECT_ID + " = "
            + Project.PROJECT_TABLE_NAME + "." + Project.PROJECT_COL_ID + " AND " + Proposal.PROPOSAL_APPROVED
            + " <> 2";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Project proj = new Project();
                proj.setProjectID(c.getInt(c.getColumnIndex(Project.PROJECT_COL_ID)));
                proj.setCustomerEmail(c.getString(c.getColumnIndex(Project.PROJECT_COL_CUST_EMAIL)));
                proj.setProjectDescription(c.getString(c.getColumnIndex(Project.PROJECT_COL_DESCRIPTION)));
                proj.setProjectType(c.getString(c.getColumnIndex(Project.PROJECT_COL_TYPE)));
                proj.setProjectBudget(c.getDouble(c.getColumnIndex(Project.PROJECT_COL_BUDGET)));
                proj.setTitle(c.getString(c.getColumnIndex(Project.PROJECT_COL_TITLE)));
                proj.setAddress(c.getString(c.getColumnIndex(Project.PROJECT_COL_ADDRESS)));
                proj.setCity(c.getString(c.getColumnIndex(Project.PROJECT_COL_CITY)));
                proj.setImage(c.getBlob(c.getColumnIndex(Project.PROJECT_COL_IMAGE)));
                proj.setDatePosted(c.getString(c.getColumnIndex(Project.PROJECT_COL_DATE_POSTED)));

                list.add(proj);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public ArrayList<Project> getProjectListForCustomerEmail(String email)
    {
        ArrayList<Project> list = new ArrayList<Project>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Project.PROJECT_TABLE_NAME + " WHERE "
                + Project.PROJECT_COL_CUST_EMAIL + " = '"+email + "';";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Project proj = new Project();
                proj.setProjectID(c.getInt(c.getColumnIndex(Project.PROJECT_COL_ID)));
                proj.setCustomerEmail(c.getString(c.getColumnIndex(Project.PROJECT_COL_CUST_EMAIL)));
                proj.setProjectDescription(c.getString(c.getColumnIndex(Project.PROJECT_COL_DESCRIPTION)));
                proj.setProjectType(c.getString(c.getColumnIndex(Project.PROJECT_COL_TYPE)));
                proj.setProjectBudget(c.getDouble(c.getColumnIndex(Project.PROJECT_COL_BUDGET)));
                proj.setTitle(c.getString(c.getColumnIndex(Project.PROJECT_COL_TITLE)));
                proj.setAddress(c.getString(c.getColumnIndex(Project.PROJECT_COL_ADDRESS)));
                proj.setCity(c.getString(c.getColumnIndex(Project.PROJECT_COL_CITY)));
                proj.setImage(c.getBlob(c.getColumnIndex(Project.PROJECT_COL_IMAGE)));
                proj.setDatePosted(c.getString(c.getColumnIndex(Project.PROJECT_COL_DATE_POSTED)));

                list.add(proj);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public ArrayList<Project> getProjectListFromTypes(String types)
    {
        ArrayList<Project> list = new ArrayList<Project>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Project.PROJECT_TABLE_NAME + " WHERE "
                + Project.PROJECT_COL_TYPE + " in ('"+ types + "');";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Project proj = new Project();
                proj.setProjectID(c.getInt(c.getColumnIndex(Project.PROJECT_COL_ID)));
                proj.setCustomerEmail(c.getString(c.getColumnIndex(Project.PROJECT_COL_CUST_EMAIL)));
                proj.setProjectDescription(c.getString(c.getColumnIndex(Project.PROJECT_COL_DESCRIPTION)));
                proj.setProjectType(c.getString(c.getColumnIndex(Project.PROJECT_COL_TYPE)));
                proj.setProjectBudget(c.getDouble(c.getColumnIndex(Project.PROJECT_COL_BUDGET)));
                proj.setTitle(c.getString(c.getColumnIndex(Project.PROJECT_COL_TITLE)));
                proj.setAddress(c.getString(c.getColumnIndex(Project.PROJECT_COL_ADDRESS)));
                proj.setCity(c.getString(c.getColumnIndex(Project.PROJECT_COL_CITY)));
                proj.setImage(c.getBlob(c.getColumnIndex(Project.PROJECT_COL_IMAGE)));
                proj.setDatePosted(c.getString(c.getColumnIndex(Project.PROJECT_COL_DATE_POSTED)));

                list.add(proj);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public boolean deleteProject(Project project)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isDeleted = db.delete(
                Project.PROJECT_TABLE_NAME,
                Project.PROJECT_COL_ID + "=" + project.getProjectID(),
                null) > 0;
        db.close();

        return isDeleted;
    }


    //Proposal*************************************************************************************
    public static final String CREATE_PROPOSAL_QUERY = "CREATE TABLE " + Proposal.PROPOSAL_TABLE_NAME + " (" +
            Proposal.PROPOSAL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Proposal.PROPOSAL_CONTRACTOR_CO_NUM + " INTEGER," +
            Proposal.PROPOSAL_PROJECT_ID + " INTEGER," +
            Proposal.PROPOSAL_PROJECT_ESTIMATE + " REAL," +
            Proposal.PROPOSAL_APPROVED + " INTEGER" +
            ")";

    public void addProposal(Proposal p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Proposal.PROPOSAL_CONTRACTOR_CO_NUM, p.getContractorCONum());
        vals.put(Proposal.PROPOSAL_PROJECT_ID, p.getProjectID());
        vals.put(Proposal.PROPOSAL_PROJECT_ESTIMATE, p.getProjectEstimate());
        vals.put(Proposal.PROPOSAL_APPROVED, p.getProposalApproved());

        db.insert(Proposal.PROPOSAL_TABLE_NAME, null, vals);
        db.close();
    }

    public void replaceProposal(Proposal p)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Proposal.PROPOSAL_ID, p.getProposalID());
        vals.put(Proposal.PROPOSAL_CONTRACTOR_CO_NUM, p.getContractorCONum());
        vals.put(Proposal.PROPOSAL_PROJECT_ID, p.getProjectID());
        vals.put(Proposal.PROPOSAL_PROJECT_ESTIMATE, p.getProjectEstimate());
        vals.put(Proposal.PROPOSAL_APPROVED, p.getProposalApproved());

        db.replace(Proposal.PROPOSAL_TABLE_NAME, null, vals);
        db.close();
    }

    public ArrayList<Proposal> getProposalList()
    {
        ArrayList<Proposal> list = new ArrayList<Proposal>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Proposal.PROPOSAL_TABLE_NAME+";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Proposal prop = new Proposal();
                prop.setProposalID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_ID)));
                prop.setContractorCONum(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_CONTRACTOR_CO_NUM)));
                prop.setProjectID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ID)));
                prop.setProjectEstimate(c.getDouble(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ESTIMATE)));
                prop.setProposalApproved(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_APPROVED)));

                list.add(prop);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public ArrayList<Proposal> getProposalListForProject(Project project)
    {
        ArrayList<Proposal> list = new ArrayList<Proposal>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Proposal.PROPOSAL_TABLE_NAME+" WHERE "
                + Proposal.PROPOSAL_PROJECT_ID + " = " + project.getProjectID() + ";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Proposal prop = new Proposal();
                prop.setProposalID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_ID)));
                prop.setContractorCONum(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_CONTRACTOR_CO_NUM)));
                prop.setProjectID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ID)));
                prop.setProjectEstimate(c.getDouble(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ESTIMATE)));
                prop.setProposalApproved(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_APPROVED)));

                list.add(prop);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return list;
    }

    public ArrayList<Proposal> getProposalListForContractor(Contractor contractor)
    {
        ArrayList<Proposal> list = new ArrayList<Proposal>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Proposal.PROPOSAL_TABLE_NAME+" WHERE "
                + Proposal.PROPOSAL_CONTRACTOR_CO_NUM + " = " + contractor.getContractorCONum() + ";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Proposal prop = new Proposal();
                prop.setProposalID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_ID)));
                prop.setContractorCONum(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_CONTRACTOR_CO_NUM)));
                prop.setProjectID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ID)));
                prop.setProjectEstimate(c.getDouble(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ESTIMATE)));
                prop.setProposalApproved(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_APPROVED)));

                list.add(prop);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return list;
    }

    public ArrayList<Proposal> getProposalListForContractorAndStatus(Contractor contractor, String status)
    {
        ArrayList<Proposal> list = new ArrayList<Proposal>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Proposal.PROPOSAL_TABLE_NAME+" WHERE "
                + Proposal.PROPOSAL_CONTRACTOR_CO_NUM + " = " + contractor.getContractorCONum() + " AND "
                + Proposal.PROPOSAL_APPROVED + " IN ('" + status + "');";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Proposal prop = new Proposal();
                prop.setProposalID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_ID)));
                prop.setContractorCONum(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_CONTRACTOR_CO_NUM)));
                prop.setProjectID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ID)));
                prop.setProjectEstimate(c.getDouble(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ESTIMATE)));
                prop.setProposalApproved(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_APPROVED)));

                list.add(prop);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return list;
    }

    public void acceptProposal(Proposal proposal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Proposal.PROPOSAL_APPROVED, 0);
        db.update(Proposal.PROPOSAL_TABLE_NAME, vals, Proposal.PROPOSAL_PROJECT_ID + " = "
                + proposal.getProjectID(), null);

        vals.put(Proposal.PROPOSAL_APPROVED, 1);
        db.update(Proposal.PROPOSAL_TABLE_NAME, vals, Proposal.PROPOSAL_ID + " = "
                + proposal.getProposalID(), null);

        db.close();
    }

    // Returns the proposal if it is found, otherwise returns null
    public Proposal didContractorAlreadyMadeProposal(Contractor contractor, Project project)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Proposal proposal = null;

        String GET_LIST = "SELECT * FROM " + Proposal.PROPOSAL_TABLE_NAME + " WHERE " + Proposal.PROPOSAL_CONTRACTOR_CO_NUM
                + " = " + contractor.getContractorCONum() + " AND " + Proposal.PROPOSAL_PROJECT_ID
                + " = " + project.getProjectID() + ";";
        Cursor c = db.rawQuery(GET_LIST, null);

        if (c.moveToFirst())
        {
            proposal = new Proposal();
            proposal.setProposalID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_ID)));
            proposal.setContractorCONum(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_CONTRACTOR_CO_NUM)));
            proposal.setProjectID(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ID)));
            proposal.setProjectEstimate(c.getDouble(c.getColumnIndex(Proposal.PROPOSAL_PROJECT_ESTIMATE)));
            proposal.setProposalApproved(c.getInt(c.getColumnIndex(Proposal.PROPOSAL_APPROVED)));
        }

        c.close();
        db.close();

        return proposal;
    }

    //Payment**************************************************************************************
    public static final String CREATE_PAYMENT_QUERY = "CREATE TABLE " + Payment.PAYMENT_TABLE_NAME + " (" +
            Payment.PAYMENT_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Payment.PAYMENT_CONTRACTOR_CO_NUM + " INTEGER," +
            Payment.PAYMENT_AMOUNT + " REAL," +
            Payment.PAYMENT_PROPOSAL_ID + " INT," +
            Payment.PAYMENT_STATUS + " INTEGER," +
            Payment.PAYMENT_DATE + " TEXT" +
            ")";

    public void addPayment(Payment p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Payment.PAYMENT_CONTRACTOR_CO_NUM, p.getContractorCONum());
        vals.put(Payment.PAYMENT_AMOUNT, p.getPaymentAmount());
        vals.put(Payment.PAYMENT_PROPOSAL_ID, p.getProposalID());
        vals.put(Payment.PAYMENT_STATUS, p.getPaymentStatus());
        vals.put(Payment.PAYMENT_DATE, p.getPaymentDate());

        db.insert(Payment.PAYMENT_TABLE_NAME, null, vals);
        db.close();
    }

    public ArrayList<Payment> getPaymentList()
    {
        ArrayList<Payment> list = new ArrayList<Payment>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Payment.PAYMENT_TABLE_NAME+";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Payment pay = new Payment();
                pay.setPaymentID(c.getInt(c.getColumnIndex(Payment.PAYMENT_ID)));
                pay.setContractorCONum(c.getInt(c.getColumnIndex(Payment.PAYMENT_CONTRACTOR_CO_NUM)));
                pay.setPaymentAmount(c.getDouble(c.getColumnIndex(Payment.PAYMENT_AMOUNT)));
                pay.setProposalID(c.getInt(c.getColumnIndex(Payment.PAYMENT_PROPOSAL_ID)));
                pay.setPaymentStatus(c.getInt(c.getColumnIndex(Payment.PAYMENT_STATUS)));
                pay.setPaymentDate(c.getString(c.getColumnIndex(Payment.PAYMENT_DATE)));

                list.add(pay);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
//Admin Methods

    public long getCustomerCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,Customer.CUSTOMER_TABLE_NAME);
        db.close();

        return count;
    }

    public long getContractorCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,Contractor.CONTRACTOR_TABLE_NAME);
        db.close();
        return count;
    }
    public long getCustomerCountLastSevenDays(){
        return 6;

    }
    public int getContractorCountLastSevenDays(){
        return 2;
    }

    //Review***************************************************************************************
    public static final String CREATE_REVIEW_QUERY = "CREATE TABLE " + Review.REVIEW_TABLE_NAME + " (" +
            Review.REVIEW_COL_CON_NO + " INTEGER," +
            Review.REVIEW_COL_CUST_ID + " INTEGER," +
            Review.REVIEW_COL_Rating + " INTEGER," +
            Review.REVIEW_COL_REVIEW_TEXT + " TEXT," +
            "PRIMARY KEY (" + Review.REVIEW_COL_CON_NO + "," + Review.REVIEW_COL_CUST_ID + "))";

    public ArrayList<Review> getReviewListForContractor(Contractor contractor)
    {
        ArrayList<Review> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT * FROM " + Review.REVIEW_TABLE_NAME +" WHERE "
                + Review.REVIEW_COL_CON_NO + " = " + contractor.getContractorCONum() + ";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            do {
                Review review = new Review();

                review.setContractorNo(c.getInt(c.getColumnIndex(Review.REVIEW_COL_CON_NO)));
                review.setCustomerId(c.getInt(c.getColumnIndex(Review.REVIEW_COL_CUST_ID)));
                review.setRating(c.getInt(c.getColumnIndex(Review.REVIEW_COL_Rating)));
                review.setReviewText(c.getString(c.getColumnIndex(Review.REVIEW_COL_REVIEW_TEXT)));

                list.add(review);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return list;
    }

    public void insertOrReplaceReview(Review review)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(Review.REVIEW_COL_CON_NO, review.getContractorNo());
        vals.put(Review.REVIEW_COL_CUST_ID, review.getCustomerId());
        vals.put(Review.REVIEW_COL_Rating, review.getRating());
        vals.put(Review.REVIEW_COL_REVIEW_TEXT, review.getReviewText());

        db.replace(Review.REVIEW_TABLE_NAME, null, vals);
        db.close();
    }

    public boolean deleteReview(Review review)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isDeleted = db.delete(
                Review.REVIEW_TABLE_NAME,
                Review.REVIEW_COL_CON_NO + "=" + review.getContractorNo() + " AND "
                        + Review.REVIEW_COL_CUST_ID + "=" + review.getCustomerId(),
                null) > 0;
        db.close();

        return isDeleted;
    }

    public double[] getReviewCountAndRatingForContractor(Contractor contractor)
    {
        double[] values = new double[2];
        SQLiteDatabase db = this.getReadableDatabase();

        String GET_LIST = "SELECT COUNT(*), AVG("+ Review.REVIEW_COL_Rating +") FROM "
                + Review.REVIEW_TABLE_NAME + " WHERE " + Review.REVIEW_COL_CON_NO + " = " + contractor.getContractorCONum() + ";";
        Cursor c = db.rawQuery(GET_LIST,null);

        if (c.moveToFirst())
        {
            values[0] = c.getInt(0);
            values[1] = c.getDouble(1);
        }

        c.close();
        db.close();
        return values;
    }


    //General Methods******************************************************************************
    public boolean checkIfEmailExists(String email){
        ArrayList<Customer> custList = getCustomerList();
        for (int i = 0; i < custList.size(); i++)
        {
            if (email.equals(custList.get(i).getCustomerEmail()))
            {
                return true;
            }
        }

        ArrayList<Contractor> conList = getContractorList();
        for (int i = 0; i < conList.size(); i++)
        {
            if (email.equals(conList.get(i).getContractorEmail()))
            {
                return true;
            }
        }

        return false;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMER_TABLE_QUERY);
        db.execSQL(CREATE_CONTRACTOR_TABLE_QUERY);
        db.execSQL(CREATE_PROJECT_TABLE_QUERY);
        db.execSQL(CREATE_PROPOSAL_QUERY);
        db.execSQL(CREATE_PAYMENT_QUERY);
        db.execSQL(CREATE_REVIEW_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
