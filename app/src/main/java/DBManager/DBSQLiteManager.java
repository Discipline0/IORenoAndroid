package DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Contractor;
import Model.Customer;

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

    }


//General Methods
    public boolean checkIfEmailExists(String email){
        ArrayList<Customer> list = getCustomerList();
        boolean exists = false;
        for (int i = 0; i < list.size(); i++)
        {
            if (email.equals(list.get(i).getCustomerEmail()))
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


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
