package com.example.irfannawawi.kamus5milyar.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.irfannawawi.kamus5milyar.model.KamusItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.KamusColumns.ARTI;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.KamusColumns.KATA;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.TABLE_ENGLISH;
import static com.example.irfannawawi.kamus5milyar.db.DatabaseContract.TABLE_INDONESIA;

/**
 * Created by Irfan Nawawi on 26/01/2019.
 */

public class KamusHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<KamusItem> getKamusByKataInggris(String kata) {
        Cursor cursor = database.query(TABLE_ENGLISH, null, KATA + " LIKE ?", new String[]{kata}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusItem> arrayList = new ArrayList<>();
        KamusItem kamusItem;
        if (cursor.getCount() > 0) {
            do {
                kamusItem = new KamusItem();
                kamusItem.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusItem.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusItem.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));
                arrayList.add(kamusItem);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusItem> getKamusByKataIndonesia(String kata) {
        Cursor cursor = database.query(TABLE_INDONESIA, null, KATA + " LIKE ?", new String[]{kata}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusItem> arrayList = new ArrayList<>();
        KamusItem kamusItem;
        if (cursor.getCount() > 0) {
            do {
                kamusItem = new KamusItem();
                kamusItem.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusItem.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusItem.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));
                arrayList.add(kamusItem);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusItem> getAllDataEng() {
        Cursor cursor = database.query(TABLE_ENGLISH, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusItem> arrayList = new ArrayList<>();
        KamusItem kamusItem;
        if (cursor.getCount() > 0) {
            do {
                kamusItem = new KamusItem();
                kamusItem.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusItem.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusItem.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));
                arrayList.add(kamusItem);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusItem> getAllDataInd() {
        Cursor cursor = database.query(TABLE_INDONESIA, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusItem> arrayList = new ArrayList<>();
        KamusItem kamusItem;
        if (cursor.getCount() > 0) {
            do {
                kamusItem = new KamusItem();
                kamusItem.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusItem.setKata(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusItem.setArti(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));
                arrayList.add(kamusItem);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusItem kamusItem, boolean inggris) {
        String DATABASE_TABLE = inggris ? TABLE_ENGLISH : TABLE_INDONESIA;
        ContentValues initialValues = new ContentValues();
        initialValues.put(KATA, kamusItem.getKata());
        initialValues.put(ARTI, kamusItem.getArti());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void insertTransaction(KamusItem kamusItem, boolean inggris) {
        String DATABASE_TABLE = inggris ? TABLE_ENGLISH : TABLE_INDONESIA;
        String sql = "INSERT INTO " + DATABASE_TABLE + "(" + KATA + ", " + ARTI + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusItem.getKata());
        stmt.bindString(2, kamusItem.getArti());
        stmt.execute();
        stmt.clearBindings();
    }

    public int update(KamusItem kamusItem, boolean inggris) {
        String DATABASE_TABLE = inggris ? TABLE_ENGLISH : TABLE_INDONESIA;
        ContentValues args = new ContentValues();
        args.put(KATA, kamusItem.getKata());
        args.put(ARTI, kamusItem.getArti());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + kamusItem.getId() + "'", null);
    }

    public int delete(int id, boolean inggris) {
        String DATABASE_TABLE = inggris ? TABLE_ENGLISH : TABLE_INDONESIA;
        return database.delete(DATABASE_TABLE, _ID + "='" + id + "'", null);
    }
}
