package com.inventariosbodegas.rommeltorres.myfirstapp.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class EMPLEADOS {
    @PrimaryKey(autoGenerate = true)
    public int EMP_ID;
    public String EMP_NOMBRE;
    public String EMP_DIRECCION;
    public String EMP_TELEFONO;
    public String EMP_CEDULA;

    @Ignore
    public String toString() {
        return EMP_NOMBRE;
    }
    @Ignore
    public int getId() {
        return EMP_ID;
    }
    @Ignore
    public String getValue() {
        return EMP_NOMBRE;
    }

    @Ignore
    private int CalculoHorasExtra;
}
