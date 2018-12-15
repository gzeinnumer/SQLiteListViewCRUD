package com.zein.sqlite_listviewcrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MahasiswaInputData extends AppCompatActivity implements View.OnClickListener {
    private Button btnSimpan, btnTutup;
    private EditText editNobp, editNama, editKelas, editProdi, editJurusan, editUmur;
    private int _Mahasiswa_Id=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mahasiswa);
        btnSimpan= (Button) findViewById(R.id.btnSimpan);
        btnTutup= (Button) findViewById(R.id.btnTutup);
        editNobp = (EditText) findViewById(R.id.editNoBp);
        editNama = (EditText) findViewById(R.id.editNama);
        editKelas = (EditText) findViewById(R.id.editKelas);
        editProdi = (EditText) findViewById(R.id.editProdi);
        editJurusan = (EditText) findViewById(R.id.editJurusan);
        editUmur = (EditText) findViewById(R.id.editUmur);
        btnSimpan.setOnClickListener(this);
        btnTutup.setOnClickListener(this);
        _Mahasiswa_Id = 0;
        Intent intent = getIntent();
        _Mahasiswa_Id = intent.getIntExtra("mahasiswa_Id",0);
        MahasiswaCRUD mahasiswaCRUD = new MahasiswaCRUD(this);
        Mahasiswa mahasiswa = new Mahasiswa();
        ///////
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.btnSimpan)){
            MahasiswaCRUD mahasiswaCRUD = new MahasiswaCRUD(this);
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.umur = Integer.parseInt(editUmur.getText().toString());
            mahasiswa.jurusan = editJurusan.getText().toString();
            mahasiswa.prodi = editProdi.getText().toString();
            mahasiswa.kelas = editKelas.getText().toString();
            mahasiswa.nama = editNama.getText().toString();
            mahasiswa.nobp = editNobp.getText().toString();
            mahasiswa.mahasiswa_ID = _Mahasiswa_Id;


            if(_Mahasiswa_Id == 0) {
                _Mahasiswa_Id = mahasiswaCRUD.insert(mahasiswa);
                Toast.makeText(this, "Data Dimasukan", Toast.LENGTH_SHORT).show();
            } else {
                mahasiswaCRUD.update(mahasiswa);
                Toast.makeText(getApplicationContext(), "Data di Update",Toast.LENGTH_SHORT).show();
            }
        } else if(v == findViewById(R.id.btnTutup)){
            finish();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mahasiswa_detil,null);
        return super.onCreateOptionsMenu(menu);
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==  R.id.action_setting){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


/*
        else if(v == findViewById(R.id.btnHapus)){
            MahasiswaCRUD mahasiswaCRUD = new MahasiswaCRUD(this);
            mahasiswaCRUD.delete(_Mahasiswa_Id);
            Toast.makeText(this, "Data Dihapus",Toast.LENGTH_SHORT).show();
            finish();
        }

 */