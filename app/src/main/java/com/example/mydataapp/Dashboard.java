package com.example.mydataapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    EditText etNIM, etNama, etStudi, etKelas, etAlamat, etEmail;

    Button tmblSimpan, tmblLogout;

    public static final String MyPREFERENCES = "mypreference";

    public static final String nim = "keyNIM";
    public static final String nama = "keyNama";
    public static final String studi = "keyStudi";
    public static final String kelas = "keyKelas";
    public static final String alamat = "keyAlamat";
    public static final String email = "keyEmail";

    SharedPreferences sharedPreferences;

    ListView listView;

    ArrayList<String> dataMahasiswa;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_dashboard);

        etNIM = (EditText) findViewById(R.id.nim);
        etNama = (EditText) findViewById(R.id.nama);
        etStudi = (EditText) findViewById(R.id.studi);
        etKelas = (EditText) findViewById(R.id.kelas);
        etAlamat = (EditText) findViewById(R.id.alamat);
        etEmail = (EditText) findViewById(R.id.email);

        tmblSimpan = (Button) findViewById(R.id.btnSimpan);
        tmblLogout = (Button) findViewById(R.id.btnLogout);

        listView = (ListView) findViewById(R.id.list);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        dataMahasiswa = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                Dashboard.this,
                android.R.layout.simple_list_item_1,
                dataMahasiswa
        );

        listView.setAdapter(adapter);

        tmblSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nNim = etNIM.getText().toString();
                String nNama = etNama.getText().toString();
                String nStudi = etStudi.getText().toString();
                String nKelas = etKelas.getText().toString();
                String nAlamat = etAlamat.getText().toString();
                String nEmail = etEmail.getText().toString();

                if (nNim.isEmpty() || nNama.isEmpty() || nStudi.isEmpty() || nKelas.isEmpty() || nAlamat.isEmpty() || nEmail.isEmpty()) {

                    Toast.makeText(Dashboard.this, "Data Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }

                else {

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(nim, nNim);
                    editor.putString(nama, nNama);
                    editor.putString(studi, nStudi);
                    editor.putString(kelas, nKelas);
                    editor.putString(alamat, nAlamat);
                    editor.putString(email, nEmail);

                    editor.commit();

                    String data =
                            "NIM : " + nNim + "\n" + "Nama : " + nNama + "\n" + "Program Studi : " + nStudi + "\n" + "Kelas : " + nKelas + "\n" + "Alamat : " + nAlamat + "\n" + "E-Mail : " + nEmail;

                    dataMahasiswa.clear();

                    dataMahasiswa.add(data);

                    adapter.notifyDataSetChanged();

                    Toast.makeText(Dashboard.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();

                    etNIM.setText("");
                    etNama.setText("");
                    etStudi.setText("");
                    etKelas.setText("");
                    etAlamat.setText("");
                    etEmail.setText("");
                }
            }
        });

        tmblLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.clear();

                editor.commit();

                Toast.makeText(Dashboard.this,
                        "Logout Berhasil",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Dashboard.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        });
    }
}