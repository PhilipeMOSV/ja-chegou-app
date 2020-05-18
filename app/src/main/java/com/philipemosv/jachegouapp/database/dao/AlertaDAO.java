package com.philipemosv.myapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.philipemosv.myapplication.models.Alerta;

import java.util.List;

@Dao
public interface AlertaDAO {

    @Insert
    void salvar(Alerta alerta);

    @Query("SELECT * FROM alerta")
    List<Alerta> todos();

    @Delete
    void remove(Alerta alerta);

    @Update
    void editar(Alerta alerta);

}
