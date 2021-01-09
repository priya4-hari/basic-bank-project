package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into "+ TABLE_NAME+ " values(9987656789,'JEFF',100000,'jeffb_2233@gmail.com','679586567','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+ " values(8976543231,'ELON',6000.89,'elon_musk@gmail.com','987654345','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(8182838485,'ROSS',300987,'ross_144@gmail.com','987603417','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(6567890123,'RACHEL',159087,'rachel.05@gmail.com','123456789','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(7657898765,'Sow',509876.77,'sow_2306@gmail.com','345622342','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(9490718789,'JENNY',9000,'jennifer.j@gmail.com','678654345','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(6789876543,'CHRISTINA',2000000,'yang_c@gmail.com','567456789','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(9089878987,'KEVIN',85987.22,'kevin_1@gmail.com','987655232','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(7897207831,'BOB',40000.46,'bob_2@gmail.com','987656787','ABC0987655')");
        db.execSQL("insert into "+TABLE_NAME+" values(8976547890,'STUART',2345,'stuart_3@gmail.com','987689076','ABC0987655')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}