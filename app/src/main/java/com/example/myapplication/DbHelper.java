package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NOMBRE="Movil2.db";
    public static final String DBProductos ="Productos";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ DBProductos +"(" +
                "id integer primary key autoincrement, " +
                "Titulo text not null," +
                "Descripcion text not null," +
                "Precio text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table " + DBProductos);
        onCreate(sqLiteDatabase);
    }
}
