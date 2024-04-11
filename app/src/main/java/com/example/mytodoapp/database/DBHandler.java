package com.example.mytodoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler  extends SQLiteOpenHelper {
    private static final String DB_NAME = "todoTasks";

    public static final int  DB_VERSION = 1;
    public static final String TABLE_NAME =  "yojoTasks";
    public static final String ID_COL = "id";
    public static final String TITLE_COL = "title";
    public static final String DURATION_COL = "duration";
    public static final String NOTE_COL = "note";


    public DBHandler(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT, "
                + DURATION_COL + " TEXT, "
                + NOTE_COL + " TEXT) ";

        db.execSQL(query);
    }


    public void addNewTask(String title, String duration, String note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE_COL, title);
        values.put(DURATION_COL, duration);
        values.put(NOTE_COL, note);

        db.insert(TABLE_NAME, null, values);

        db.close();

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL((" DROP TABLE IF EXISTS " + TABLE_NAME));
        onCreate(db);
    }
}
