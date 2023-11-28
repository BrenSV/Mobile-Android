package com.mobicode.intentno1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private EditText edtLatitud, edtLongitutd;
    private Button btnGraficar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLatitud = findViewById(R.id.edtLatitud);
        edtLongitutd = findViewById(R.id.edtLongitud);
        btnGraficar = findViewById(R.id.btnGraficar);
/*        btnGraficar.setOnClickListener(e ->{
           String lat = edtLatitud.getText().toString();
           String lon = edtLongitutd.getText().toString();
        });
*/        btnGraficar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lat = edtLatitud.getText().toString();
                String lon = edtLongitutd.getText().toString();
                Uri uri = Uri.parse("geo:"+ lat +"," + lon);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}