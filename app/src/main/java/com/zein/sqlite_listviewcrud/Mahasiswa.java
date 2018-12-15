package com.zein.sqlite_listviewcrud;

public class Mahasiswa {
    public static final  String TABLE = "Mahasiswa";
    public static final  String KEY_ID = "id";
    public static final  String KEY_nobp = "nobp";
    public static final  String KEY_nama = "nama";
    public static final  String KEY_kelas = "kelas";
    public static final  String KEY_prodi = "prodi";
    public static final  String KEY_jurusan = "jurusan";
    public static final  String KEY_umur = "umur";
    public int mahasiswa_ID;
    public String nobp;
    public String nama;
    public String kelas;
    public String prodi;
    public String jurusan;
    public int umur;

    public Mahasiswa() {
    }

    public Mahasiswa(int mahasiswa_ID, String nobp, String nama, String kelas, String prodi, String jurusan, int umur) {
        this.mahasiswa_ID = mahasiswa_ID;
        this.nobp = nobp;
        this.nama = nama;
        this.kelas = kelas;
        this.prodi = prodi;
        this.jurusan = jurusan;
        this.umur = umur;
    }
}
