package com.zein.sqlite_listviewcrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MahasiswaDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 14;
    private static final String DATABASE_NAME = "crudmahasiswa.db";

    public MahasiswaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MAHASISWA = "CREATE TABLE "+ Mahasiswa.TABLE + " (" +
                Mahasiswa.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Mahasiswa.KEY_nobp + " TEXT, " +
                Mahasiswa.KEY_nama + " TEXT, " +
                Mahasiswa.KEY_kelas + " TEXT, " +
                Mahasiswa.KEY_prodi + " TEXT, " +
                Mahasiswa.KEY_jurusan + " TEXT, " +
                Mahasiswa.KEY_umur + " INTEGER)";
        db.execSQL(CREATE_TABLE_MAHASISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Mahasiswa.TABLE);
        onCreate(db);
    }
}
