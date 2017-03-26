package com.techobbyist.signuplogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 5/5/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 2;

    public static final String SELLER_TABLE = "sellers";
    public static final String SELLER_ID = "_id";
    public static final String SELLER_EMAIL = "email";
    public static final String SELLER_PASS = "password";


    public static final String BUYER_TABLE = "buyers";
    public static final String BUYER_ID = "buyer_id";
    public static final String BUYER_EMAIL = "buyer_email";
    public static final String BUYER_PASS = "buyer_password";


    public static final String ITEMS_TABLE = "items";
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_NAME = "item_name";
  //  public static final String SELLER_IDF = "seller_id";
    /*
    create table users(
        id integer primary key autoincrement,
        email text,
        password text);
     */
    public static final String CREATE_TABLE_SELLERS = "CREATE TABLE " + SELLER_TABLE + "("
            + SELLER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SELLER_EMAIL + " TEXT,"
            + SELLER_PASS + " TEXT);";


    public static final String CREATE_TABLE_BUYERS = "CREATE TABLE " + BUYER_TABLE + "("
            + BUYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BUYER_EMAIL + " TEXT,"
            + BUYER_PASS + " TEXT);";


    public static final String CREATE_TABLE_ITEMS = "CREATE TABLE " + ITEMS_TABLE + "("
            + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ITEM_NAME + " TEXT);";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SELLERS);
        db.execSQL(CREATE_TABLE_BUYERS);
        db.execSQL(CREATE_TABLE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SELLER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUYER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);
    }

    /**
     * Storing user details in database
     */
    public void addSeller(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SELLER_EMAIL, email);
        values.put(SELLER_PASS, password);

        long id = db.insert(SELLER_TABLE, null, values);
        db.close();

        Log.d(TAG, "seller inserted" + id);
    }

    public boolean getSeller(String email, String pass) {
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + SELLER_TABLE + " where " +
                SELLER_EMAIL + " = " + "'" + email + "'" + " and " + SELLER_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    //buyer methods
    public void addBuyer(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUYER_EMAIL, email);
        values.put(BUYER_PASS, password);

        long id = db.insert(BUYER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public boolean getBuyer(String email, String pass) {
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + BUYER_TABLE + " where " +
                BUYER_EMAIL + " = " + "'" + email + "'" + " and " + BUYER_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    //item methods
    public void addItem(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, name);
//        /values.put(SELLER_IDF,sellerid);

        long id = db.insert(ITEMS_TABLE, null, values);
        db.close();

        Log.d(TAG, "item inserted" + id);
    }

    public ArrayList<String> getAllCategorys() {
        ArrayList<String> categoryList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ITEMS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.query(ITEMS_TABLE,
                null, null, null, null, null, null);
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            categoryList.add(cur.getString(1));
            cur.moveToNext();
        }
        cur.close();

        // return category list
        return categoryList;
    }
}
