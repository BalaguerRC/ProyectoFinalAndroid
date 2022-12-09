package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.myapplication.Entidades.Productos;
import com.example.myapplication.adaptadores.ListaProductosAdapter;

import java.util.ArrayList;

public class ListaDeProductos extends AppCompatActivity {
    RecyclerView listaProductoss;
    ArrayList<Productos> listaArraynota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_productos);
        listaProductoss = findViewById(R.id.listaProductoss);
        listaProductoss.setLayoutManager(new LinearLayoutManager(this));

        DBProductos DBProductos =new DBProductos(ListaDeProductos.this);
        listaArraynota= new ArrayList<>();

        ListaProductosAdapter adapter=new ListaProductosAdapter(DBProductos.mostrarProducto());

        listaProductoss.setAdapter(adapter);

        DbHelper dbHelper=new DbHelper(ListaDeProductos.this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
    }
}