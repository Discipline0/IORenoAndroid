package DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Contractor;
import Model.Customer;
import Model.Payment;
import Model.Project;
import Model.Proposal;

public class DBSQLiteManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
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

    public void addCustomer(Customer c )
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

                list.add(cus);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
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





    //General Methods******************************************************************************
    public boolean checkIfEmailExists(String email){
        ArrayList<Customer> custList = getCustomerList();
        boolean exists = false;
        for (int i = 0; i < custList.size(); i++)
        {
            if (email.equals(custList.get(i).getCustomerEmail()))
            {
                exists = true;
            }
        }

        ArrayList<Contractor> conList = getContractorList();
        for (int i = 0; i < conList.size(); i++)
        {
            if (email.equals(conList.get(i).getContractorEmail()))
            {
                exists = true;
            }
        }

        return exists;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMER_TABLE_QUERY);
        db.execSQL(CREATE_CONTRACTOR_TABLE_QUERY);
        db.execSQL(CREATE_PROJECT_TABLE_QUERY);
        db.execSQL(CREATE_PROPOSAL_QUERY);
        db.execSQL(CREATE_PAYMENT_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
