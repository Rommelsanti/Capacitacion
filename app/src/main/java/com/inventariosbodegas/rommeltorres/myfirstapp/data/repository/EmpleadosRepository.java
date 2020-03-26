package com.inventariosbodegas.rommeltorres.myfirstapp.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.dao.empleados_DAO;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.db;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;

import java.util.List;

import androidx.lifecycle.LiveData;

public class EmpleadosRepository {
    private empleados_DAO empleadosDao;
    private LiveData<List<EMPLEADOS>> empleadosListLiveData;
    private Long id;
    private EMPLEADOS empleados;

    public EmpleadosRepository(Application application) {
        db bdd = db.getDatabase(application);
        empleadosDao = bdd.empleadosDao();
        empleadosListLiveData = empleadosDao.getAll();
    }

    public LiveData<List<EMPLEADOS>> getEmpleadosListLiveData() {
        return empleadosListLiveData;
    }

    public void insert(EMPLEADOS empleados) {
        new GENERAL_ASYC(empleadosDao, 0).execute(empleados);
    }

    public void update(EMPLEADOS empleados) {
        new GENERAL_ASYC(empleadosDao, 1).execute(empleados);
    }

    public void delete(EMPLEADOS empleados) {
        new GENERAL_ASYC(empleadosDao, 2).execute(empleados);
    }

    public EMPLEADOS getUnSend() {
        empleados = null;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                empleados = empleadosDao.getUnSend();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public void insertAll(final List<EMPLEADOS> empleadosList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                empleadosDao.insertAll(empleadosList);
            }
        }).start();
    }

    public Long insertId(final EMPLEADOS empleados) {
        id = null;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                id = empleadosDao.insertId(empleados);
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    public EMPLEADOS selectByCedula(final String EMP_CEDULA) {
        empleados = null;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                empleados = empleadosDao.selectByCedula(EMP_CEDULA);
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public void updateStatus(final int EMP_ID){
        new Thread(new Runnable() {
            @Override
            public void run() {
                empleadosDao.updateStatus(EMP_ID);
            }
        }).start();
    }

    public void changeStatus(final int EMP_ID){
        new Thread(new Runnable() {
            @Override
            public void run() {
                empleadosDao.changeStatus(EMP_ID);
            }
        }).start();
    }

    private static class GENERAL_ASYC extends AsyncTask<EMPLEADOS, Void, Void> {
        private empleados_DAO empleadosDao;
        private int tipo;

        public GENERAL_ASYC(empleados_DAO empleadosDao, int tipo) {
            this.empleadosDao = empleadosDao;
            this.tipo = tipo;
        }

        @Override
        protected Void doInBackground(EMPLEADOS... empleados) {
            switch (tipo) {
                case 0:
                    empleadosDao.insert(empleados[0]);
                    break;
                case 1:
                    empleadosDao.update(empleados[0]);
                    break;
                case 2:
                    empleadosDao.delete(empleados[0]);
            }

            return null;
        }
    }
}
