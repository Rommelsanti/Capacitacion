package com.inventariosbodegas.rommeltorres.myfirstapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EMPLEADOS {
    @PrimaryKey(autoGenerate = true)
    public int EMP_ID;
    public String EMP_NOMBRE;
    public String EMP_DIRECCION;
    public String EMP_TELEFONO;
    public String EMP_CEDULA;
}
