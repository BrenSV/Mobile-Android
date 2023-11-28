package com.mobicode.intentno2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivity = findViewById(R.id.btnActivity);
        btnActivity.setOnClickListener(e ->{
                Intent intent = new Intent();
                String packageName = "com.mobicode.intentno2";
                String className = "com.mobicode.intentno2.MainActivity2";
                intent.setClassName(packageName,className);
                startActivity(intent);
        });
    }
}