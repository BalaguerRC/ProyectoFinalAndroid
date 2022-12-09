package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Nuevo extends AppCompatActivity {
    EditText Titulo,Descripcion,Precio;
    Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        Titulo=(EditText) findViewById(R.id.EditTitulo);
        btnAgregar=(Button) findViewById(R.id.BtnGuardar);
        Descripcion=(EditText) findViewById(R.id.EditDescripcion);
        Precio= (EditText)findViewById(R.id.EditPrecio);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBProductos DBProductos = new DBProductos(Nuevo.this);
                if (!Titulo.getText().toString().equals("") && !Descripcion.getText().toString().equals("") && !Precio.getText().toString().equals("")) {
                    long id= DBProductos.insertarProducto(Titulo.getText().toString(),Descripcion.getText().toString(),Precio.getText().toString());

                    if(id>0){
                        Toast.makeText(Nuevo.this,"Agregado", Toast.LENGTH_SHORT).show();
                        Titulo.setText("");
                        Descripcion.setText("");
                        Precio.setText("");
                        //Siguiente();
                    } else{
                        Toast.makeText(Nuevo.this,"Error", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Nuevo.this,"Llene todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void Siguiente(){
        Intent intent= new Intent(Nuevo.this,MainActivity.class);
        startActivity(intent);
    }
}