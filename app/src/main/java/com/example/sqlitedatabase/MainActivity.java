package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

public class MainActivity extends AppCompatActivity {

    private EditText editTextusuario, editTextpais, editTexttipo,editTextId;
    private Button buttonInsertar, buttonBorrar, buttonActualizar, buttonVer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextusuario=(EditText) findViewById(R.id.EditTextUsuario);
        editTextId=(EditText) findViewById(R.id.EditTextID);
        editTextpais=(EditText) findViewById(R.id.EditTextPais);
        editTexttipo=(EditText) findViewById(R.id.EditTextTipo);
        buttonActualizar=(Button) findViewById(R.id.ButtonActualizar);
        buttonBorrar=(Button) findViewById(R.id.ButtonBorrar);
        buttonInsertar=(Button) findViewById(R.id.ButtonInsertar);
        buttonVer=(Button) findViewById(R.id.ButtonVer);

        DBhelper DB=new DBhelper(this);


       buttonInsertar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String usuario=editTextusuario.getText().toString();
               String id=editTextId.getText().toString();
               String pais=editTextpais.getText().toString();
               String tipo=editTexttipo.getText().toString();

               Boolean cheking=DB.insertar(id,pais,tipo,usuario);
               if (cheking==true){
                   Toast.makeText(MainActivity.this, "Dato añadido", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(MainActivity.this, "No se puedo añadir", Toast.LENGTH_SHORT).show();
               }
           }
       });

       buttonActualizar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String usuario=editTextusuario.getText().toString();
               String id=editTextId.getText().toString();
               String pais=editTextpais.getText().toString();
               String tipo=editTexttipo.getText().toString();

               Boolean cheking=DB.actualizar(id,pais,tipo,usuario);
               if (cheking==true){
                   Toast.makeText(MainActivity.this, "Dato actualizado", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(MainActivity.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
               }

           }
       });


       buttonBorrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String id=editTextId.getText().toString();

               Boolean cheking=DB.borrar(id);
               if (cheking==true){
                   Toast.makeText(MainActivity.this, "Dato borrado", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(MainActivity.this, "No se pudo borrarr", Toast.LENGTH_SHORT).show();
               }
           }
       });


       buttonVer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Cursor res=DB.getdata();
               if (res.getCount()==0){
                   Toast.makeText(MainActivity.this, "No existe", Toast.LENGTH_SHORT).show();
                   return;
               }
               StringBuffer buffer=new StringBuffer();
               while(res.moveToNext()){
                   buffer.append("id :"+res.getString(0)+"\n");
                   buffer.append("pais :"+res.getString(1)+"\n");
                   buffer.append("tipo :"+res.getString(2)+"\n");
                   buffer.append("usuario :"+res.getString(3)+"\n\n");
               }

               AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               builder.setCancelable(true);
               builder.setTitle("datos de usuario");
               builder.setMessage(buffer.toString());
               builder.show();
           }
       });

    }

}