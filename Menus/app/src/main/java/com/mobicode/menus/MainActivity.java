package com.mobicode.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtMenu, edtContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMenu = findViewById(R.id.edtMenu);
        edtContext = findViewById(R.id.edtContext);
        registerForContextMenu(edtMenu);
        registerForContextMenu(edtContext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mymenu,menu);
//        getMenuInflater().inflate(R.menu.myeditablemenu,menu); //porque este no es un menu normal, sino es contextual
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        if (v.getId() == R.id.edtMenu) {
            menu.setHeaderTitle("Contextual");
            menuInflater.inflate(R.menu.myoptionmenu,menu);
        }
        if(v.getId() ==R.id.edtContext) {
            menu.setHeaderTitle("Editar");
            menuInflater.inflate(R.menu.myeditablemenu,menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuOpcion1) {
            edtContext.setText("Haz presionado la opcion 1");
            return true;
        }
        if(item.getItemId() == R.id.mnuOpcion2) {
            edtContext.setText("Haz presionado la opcion 2");
            return true;
        }
        if(item.getItemId() == R.id.mnuOpcion3) {
            edtContext.setText("Haz presionado la opcion 3");
            return true;
        }
        if(item.getItemId() == R.id.mnuCopiar) {
            edtContext.setText("Copiando");
            return true;
        }
        if(item.getItemId() == R.id.mnuCortar) {
            edtContext.setText("Cortando");
            return true;
        }
        if(item.getItemId() == R.id.mnuPegar) {
            edtContext.setText("Pegando");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuItem1) {
            edtContext.setText("Haz presionado la check1");
            return true;
        }
        if (item.getItemId() == R.id.mnuItem2) {
            edtContext.setText("Haz presionado la check2");
            return true;
        }
        if (item.getItemId() == R.id.mnuItem4) {
            edtContext.setText("Haz presionado la option1");
            return true;
        }
        if (item.getItemId() == R.id.mnuItem5) {
            edtContext.setText("Haz presionado la option2");
            return true;
        }
        if (item.getItemId() == R.id.mnuItem6) {
            edtContext.setText("Haz presionado la option3");
            return true;
        }
        if (item.getItemId() == R.id.mnuSub1) {
            edtContext.setText("Haz presionado la submenu1");
            return true;
        }
        if (item.getItemId() == R.id.mnuSub2) {
            edtContext.setText("Haz presionado la submenu2");
            return true;
        }
        return super.onContextItemSelected(item);
    }
}