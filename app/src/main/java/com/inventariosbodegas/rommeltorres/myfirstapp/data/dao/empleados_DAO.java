package com.inventariosbodegas.rommeltorres.myfirstapp.data.dao;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface empleados_DAO {
    @Insert
    public void insert(EMPLEADOS empleados);

    @Insert
    public void insertAll(List<EMPLEADOS> empleadosList);

    @Insert
    Long insertId(EMPLEADOS empleados);

    @Delete
    void delete(EMPLEADOS empleados);

    @Update
    void update(EMPLEADOS empleados);

    @Query("UPDATE EMPLEADOS SET EMP_NOMBRE=:EMP_NOMBRE WHERE EMP_CEDULA=:EMP_CEDULA")
    void updateByCedula(String EMP_NOMBRE, String EMP_CEDULA);

    @Query("SELECT * FROM EMPLEADOS WHERE EMP_NOMBRE IN(:NOMBRESList)")
    List<EMPLEADOS> selectByNombres(List<String> NOMBRESList);

    @Query("SELECT * FROM EMPLEADOS WHERE EMP_CEDULA=:EMP_CEDULA LIMIT 1")
    EMPLEADOS selectByCedula(String EMP_CEDULA);

    @Query("SELECT * FROM EMPLEADOS")
    LiveData<List<EMPLEADOS>> getAll();

    @Query("SELECT * FROM EMPLEADOS WHERE EMP_STATUSSERVER=1 LIMIT 1")
    EMPLEADOS getUnSend();

    @Query("UPDATE EMPLEADOS SET EMP_STATUSSERVER=1 WHERE EMP_ID=:EMP_ID")
    public void changeStatus(int EMP_ID);

    @Query("UPDATE EMPLEADOS SET EMP_STATUSSERVER=0 WHERE EMP_ID=:EMP_ID")
    public void updateStatus(int EMP_ID);

}