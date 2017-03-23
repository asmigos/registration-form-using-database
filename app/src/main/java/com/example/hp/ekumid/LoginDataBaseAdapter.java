package com.example.hp.ekumid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hp on 23-03-2017.
 */

public class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "NAME  text,CONTACT text,ALTCONTACT text,BLOOD text ,AGE text, EMAIL text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String namevalue, String contactvalue, String altcontactvalue, String agevalue, String bloodvalue, String emailvalue)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("NAME", namevalue);
        newValues.put("CONTACT",contactvalue);
        newValues.put("ALTCONTACT", altcontactvalue);
        newValues.put("BLOOD",bloodvalue);
        newValues.put("AGE", agevalue);
        newValues.put("EMAIL",emailvalue);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String namevalue)
    {
        //String id=String.valueOf(ID);
        String where="NAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{namevalue}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String namevalue, String contactvalue, String altcontactvalue, String bloodvalue, String agevalue, String emailvalue)
    {
        Cursor cursor=db.query("LOGIN", null, " NAME=?", new String[]{namevalue}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
         contactvalue= cursor.getString(cursor.getColumnIndex("CONTACT"));
        altcontactvalue= cursor.getString(cursor.getColumnIndex("ALTCONTACT"));
        bloodvalue= cursor.getString(cursor.getColumnIndex("BLOOD"));
        agevalue= cursor.getString(cursor.getColumnIndex("AGE"));
        emailvalue= cursor.getString(cursor.getColumnIndex("EMAIL"));
        cursor.close();
        return contactvalue;
    }
    public void  updateEntry(String namevalue,String contactvalue, String altcontactvalue, String bloodvalue, String agevalue, String emailvalue)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("NAME", namevalue);
        updatedValues.put("CONRACT",contactvalue);
        updatedValues.put("ALTCONTACT", altcontactvalue);
        updatedValues.put("BLOOD",bloodvalue);
        updatedValues.put("AGE", agevalue);
        updatedValues.put("EMAIL",emailvalue);

        String where="NAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{namevalue});
    }
}
