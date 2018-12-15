package com.zein.sqlite_listviewcrud;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTambah;
    TextView mahasiswa_Id;

    //for dialog
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTambah= findViewById(R.id.btnTambahData);
        btnTambah.setOnClickListener(this);
        final MahasiswaCRUD mahasiswaCRUD = new MahasiswaCRUD(this);
        //dialog
        context=this;

        ArrayList<HashMap<String,String>> mahasiswaList = mahasiswaCRUD.getMahasiswaList();
        ListView listView = findViewById(R.id.list);
        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(), mahasiswaList,
                R.layout.activity_tampil_mahasiswa,
                new String[]{"id","nama"},
                new int[]{R.id.idMahasiswa, R.id.nama_mahasiswa});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mahasiswa_Id = (TextView) view.findViewById(R.id.idMahasiswa);
                String valMahasiswaId = mahasiswa_Id.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MahasiswaDetilData.class);
                intent.putExtra("indexClick", valMahasiswaId);
                intent.putExtra("statusUpdate", "notEdit");
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mahasiswa_Id = (TextView) view.findViewById(R.id.idMahasiswa);
                final String valMahasiswaId = mahasiswa_Id.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                final View inflater = layoutInflater.inflate(R.layout.dialog_button,null);
                Button btn1 = inflater.findViewById(R.id.btnDialogEdit);
                Button btn2 = inflater.findViewById(R.id.btnDialogDelete);
                builder.setView(inflater);
                final AlertDialog alertDialog=builder.create();

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MahasiswaDetilData.class);
                        intent.putExtra("indexClick", valMahasiswaId);
                        intent.putExtra("statusUpdate", "edit");
                        startActivity(intent);
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mahasiswaCRUD.delete(Integer.parseInt(valMahasiswaId));
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Didelete",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
                return true;
            }
        });
    }
    @Override
    public void onClick(View v) {
        if( v == findViewById(R.id.btnTambahData)){
            Intent intent = new Intent(this, MahasiswaInputData.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_setting){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
