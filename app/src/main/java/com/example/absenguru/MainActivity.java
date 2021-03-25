package com.example.absenguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.absenguru.app.Server;

import java.util.HashMap;

import static java.nio.file.Paths.get;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    String idguru;
    private String JSON_STRING;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idguru = getIntent().getStringExtra(Server.TAG_IDGURU);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        idguru = sharedpreferences.getString(Server.TAG_IDGURU, null);
    }

    public void keluar(View view) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    public void absenmasuk(View view) {
        Intent intent = new Intent(MainActivity.this,AbsenMasuk.class);
        intent.putExtra(Server.TAG_IDGURU, idguru);
        startActivity(intent);
    }

    public void absenkeluar(View view) {
        Intent intent = new Intent(MainActivity.this,AbsenKeluar.class);
        intent.putExtra(Server.TAG_IDGURU, idguru);
        startActivity(intent);
    }

    public void tampilabsenmasuk(View view) {
        Intent intent = new Intent(MainActivity.this,TampilAbsenMasuk.class);
        intent.putExtra(Server.TAG_IDGURU, idguru);
        startActivity(intent);
    }

    public void tampilabsenkeluar(View view) {
        Intent intent = new Intent(MainActivity.this,TampilAbsenKeluar.class);
        intent.putExtra(Server.TAG_IDGURU, idguru);
        startActivity(intent);
    }
}