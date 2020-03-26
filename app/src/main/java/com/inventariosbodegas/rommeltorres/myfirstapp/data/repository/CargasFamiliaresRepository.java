package com.inventariosbodegas.rommeltorres.myfirstapp.data.repository;

import android.app.Application;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.dao.cargasfamiliares_DAO;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.db;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.CARGAS_FAMILIARES;

import java.util.ArrayList;
import java.util.List;

public class CargasFamiliaresRepository {

    private cargasfamiliares_DAO cargasfamiliaresDao;
    private List<CARGAS_FAMILIARES> cargas_familiaresList;

    public CargasFamiliaresRepository(Application application) {
        db bdd = db.getDatabase(application);
        cargasfamiliaresDao = bdd.cargasfamiliaresDao();
    }

    public void insert(final CARGAS_FAMILIARES cargas_familiares) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cargasfamiliaresDao.insert(cargas_familiares);
            }
        }).start();
    }

    public List<CARGAS_FAMILIARES> getByEMP_ID(final int EMP_ID) {
        cargas_familiaresList = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                cargas_familiaresList= cargasfamiliaresDao.getByEMP_ID(EMP_ID);
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cargas_familiaresList;
    }
}
