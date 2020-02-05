package com.example.senddatatoserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Name,FatherName;
    AlertDialog.Builder builder;
    String urlWithSSlCirtificate = "https://c606e7ae.ngrok.io/registerUser.php";
    String url = "https://192.168.64.2/registerUser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.Name);
        FatherName = findViewById(R.id.fatherName);
    }

    public void submit(View view) {
        insertData();

    }
    private void insertData() {

        final String name = Name.getText().toString();
        final String fatherName = FatherName.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlWithSSlCirtificate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Respnose: " + response, Toast.LENGTH_SHORT).show();
                Log.d("Response",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String, String>();
                params.put("myName",name);
                params.put("myFatherName",fatherName);
                return params;
            }


        };
        //MySingleton.getInstance(MainActivity.this).addTorequestQue(stringRequest);
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
