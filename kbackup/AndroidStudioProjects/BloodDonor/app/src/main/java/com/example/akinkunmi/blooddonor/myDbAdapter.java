package com.example.akinkunmi.blooddonor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String email,String password,String name,String bloodgroup,String location,String mobile_number )
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.User_email, email);
        contentValues.put(myDbHelper.MyPASSWORD, password);
        contentValues.put(myDbHelper.User_NAME, name);
        contentValues.put(myDbHelper.userbloodgroup, bloodgroup);
        contentValues.put(myDbHelper.User_location, location);
        contentValues.put(myDbHelper.user_mobile, mobile_number);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return  id;

    }

    public String getData()

    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.User_email,myDbHelper.MyPASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.User_email));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

        /**public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    } **/

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "Blood_donors";    // Database Name
        private static final String TABLE_NAME = "Members";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String User_email = "email";  // Column II
        private static final String MyPASSWORD= "Password";    // Column III
        private static final String User_NAME = "Name";
        private static final String userbloodgroup = "bloodgroup";  // Column IV
        private static final String User_location = "location"; // Column V
        private static final String user_mobile = "mobile_number"; // Column VI
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+User_email+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225)," + User_NAME+" VARCHAR(255), " +
                ""+ userbloodgroup+" VARCHAR(255),"+User_location+" VARCHAR(255), "+user_mobile +" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}