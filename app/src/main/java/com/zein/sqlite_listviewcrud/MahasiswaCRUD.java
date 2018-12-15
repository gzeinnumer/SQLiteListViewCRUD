package com.zein.sqlite_listviewcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MahasiswaCRUD {

    private MahasiswaDBHelper mahasiswaDBHelper;

    public MahasiswaCRUD(Context context){
        mahasiswaDBHelper = new MahasiswaDBHelper(context);
    }

    public int insert(Mahasiswa mahasiswa){
        SQLiteDatabase db = mahasiswaDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Mahasiswa.KEY_umur,mahasiswa.umur);
        values.put(Mahasiswa.KEY_jurusan,mahasiswa.jurusan);
        values.put(Mahasiswa.KEY_prodi,mahasiswa.prodi);
        values.put(Mahasiswa.KEY_kelas,mahasiswa.kelas);
        values.put(Mahasiswa.KEY_nama,mahasiswa.nama);
        values.put(Mahasiswa.KEY_nobp,mahasiswa.nobp);
        long mahasiswa_Id = db.insert(Mahasiswa.TABLE,null, values);
        db.close();
        return (int) mahasiswa_Id;
    }

    public void delete(int mahasiswa_Id){
        SQLiteDatabase db = mahasiswaDBHelper.getWritableDatabase();
        db.delete(Mahasiswa.TABLE, Mahasiswa.KEY_ID + "=" + mahasiswa_Id,null);
        db.close();
    }

    public void update(Mahasiswa mahasiswa){
        SQLiteDatabase db = mahasiswaDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Mahasiswa.KEY_umur,mahasiswa.umur);
        values.put(Mahasiswa.KEY_jurusan,mahasiswa.jurusan);
        values.put(Mahasiswa.KEY_prodi,mahasiswa.prodi);
        values.put(Mahasiswa.KEY_kelas,mahasiswa.kelas);
        values.put(Mahasiswa.KEY_nama,mahasiswa.nama);
        values.put(Mahasiswa.KEY_nobp,mahasiswa.nobp);
        db.update(Mahasiswa.TABLE, values, Mahasiswa.KEY_ID+"="+mahasiswa.mahasiswa_ID,null);
        db.close();
    }

    public ArrayList getAll(){
        SQLiteDatabase db = mahasiswaDBHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Mahasiswa.KEY_ID + "," +
                Mahasiswa.KEY_nobp + "," +
                Mahasiswa.KEY_nama + "," +
                Mahasiswa.KEY_kelas + "," +
                Mahasiswa.KEY_prodi + "," +
                Mahasiswa.KEY_jurusan + "," +
                Mahasiswa.KEY_umur +
                " FROM " + Mahasiswa.TABLE ;

        ArrayList mahasiswaList = new ArrayList();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                mahasiswaList.add(
                        cursor.getString(
                                cursor.getColumnIndex(Mahasiswa.KEY_ID)) + "_" +
                cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_nama)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswaList;
    }

    public ArrayList<HashMap<String,String>> getMahasiswaList(){
        SQLiteDatabase db = mahasiswaDBHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Mahasiswa.KEY_ID + "," +
                Mahasiswa.KEY_nobp + "," +
                Mahasiswa.KEY_nama + "," +
                Mahasiswa.KEY_kelas + "," +
                Mahasiswa.KEY_prodi + "," +
                Mahasiswa.KEY_jurusan + "," +
                Mahasiswa.KEY_umur + " FROM " + Mahasiswa.TABLE;

        ArrayList<HashMap<String,String>> mahasiswaList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> mahasiswa = new HashMap<String, String>();
                mahasiswa.put("id", cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_ID)));
                mahasiswa.put("nama", cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_nama)));
                mahasiswaList.add(mahasiswa);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswaList;
    }

    public Mahasiswa getMahasiswaById(int Id){
        SQLiteDatabase db = mahasiswaDBHelper.getReadableDatabase();
        String selectQuery = "SELECT "+
                Mahasiswa.KEY_ID + "," +
                Mahasiswa.KEY_nobp + "," +
                Mahasiswa.KEY_nama + "," +
                Mahasiswa.KEY_kelas + "," +
                Mahasiswa.KEY_jurusan + "," +
                Mahasiswa.KEY_prodi + "," +
                Mahasiswa.KEY_umur + " FROM " + Mahasiswa.TABLE + " WHERE " + Mahasiswa.KEY_ID + "=" + Id;
        int iCount = 0;
        Mahasiswa mahasiswa = new Mahasiswa();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                mahasiswa.mahasiswa_ID = cursor.getInt(cursor.getColumnIndex(Mahasiswa.KEY_ID));
                mahasiswa.nobp = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_nobp));
                mahasiswa.nama = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_nama));
                mahasiswa.kelas = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_kelas));
                mahasiswa.prodi = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_prodi));
                mahasiswa.jurusan = cursor.getString(cursor.getColumnIndex(Mahasiswa.KEY_jurusan));
                mahasiswa.umur = cursor.getInt(cursor.getColumnIndex(Mahasiswa.KEY_umur));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswa;
    }

    private int getFirstMahasiswa(){
        SQLiteDatabase db = mahasiswaDBHelper.getReadableDatabase();
        String selectQuery =
                "SELECT " + Mahasiswa.KEY_ID +  " FROM " + Mahasiswa.TABLE + " LIMIT 1;" ;
        int mahasiswa_Id = 0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        //masukan ke listdata
        if(cursor.moveToFirst()){
            do{
                mahasiswa_Id = cursor.getInt(cursor.getColumnIndex(Mahasiswa.KEY_ID));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswa_Id;
    }

    private int getLastMahasiswa(){
        SQLiteDatabase db = mahasiswaDBHelper.getReadableDatabase();
        String selectQuery =
                "SELECT " + Mahasiswa.KEY_ID + " FROM " + Mahasiswa.TABLE + " ORDER BY " + Mahasiswa.KEY_ID + " DESC ";
        //" DESC LIMIT 1;" ;
        int mahasiswa_Id = 0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                mahasiswa_Id =cursor.getInt(cursor.getColumnIndex(Mahasiswa.KEY_ID));
                break;
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mahasiswa_Id;
    }
}
