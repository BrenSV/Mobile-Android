package com.mobicode.archivos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private TextView txtTexto;
    private String line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTexto = findViewById(R.id.txtTexto);
        InputStream inputStream= getResources().openRawResource(R.raw.texto);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader,1000);

        try {
            while(true) {
                line = bufferedReader.readLine();
                if (line == null) break;
                txtTexto.append(String.format(" %s\n", line));
            }
            bufferedReader.close();
            streamReader.close();
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}