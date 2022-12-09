package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Entidades.Productos;

public class Editar extends AppCompatActivity {
    EditText Titulo,Descripcion,Precio;
    Button Guardar;
    ImageButton Edit,Delete;
    TextView Detalles;
    boolean correcto=false;
    Productos productos;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        Titulo=(EditText) findViewById(R.id.EditTitulo);
        Descripcion=(EditText) findViewById(R.id.EditDescripcion);
        Precio=(EditText) findViewById(R.id.EditPrecio);
        Guardar=(Button) findViewById(R.id.BtnGuardar);
        Edit=findViewById(R.id.btnEditar);
        Delete= findViewById(R.id.btnEliminar);
        Detalles= findViewById(R.id.TxtDetalles);

        if(savedInstanceState==null){
            Bundle extras= getIntent().getExtras();
            if(extras==null){
                id= Integer.parseInt(null);
            }else{
                id=extras.getInt("id");
            }
        }
        else{
            id=(int) savedInstanceState.getSerializable("id");
        }

        final DBProductos DBProductos =new DBProductos(Editar.this);
        productos = DBProductos.verProducto(id);
        if(productos !=null){
            Titulo.setText(productos.getTitulo());
            Descripcion.setText(productos.getDescripcion());
            Precio.setText(productos.getPrecio());
            Detalles.setVisibility(View.INVISIBLE);
            Edit.setVisibility(View.INVISIBLE);
            //Delete.setVisibility(View.INVISIBLE);
        }


        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Titulo.getText().toString().equals("") || !Descripcion.getText().toString().equals("") || !Precio.getText().toString().equals("")){
                    correcto= DBProductos.editarProducto(id,Titulo.getText().toString(),Descripcion.getText().toString(),Precio.getText().toString());
                    if(correcto){
                        Toast.makeText(Editar.this, "Modificado", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else{
                        Toast.makeText(Editar.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Editar.this, "Llenar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(Editar.this);
                builder.setMessage("Quiere elimanar el Producto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //boolean correcto
                                if(DBProductos.eliminarProducto(id)){
                                    Toast.makeText(Editar.this,"Producto Eliminado", Toast.LENGTH_SHORT).show();
                                    lista();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Editar.this,"OK", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
    private void verRegistro(){
        Intent intent=new Intent(this, VerActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    private void lista(){
        Intent intent=new Intent(Editar.this, ListaDeProductos.class);
        startActivity(intent);
    }
}