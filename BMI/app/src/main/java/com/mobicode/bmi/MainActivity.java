package com.mobicode.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtPeso, edtEstatura;
    private Button btnCalculo;
    private TextView txtResultado;
    private SharedPreferences preferences;
    private String peso, estatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("DATA", MODE_PRIVATE);


        edtPeso = findViewById(R.id.edtPeso);
        edtEstatura = findViewById(R.id.edtEstatura);
        btnCalculo = findViewById(R.id.btnCalculo);
        txtResultado = findViewById(R.id.txtResultado);

        btnCalculo.setOnClickListener(e -> {
            calculaBMI();
        });

        edtPeso.setText(preferences.getString("peso","0"));
        edtEstatura.setText(preferences.getString("estatura", "0"));
    }


    @Override
    public void onClick(View view) {
        calculaBMI();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        peso = edtPeso.getText().toString();
        estatura = edtEstatura.getText().toString();
        editor.putString("peso", peso);
        editor.putString("estatura",estatura);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editor = preferences.edit();
        peso = edtPeso.getText().toString();
        estatura = edtEstatura.getText().toString();
        editor.putString("peso", peso);
        editor.putString("estatura",estatura);
        editor.apply();
    }

    public void calculaBMI(){
        int masa =  Integer.parseInt(edtPeso.getText().toString());
        double estatura = Double.parseDouble(edtEstatura.getText().toString()) / 100;
        double bmi = masa/ Math.pow(estatura,2);
        Locale locale = Locale.getDefault();
        String s = getResources().getString(R.string.bmi) + " " + String.format(locale,"%.3f",bmi);
        txtResultado.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.edit_menu,menu);
        return true;
    }
}