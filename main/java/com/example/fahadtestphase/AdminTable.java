package com.example.fahadtestphase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminTable extends SQLiteOpenHelper
{

    /*
    * Define constants
    *DATABASE_NAME
    *TABLE_NAME
    * COL_1,COL_2,COL_3,COL_4,COL_5
     * */
    public static final String DATABASE_NAME = "FahadDB1.db";
    public static final String TABLE_NAME = "Admin_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "userID";
    public static final String COL_3 = "Password";
    public static final String COL_4 = "MobileNo";
    public static final String COL_5 = "Address";

    public AdminTable(Context context)//constructor
    {

        super(context, DATABASE_NAME, null, 2);
    }







    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,userID TEXT ,Password TEXT,MobileNo INTEGER,Address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public  boolean insertData(String username,String pw,int mobileNo,String add)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,pw);
        contentValues.put(COL_4,mobileNo);
        contentValues.put(COL_5,add);

        long result = db.insert(TABLE_NAME,null ,contentValues);//insert
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean checkUserExist(String username, String password)
    {//UserID ,Password
        String[] columns = {"userID"};
        SQLiteDatabase   db =  this.getWritableDatabase();

        String selection = "userID =? and Password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("Admin_table", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }





}
