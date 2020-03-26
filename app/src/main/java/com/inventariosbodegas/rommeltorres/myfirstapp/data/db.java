package com.inventariosbodegas.rommeltorres.myfirstapp.data;

import android.content.Context;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.dao.cargasfamiliares_DAO;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.dao.empleados_DAO;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.CARGAS_FAMILIARES;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EMPLEADOS.class, CARGAS_FAMILIARES.class}, version = 1, exportSchema = false)
public abstract class db extends RoomDatabase {
    public abstract empleados_DAO empleadosDao();

    public abstract cargasfamiliares_DAO cargasfamiliaresDao();

    private static db INSTANCE;

    public static db getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (db.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), db.class, "capacitacion").build();
                }
            }
        }
        return INSTANCE;
    }

}
