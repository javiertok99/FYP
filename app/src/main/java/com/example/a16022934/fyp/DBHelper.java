package com.example.a16022934.fyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simplesongs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USER_ID = "user_id";;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_ID + " TEXT)";
        db.execSQL(createSongTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public long retainUserLogIn(String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user_id);
        long result = db.insert(TABLE_USER, null, values);
        if(result == -1){
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert ",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public String getUserId(){
        String userId = "";
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_USER_ID + " FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                userId = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userId;
    }

    public int getId(){
        int id = 0;
        String selectQuery = "SELECT " + COLUMN_ID + " FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return id;
    }

    public void removeUserId(int uid){
        SQLiteDatabase db = this.getWritableDatabase();
        String remove = "";
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, remove);
        String condition = COLUMN_ID + "= ?";
        String[] args = { uid + "" };
        int result = db.update(TABLE_USER, values, condition, args);
        db.close();
    }

}

