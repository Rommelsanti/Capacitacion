package com.inventariosbodegas.rommeltorres.myfirstapp.ui.home;

import android.app.Application;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.CARGAS_FAMILIARES;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.repository.CargasFamiliaresRepository;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.repository.EmpleadosRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends AndroidViewModel {

    private EmpleadosRepository empleadosRepository;
    private CargasFamiliaresRepository cargasFamiliaresRepository;
    private MutableLiveData<String> mText;
    private LiveData<List<EMPLEADOS>> empleadosListLiveData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        empleadosRepository = new EmpleadosRepository(application);
        cargasFamiliaresRepository = new CargasFamiliaresRepository(application);
        empleadosListLiveData = empleadosRepository.getEmpleadosListLiveData();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<List<EMPLEADOS>> getEmpleadosListLiveData() {
        return empleadosListLiveData;
    }

    public Long insertEmpleado(EMPLEADOS empleados){
        return empleadosRepository.insertId(empleados);
    }

    public void insertCargasFalimiares(CARGAS_FAMILIARES cargas_familiares){
        cargasFamiliaresRepository.insert(cargas_familiares);
    }

    public void changeStatus(int EMP_ID){
        empleadosRepository.changeStatus(EMP_ID);
    }


    public LiveData<String> getText() {
        return mText;
    }
}