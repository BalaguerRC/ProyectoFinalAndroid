package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Entidades.Productos;

public class VerActivity extends AppCompatActivity {
    EditText Titulo,Descripcion,Precio;
    Button Guardar;
    ImageButton Edit,Delete;
    Productos productos;
    TextView Edicion;
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
        Edicion= findViewById(R.id.TxtEditar);

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

        DBProductos DBProductos =new DBProductos(VerActivity.this);
        productos = DBProductos.verProducto(id);
        if(productos !=null){
            Titulo.setText(productos.getTitulo());
            Descripcion.setText(productos.getDescripcion());
            Precio.setText(productos.getPrecio());
            //
            Guardar.setVisibility(View.INVISIBLE);
            Titulo.setInputType(InputType.TYPE_NULL);
            Descripcion.setInputType(InputType.TYPE_NULL);
            Precio.setInputType(InputType.TYPE_NULL);
            Edicion.setVisibility(View.INVISIBLE);
        }


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VerActivity.this,"Vista Edicion", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(VerActivity.this, com.example.myapplication.Editar.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("Quiere elimanar el Producto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //boolean correcto
                                if(DBProductos.eliminarProducto(id)){
                                    Toast.makeText(VerActivity.this,"Producto Eliminado", Toast.LENGTH_SHORT).show();
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(VerActivity.this,"OK", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
    private void lista(){
        Intent intent=new Intent(VerActivity.this, ListaDeProductos.class);
        startActivity(intent);
    }
}