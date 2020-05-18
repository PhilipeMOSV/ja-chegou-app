package com.philipemosv.jachegouapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.philipemosv.myapplication.database.dao.AlertaDAO;
import com.philipemosv.myapplication.models.Alerta;

@Database(entities = {Alerta.class}, version = 1, exportSchema = false)
public abstract class AlertaDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "alerta.db";

    public abstract AlertaDAO getRoomAlertaDAO();

    public static AlertaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AlertaDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .build();
    }

}
