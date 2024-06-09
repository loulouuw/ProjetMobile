package com.example.projetmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InfoJourBDD extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "infoJour.db";
    private static final int DATABASE_VERSION = 1;

    public InfoJourBDD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE infoJour (id INTEGER PRIMARY KEY AUTOINCREMENT, info TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS infoJour");
        onCreate(db);
    }

    // Méthodes pour insérer, mettre à jour, supprimer et récupérer les données
}
