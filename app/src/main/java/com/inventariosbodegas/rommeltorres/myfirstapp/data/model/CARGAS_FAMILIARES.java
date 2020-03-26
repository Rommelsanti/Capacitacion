package com.inventariosbodegas.rommeltorres.myfirstapp.data.model;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity()
public class CARGAS_FAMILIARES {
    @PrimaryKey(autoGenerate = true)
    public int CARFAM_ID;
    public String CARFAM_NOMBRE;
    public int EMP_ID;

    @Ignore
    EMPLEADOS EMPLEADOS;

}
