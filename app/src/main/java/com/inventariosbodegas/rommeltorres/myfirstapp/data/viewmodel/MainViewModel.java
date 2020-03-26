package com.inventariosbodegas.rommeltorres.myfirstapp.data.viewmodel;

import android.app.Application;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.repository.CargasFamiliaresRepository;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.repository.EmpleadosRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {
    private EmpleadosRepository empleadosRepository;
    private CargasFamiliaresRepository cargasFamiliaresRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        empleadosRepository = new EmpleadosRepository(application);
        cargasFamiliaresRepository = new CargasFamiliaresRepository(application);
    }

    public EMPLEADOS getUnSendEmpleados() {
        EMPLEADOS empleados = empleadosRepository.getUnSend();
        if (empleados != null)
            empleados.CARGAS_FAMILIARES = cargasFamiliaresRepository.getByEMP_ID(empleados.EMP_ID);
        return empleados;
    }

    public void updateStatus(int EMP_ID){
        empleadosRepository.updateStatus(EMP_ID);
    }
}
