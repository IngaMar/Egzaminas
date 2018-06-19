package com.example.dariusm.egzaminas.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DariusM on 19/06/2018.
 */

public class DatabaseSQLite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";


    private static final String TABLE_KLIENTAI = "klientai";
    private static final String ID = "id";
    private static final String VARDAS = "vardas_pavarde";
    private static final String METAI = "gimimo_data";
    private static final String TELEFON = "telefono_numeris";
    private static final String TIPAS = "kliento_tipas";


    public DatabaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_KLIENTAI_TABLE = "CREATE TABLE " + TABLE_KLIENTAI + "("
                + ID + " INTEGER PRIMARY KEY,"
                + VARDAS + " TEXT,"
                + METAI + " REAL,"
                + TELEFON + " REAL,"
                + TIPAS + ")";

        db.execSQL(CREATE_KLIENTAI_TABLE);
    }


    public void addKlientas(Klientas klientas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VARDAS, klientas.getVardas());
        values.put(METAI, klientas.getMetai());
        values.put(TELEFON, klientas.getTelefon());
        values.put(TIPAS, klientas.getTipas());

        // Inserting Row
        db.insert(TABLE_KLIENTAI, null, values);

        // Closing database connection
        db.close();
    }

    public List<Klientas> getAllKlientai() {
        List<Klientas> klientai = new ArrayList<Klientas>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_KLIENTAI;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Klientas klientas = new Klientas();

                klientas.setId(Integer.parseInt(cursor.getString(0)));
                klientas.setVardas(cursor.getString(1));
                klientas.setMetai(cursor.getInt(2));
                klientas.setTelefon(cursor.getInt(3));
                klientas.setTipas(cursor.getString(4));


                // adding user to list
                klientai.add(klientas);
            } while (cursor.moveToNext());
        }

        // return papuosalaiSQLite list
        return klientai;
    }

    public List<Klientas> getKlientaiByName(String name) {
        List<Klientas> klientai = new ArrayList<Klientas>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM klientai WHERE vardas_pavarde LIKE '%" + name + "%'", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Klientas klientas = new Klientas();

                klientas.setId(Integer.parseInt(cursor.getString(0)));
                klientas.setVardas(cursor.getString(1));
                klientas.setMetai(cursor.getInt(2));
                klientas.setTelefon(cursor.getInt(3));
                klientas.setTipas(cursor.getString(4));

                // adding user to list
                klientai.add(klientas);
            } while (cursor.moveToNext());
        }

        // return papuosalaiSQLite list
        return klientai;

    }

    public Klientas getKlientas(int id) {
        Klientas klientas = new Klientas();

        List<Klientas> klientai = new ArrayList<Klientas>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM klientai WHERE id = " + id + "", null);
        if (cursor.moveToFirst()) {
            do {
                klientas.setId(Integer.parseInt(cursor.getString(0)));
                klientas.setVardas(cursor.getString(1));
                klientas.setMetai(cursor.getInt(2));
                klientas.setTelefon(cursor.getInt(3));
                klientas.setTipas(cursor.getString(4));

                klientai.add(klientas);
            } while (cursor.moveToNext());
        }
        return klientai.get(0);
    }


    public void updateKlientas(Klientas klientas) {
        ContentValues cv = new ContentValues();
        cv.put(VARDAS, klientas.getVardas());
        cv.put(METAI, klientas.getMetai());
        cv.put(TELEFON, klientas.getTelefon());
        cv.put(TIPAS, klientas.getTipas());

        getReadableDatabase().update(TABLE_KLIENTAI, cv, " id = " + klientas.getId(), null);
    }

    public void deleteKlientas(int id) {

        getReadableDatabase().delete(TABLE_KLIENTAI, ID + "=?", new String[]{String.valueOf(id)});

        //Closing database connection;
        getReadableDatabase().close();
    }
}
