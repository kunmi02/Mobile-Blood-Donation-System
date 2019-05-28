package com.example.akinkunmi.blooddonor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class RequestHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Blood_donors";
    public static final String TABLE_NAME = "Donor_RequestTable";
    public static final String TABLE_NAME2 = "Members";
    public static final String TABLE_NAME3 = "Hospitals";
    public static final String COL_1 = "Request_name";
    public static final String COL_2 = "Request_email";
    public static final String COL_3 = "Request_BloodType";
    public static final String COL_4 = "Request_country";
    public static final String COL_5= "Request_city";
    public static final String COL_6= "Request_number";
    public static final String UID="_id";     // Column I (Primary Key)
    public static final String User_email = "email";  // Column II
    public static final String MyPASSWORD= "Password";    // Column III
    public static final String User_NAME = "Name";
    public static final String userbloodgroup = "bloodgroup";  // Column IV
    public static final String User_location = "location"; // Column V
    public static final String user_mobile = "mobile_number"; // Column VI
    public static final String COL_11 = "Hospital_email";
    public static final String COL_22 = "Hospital_password";
    public static final String COL_33 = "Hospital_name";
    public static final String COL_44 = "Hospital_location";
    public static final String COL_55= "Hospital_digit";
    private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME2+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+User_email+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225)," + User_NAME+" VARCHAR(255), " +
            ""+ userbloodgroup+" VARCHAR(255),"+User_location+" VARCHAR(255), "+user_mobile +" VARCHAR(255));";


    private static final String DROP_TABLE2 ="DROP TABLE IF EXISTS "+TABLE_NAME2;

    public RequestHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Request_name TEXT,Request_email TEXT,Request_BloodType TEXT," +
                "Request_country TEXT, Request_city TEXT,Request_number TEXT)");
        db.execSQL(CREATE_TABLE2);
        db.execSQL("create table " + TABLE_NAME3 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Hospital_email TEXT,Hospital_password TEXT,Hospital_name TEXT," +
                "Hospital_location TEXT, Hospital_digit TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL(DROP_TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        onCreate(db);
    }
    public boolean insertDataHospital(String mail,String password,String name,String location,String digit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11,mail);
        contentValues.put(COL_22,password);
        contentValues.put(COL_33,name);
        contentValues.put(COL_44,location);
        contentValues.put(COL_55,digit);
        long result = db.insert(TABLE_NAME3,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataBloodRequest(String name,String mail,String country,String blood_request_type,String city,String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,mail);
        contentValues.put(COL_3,country);
        contentValues.put(COL_4,blood_request_type);
        contentValues.put(COL_5,city);
        contentValues.put(COL_6,number);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public long insertDataMember(String email,String password,String name,String bloodgroup,String location,String mobile_number )
    {
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User_email, email);
        contentValues.put(MyPASSWORD, password);
        contentValues.put(User_NAME, name);
        contentValues.put(userbloodgroup, bloodgroup);
        contentValues.put(User_location, location);
        contentValues.put(user_mobile, mobile_number);
        long id = dbb.insert(TABLE_NAME2, null , contentValues);
        return  id;

    }
    public Cursor getAllDonorRequest() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getrequestList(String locality, String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM TABLE_NAME WHERE Request_city = ? AND + Request_BloodType =?" , new String[] {locality,item});
        return res;
    }


    /**public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    } **/
}
