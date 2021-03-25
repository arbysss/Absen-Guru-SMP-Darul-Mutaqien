package com.example.absenguru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.absenguru.app.AppController;
import com.example.absenguru.app.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AbsenMasuk extends AppCompatActivity {
    private TextView textViewIdguru,textViewTgl,textViewJam;
    private String idguru;
    SharedPreferences sharedPreferences;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    ProgressDialog pDialog;
    Button btn_absen;
    Intent intent;
    int success;
    ConnectivityManager conMgr;
    private String url = Server.URL + "absenmasuk.php";
    private static final String TAG = AbsenMasuk.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen_masuk);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_absen = (Button) findViewById(R.id.btn_absen);
        textViewIdguru = (TextView) findViewById(R.id.textViewIdguru);
        textViewTgl = (TextView) findViewById(R.id.textViewTgl);
        textViewJam = (TextView) findViewById(R.id.textViewJam);

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        idguru = getIntent().getStringExtra(Server.TAG_IDGURU);
        textViewIdguru.setText(idguru);

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        idguru = sharedPreferences.getString(Server.TAG_IDGURU, null);

        btn_absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String id = textViewIdguru.getText().toString();
                String tgl = textViewTgl.getText().toString();
                String jam = textViewJam.getText().toString();
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkAbsen(id,tgl, jam);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        // Now we display formattedDate value in TextView
        textViewTgl.setText(""+formattedDate);

        Calendar cc = Calendar.getInstance();
        System.out.println("Current time => "+cc.getTime());
        SimpleDateFormat dff = new SimpleDateFormat("HH:mm:ss");
        String formattedDatee = dff.format(c.getTime());
        // formattedDate have current date/time
        Toast.makeText(this, formattedDatee, Toast.LENGTH_SHORT).show();
        // Now we display formattedDate value in TextView
        textViewJam.setText(""+formattedDatee);
    }
    public void back(View view) {
        startActivity(new Intent(AbsenMasuk.this,MainActivity.class));
    }

    private void checkAbsen(final String id,final String tgl,final String jam) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Absen ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Absen Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Absen!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        textViewIdguru.setText("");
                        textViewTgl.setText("");
                        textViewJam.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Absen Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("tgl", tgl);
                params.put("jam", jam);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}