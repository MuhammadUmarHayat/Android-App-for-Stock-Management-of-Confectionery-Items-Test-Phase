package com.example.fahadtestphase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductTable  extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME =  "FahadDB21.db";
    public static final String TABLE_NAME = "Products_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ProductName";
    public static final String COL_3 = "Packing";
    public static final String COL_4 = "purchasePrice";
    public static final String COL_5 = "SalePrice";
    public static final String COL_6 = "Manufactures";
    public static final String COL_7 = "Quantity";




    public ProductTable(Context context)
    {

        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
                                                                                                    //String productName,String Packing,int purchasePrice,int SalePrice,String Manufactures,int qty)
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ProductName TEXT ,Packing TEXT,purchasePrice INTEGER,SalePrice INTEGER,Manufactures TEXT,Quantity INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public  boolean insertData(String productName,String Packing,int purchasePrice,int SalePrice,String Manufactures,int qty)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,productName);
        contentValues.put(COL_3,Packing);
        contentValues.put(COL_4,purchasePrice);
        contentValues.put(COL_5,SalePrice);
        contentValues.put(COL_6,Manufactures);
        contentValues.put(COL_7,qty);

        long result = db.insert(TABLE_NAME,null ,contentValues);//insert
        if(result == -1)
            return false;
        else
            return true;//data is inserted
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor searchData(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where id='"+ID+"'",null);
        return res;
    }


    public Integer deleteData (String itemid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {itemid});
    }





}
