package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.Entidades.Productos;

import java.util.ArrayList;

public class DBProductos extends DbHelper{
    Context context;
    public DBProductos(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarProducto(String Titulo, String Descripcion, String Precio){
        long id=0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Titulo", Titulo);
            values.put("Descripcion", Descripcion);
            values.put("Precio", Precio);

            id = db.insert(DBProductos, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
    public ArrayList<Productos> mostrarProducto(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Productos> listaNotas= new ArrayList<>();
        Productos productos =null;
        Cursor cursorN=null;
        cursorN=db.rawQuery("Select * from " + DBProductos,null);
        if(cursorN.moveToFirst()){
            do{
                productos =new Productos();
                productos.setId(cursorN.getInt(0));
                productos.setTitulo(cursorN.getString(1));
                productos.setDescripcion(cursorN.getString(2));
                productos.setPrecio(cursorN.getString(3));
                listaNotas.add(productos);
            }while(cursorN.moveToNext());
        }
        cursorN.close();
        return listaNotas;
    }
    public Productos verProducto(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Productos productos =null;
        Cursor cursorN;

        cursorN=db.rawQuery("Select * from " + DBProductos + " where id=" +id+ " limit 1",null);

        if(cursorN.moveToFirst()) {
            productos = new Productos();
            productos.setId(cursorN.getInt(0));
            productos.setTitulo(cursorN.getString(1));
            productos.setDescripcion(cursorN.getString(2));
            productos.setPrecio(cursorN.getString(3));
        }
        cursorN.close();
        return productos;
    }
    public boolean editarProducto(int id, String Titulo, String Descripcion, String Precio){
        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("update "+ DBProductos + " set Titulo = '"+Titulo+"', Descripcion ='"+Descripcion+"', Precio = '"+Precio+"' where id = '"+id+"'");
            correcto=true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarProducto(int id){
        boolean correcto=false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("Delete from "+ DBProductos +" where id= '"+id+"'");
            correcto=true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }

}
