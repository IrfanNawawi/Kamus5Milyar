package com.example.irfannawawi.kamus5milyar.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.KamusColumns.ARTI;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.KamusColumns.KATA;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.TABLE_ENGLISH;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.TABLE_INDONESIA;

/**
 * Created by Irfan Nawawi on 26/01/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_INDONESIA = "create table " + TABLE_INDONESIA +
            " (" + _ID + " integer primary key autoincrement, " +
            KATA + " text not null, " +
            ARTI + " text not null);";
    public static String CREATE_TABLE_INGGRIS = "create table " + TABLE_ENGLISH +
            " (" + _ID + " integer primary key autoincrement, " +
            KATA + " text not null, " +
            ARTI + " text not null);";
    private static String DATABASE_KAMUS = "dbkamusdicoding";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_KAMUS, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INDONESIA);
        db.execSQL(CREATE_TABLE_INGGRIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDONESIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH);
        onCreate(db);
    }
}
