package com.example.absenguru;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.absenguru.app.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TampilDetilAbsenMasuk extends AppCompatActivity {
    private TextView textViewKd;
    private TextView textViewTgl;
    private TextView textViewJam;
    private TextView textViewId;
    private String idabsen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_detil_absen_masuk);
        Intent intent = getIntent();

        idabsen = intent.getStringExtra(Server.ABSN_ID);
        textViewKd = (TextView) findViewById(R.id.textViewKd);
        textViewTgl = (TextView) findViewById(R.id.textViewTgl);
        textViewJam = (TextView) findViewById(R.id.textViewJam);
        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewKd.setText(idabsen);
        getAbsen();
    }
    private void getAbsen() {
        class GetAbsen extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilDetilAbsenMasuk.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAbsen(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Server.URL_GET_MSK, idabsen);
                return s;
            }
        }
        GetAbsen ge = new GetAbsen();
        ge.execute();
    }

    private void showAbsen(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Server.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String idabsen = c.getString(Server.TAG_IDABSEN);
            String tgl = c.getString(Server.TAG_ABSENTGL);
            String jam = c.getString(Server.TAG_ABSENJAM);
            String idguru = c.getString(Server.TAG_IDGURU);
            textViewKd.setText(idabsen);
            textViewTgl.setText(tgl);
            textViewJam.setText(jam);
            textViewId.setText(idguru);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void back(View view) {
        startActivity(new Intent(TampilDetilAbsenMasuk.this,TampilAbsenMasuk.class));
    }
}