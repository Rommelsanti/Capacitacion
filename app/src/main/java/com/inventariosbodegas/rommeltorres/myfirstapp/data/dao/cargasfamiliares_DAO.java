package com.inventariosbodegas.rommeltorres.myfirstapp.data.dao;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.CARGAS_FAMILIARES;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface cargasfamiliares_DAO {
    @Insert
    void insert(CARGAS_FAMILIARES cargas_familiares);

    @Insert
    void insertAll(List<CARGAS_FAMILIARES> cargas_familiaresList);

    @Query("SELECT * FROM CARGAS_FAMILIARES WHERE EMP_ID=:EMP_ID")
    List<CARGAS_FAMILIARES> getByEMP_ID(int EMP_ID);

}
