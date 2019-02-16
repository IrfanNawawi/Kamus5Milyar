package com.example.irfannawawi.kamus5milyar.db;

import android.provider.BaseColumns;

/**
 * Created by Irfan Nawawi on 26/01/2019.
 */

public class DatabaseContract {
    static String TABLE_ENGLISH = "table_inggris";
    static String TABLE_INDONESIA = "table_indonesia";

    static final class KamusColumns implements BaseColumns {
        static String KATA = "kata";
        static String ARTI = "arti";
    }
}
