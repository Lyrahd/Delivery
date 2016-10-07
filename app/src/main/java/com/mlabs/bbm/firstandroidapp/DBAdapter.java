package com.mlabs.bbm.firstandroidapp;

/**
 * Created by DarkHorse on 21/09/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBAdapter {
    static final String DATABASE_NAME = "users.db";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "Create Table " + "USERS" +
            "( " + "ID" + " Integer Primary Key Autoincrement," +
            "FIRSTNAME text, LASTNAME text, USERNAME text, EMAIL text,PASSWORD text); ";

    public SQLiteDatabase db;
    private final Context context;
    private DBHelper dbHelper;

    public DBAdapter(Context _context) {
        context = _context;
        dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void insertEntry(String fname, String lname, String uname, String email, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("FIRSTNAME", fname);
        newValues.put("LASTNAME", lname);
        newValues.put("USERNAME", uname);
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        db.insert("USERS", null, newValues);
    }

    public String getSingleEntry(String email) {
        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor = db.query("USERS", null, " USERNAME=?", new String[]{email}, null, null, null);
            if (cursor.getCount() < 1) {
                cursor.close();
                return "Account not found";
            }
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public boolean ifExist(String input) {
        Cursor cursor = db.query("USERS", null, " USERNAME=?", new String[]{input}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor = db.query("USERS", null, " EMAIL=?", new String[]{input}, null, null, null);
            if (cursor.getCount()< 1) // UserName Not Exist
            {
                cursor.close();
                return false;
                //not exist
            }
            cursor.moveToFirst();
            cursor.close();
            return true;

        }
        cursor.moveToFirst();
        cursor.close();
        return true;
    }

}