package com.example.absenguru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.absenguru.app.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilAbsenKeluar extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView listView;
    SharedPreferences sharedPreferences;
    private TextView textViewId;
    private String idguru;
    private String JSON_STRING;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_absen_keluar);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
        Intent intent = getIntent();
        textViewId = (TextView) findViewById(R.id.textViewId);
        idguru = intent.getStringExtra(Server.TAG_IDGURU);
        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        textViewId.setText(idguru);
        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        idguru = sharedPreferences.getString(Server.TAG_IDGURU, null);
    }
    private void showAbsenmasuk(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Server.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String idabsen = jo.getString(Server.TAG_IDABSEN);
                String tgl = jo.getString(Server.TAG_ABSENTGL);
                String jam = jo.getString(Server.TAG_ABSENJAM);
                String idguru = jo.getString(Server.TAG_IDGURU);

                HashMap<String,String> pemesanans = new HashMap<>();
                pemesanans.put(Server.TAG_IDABSEN,idabsen);
                pemesanans.put(Server.TAG_ABSENTGL,tgl);
                pemesanans.put(Server.TAG_ABSENJAM,jam);
                pemesanans.put(Server.TAG_IDGURU,idguru);
                list.add(pemesanans);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(TampilAbsenKeluar.this, list, R.layout.list_item,
                new String[]{Server.TAG_IDABSEN,Server.TAG_ABSENTGL,Server.TAG_ABSENJAM,Server.TAG_IDGURU},
                new int[]{R.id.idabsen, R.id.tgl,R.id.jam,R.id.idguru});

        listView.setAdapter(adapter);
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilAbsenKeluar.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showAbsenmasuk();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Server.URL_GET_ALLL, idguru);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    public void back(View view) {
        startActivity(new Intent(TampilAbsenKeluar.this,MainActivity.class));
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(TampilAbsenKeluar.this,TampilDetilAbsenKeluar.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Server.TAG_IDABSEN).toString();
        intent.putExtra(Server.ABSN_ID,empId);
        intent.putExtra(Server.TAG_IDGURU, idguru);
        startActivity(intent);
    }
}