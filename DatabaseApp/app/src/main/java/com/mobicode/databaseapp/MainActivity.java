package com.mobicode.databaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    private static final int addID = Menu.FIRST + 1;
    private static final int deleteID = Menu.FIRST + 3;
    private DatabaseDemo helper = null;
    private Cursor cursorConstants = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); proporciona una interfaz de usuario

        helper = new DatabaseDemo(this); // la clase de basedatos ya existe, porque se creo
        cursorConstants = helper.getReadableDatabase().rawQuery(getString(R.string.select_from_constants_order_by_title), null);
        String[] from = {DatabaseDemo.TITLE,DatabaseDemo.VALUE};
        int [] to = {R.id.txtTitle, R.id.txtValue};
        ListAdapter adapter = new SimpleCursorAdapter(this,R.layout.row,cursorConstants,from,to,SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(menu.NONE,addID,menu.NONE,"Agregar...");
        menu.add(menu.NONE,deleteID,menu.NONE,"Borrar...");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case addID:
                add();
                break;
            case deleteID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                delete(info.id);
                break;
        }
        return super.onContextItemSelected(item);
    }


    private void add(){
        LayoutInflater i = LayoutInflater.from(this);
        View addView = i.inflate(R.layout.add_edit,null);
        final DialogWrapper dW = new DialogWrapper(addView);

        new AlertDialog.Builder(this)
                .setTitle(R.string.add)
                .setView(addView)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Procesamos el alta de registros
                processAdd(dW);
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //No hacemos nada
                    }
                })
                .show();
    }

    private void delete(long rowId){
        if (rowId != 0){
            new AlertDialog.Builder(this).setTitle(R.string.delete)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            proccessDelete(rowId);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //hacer nada
                        }
                    }).show();
        }

    }

    private void processAdd(DialogWrapper dialogWrapper){
        ContentValues contentValues = new ContentValues(2);
        contentValues.put(DatabaseDemo.TITLE, dialogWrapper.getConstante());
        contentValues.put(DatabaseDemo.VALUE,dialogWrapper.getValor());
        helper.getReadableDatabase().insert("constants",DatabaseDemo.TITLE,contentValues);
        cursorConstants.requery();
    }

    private void proccessDelete(long rowId){
        String[] args = {String.valueOf(rowId)};
        helper.getReadableDatabase().delete("constants", "_id = ?",args);
        cursorConstants.requery();
    }
    public static class DialogWrapper{
        private View base;
        private EditText edtConstante, edtValor;
        public DialogWrapper(View base) {
            this.base = base;
        }
        public View getBase() {
            return base;
        }

        public EditText getEdtConstante() {

            edtConstante = base.findViewById(R.id.edtConstante);
            return edtConstante;
        }

        public EditText getEdtValor() {
            return edtValor;
        }
        public String getConstante(){
            return getEdtConstante().getText().toString();
        }
        public Float getValor(){
            return Float.parseFloat(getEdtValor().getText().toString());
        }
    }

}