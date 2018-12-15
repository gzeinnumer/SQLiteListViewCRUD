package com.zein.sqlite_listviewcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MahasiswaDetilData extends AppCompatActivity {

    private Button btnUpdate, btnTutup;
    private EditText editNobp, editNama, editKelas, editProdi, editJurusan, editUmur;
    private TextView tvTitle;

    Mahasiswa setMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_detil_data);

        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        btnTutup= (Button) findViewById(R.id.btnTutup);
        editNobp = (EditText) findViewById(R.id.tvNoBp);
        editNama = (EditText) findViewById(R.id.tvNama);
        editKelas = (EditText) findViewById(R.id.tvKelas);
        editProdi = (EditText) findViewById(R.id.tvProdi);
        editJurusan = (EditText) findViewById(R.id.tvJurusan);
        editUmur = (EditText) findViewById(R.id.tvUmur);
        tvTitle = findViewById(R.id.tvTitle);

        final MahasiswaCRUD mahasiswaCRUD = new MahasiswaCRUD(this);
        Intent intent=getIntent();
        final String seletedItem = intent.getStringExtra("indexClick");
        //Toast.makeText(getApplicationContext(), seletedItem, Toast.LENGTH_SHORT).show();

        final Mahasiswa mahasiswa = mahasiswaCRUD.getMahasiswaById(Integer.parseInt(seletedItem));
        editNobp.setText(mahasiswa.nobp);
        editNama.setText(mahasiswa.nama);
        editKelas.setText(mahasiswa.kelas);
        editProdi.setText(mahasiswa.prodi);
        editJurusan.setText(mahasiswa.jurusan);
        editUmur.setText(String.valueOf(mahasiswa.umur));

        btnTutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String selectedStatus = intent.getStringExtra("statusUpdate");
        if(selectedStatus.equals("edit")){
            editNobp.setEnabled(true);
            editNama.setEnabled(true);
            editKelas.setEnabled(true);
            editProdi.setEnabled(true);
            editJurusan.setEnabled(true);
            editUmur.setEnabled(true);

            btnTutup.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);

            tvTitle.setText("Edit Data Mahasiswa");

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String setNobp = editNobp.getText().toString();
                    String setNama = editNama.getText().toString();
                    String setKelas = editKelas.getText().toString();
                    String setProdi = editProdi.getText().toString();
                    String setJurusan = editJurusan.getText().toString();
                    int setUmur = Integer.parseInt(editUmur.getText().toString());
                    setMahasiswa = new Mahasiswa(Integer.valueOf(seletedItem),
                            setNobp, setNama, setKelas, setProdi, setJurusan, setUmur);
                    mahasiswaCRUD.update(setMahasiswa);

                    Toast.makeText(getApplicationContext(),"Diupdate",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
