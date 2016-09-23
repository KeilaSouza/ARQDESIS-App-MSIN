package com.example.app_msin.myprojectapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    String matricula;
    Button consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matricula = ((EditText) findViewById(R.id.et_matricula)).getText().toString();
        consultar = (Button) findViewById(R.id.btn_consultar);

    }

    public void abrirActivity2(View v){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("matricula", matricula);
        startActivity(intent);
    }

}
