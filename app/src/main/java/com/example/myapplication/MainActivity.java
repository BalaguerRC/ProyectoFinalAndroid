package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.Entidades.Productos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton Registro, BtnLista,BtnInfo, BtnSalir;
    //FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registro= findViewById(R.id.BtnRegistro);
        BtnLista=findViewById(R.id.BtnLista);
        BtnInfo=findViewById(R.id.BtnInfo);
        BtnSalir= findViewById(R.id.BtnSalir);

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Vista Registro", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this,Nuevo.class);
                startActivity(intent);
            }
        });
        BtnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Vista Lista de Productos", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this,ListaDeProductos.class);
                startActivity(intent);
            }
        });

        BtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Vista Informacion", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this,Info.class);
                startActivity(intent);
            }
        });

        BtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"Adios", Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
            }
        });
    }
}