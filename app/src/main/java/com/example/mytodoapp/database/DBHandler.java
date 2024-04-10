package com.example.mytodoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "coursedb";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "mytodolist";
    public static final String ID_COL = "id";
    public static final String NAME_COL = "name";
    public static final String DURATION_COL = "duration";
    public static final String TODO_TASK = "task";
    public static final String TRACKS_COL = "tracks";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_NAME +"("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + DURATION_COL + "TEXT, "
                + TODO_TASK + "TEXT, "
                + TRACKS_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewCourse(String listName, String todoDuration, String todoTask, String todotracks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL,listName);
        values.put(DURATION_COL,todoDuration);
        values.put(TODO_TASK, todoTask);
        values.put(TRACKS_COL, todotracks);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
