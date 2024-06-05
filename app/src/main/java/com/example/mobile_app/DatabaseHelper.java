package com.example.mobile_app;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "point.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "client"; // название таблицы в бд
    static final String TABLE1 = "request";
    static final String TABLE2 = "Employee";
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TRANSPORT = "transport";
    public static final String COLUMN_TACHOGRAPH="tachograph";
    public static final String COLUMN_MAP="MAP";
    public static final String COLUMN_NUMBER="number";
    public static final String COLUMN_SALARY="Salary";
    public static final String COLUMN_POST="post";
    public static final String COLUMN_LINE="LINE";
    public static final String COLUMN_LINE1="LINE1";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE client (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_TRANSPORT + " TEXT, " + COLUMN_TACHOGRAPH
                + " TEXT, " + COLUMN_MAP + " TEXT);");
        db.execSQL("CREATE TABLE request (" +COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_NUMBER + " TEXT, " + COLUMN_LINE
                + " TEXT, " + COLUMN_LINE1 + " TEXT)");
        db.execSQL("CREATE TABLE Employee(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + "TEXT," +COLUMN_POST + "TEXT," + COLUMN_SALARY
                + "TEXT," + COLUMN_LINE + "TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        onCreate(db);
    }
}
