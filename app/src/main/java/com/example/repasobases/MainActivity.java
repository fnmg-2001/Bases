package com.example.repasobases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    SQLiteHelper helper = new SQLiteHelper(this, "bdMusica.db", null,1);
    SQLiteDatabase db=helper.getWritableDatabase();

    ContentValues values= new ContentValues();
    values.put("titulo", "Don Giovanni");
    values.put("compositor", "W.A. Mozart");
    values.put("year", 1787);
    db.insert("operas",null, values);

    //consultamos los datos de la tabla operas
    Cursor cursor = db.query("operas", null, null, null, null, null, null);
    //los mostramos en el cuadro de texto que tenemos en el layout
    String titulo, compositor, year;
    txtTexto.append("\n Tabla operas \n-----------");
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
        titulo = cursor.getString(1);
        compositor = cursor.getString(2);
        year = cursor.getString(3);
        txtTexto.append("\n" + titulo + " " + compositor + " " + year);
        cursor.moveToNext();
    }
    db.close();
}