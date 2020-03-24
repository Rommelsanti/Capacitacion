package com.inventariosbodegas.rommeltorres.myfirstapp.ui.home;

import android.app.Application;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.repository.EmpleadosRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends AndroidViewModel {

    private EmpleadosRepository empleadosRepository;
    private MutableLiveData<String> mText;
    private LiveData<List<EMPLEADOS>> empleadosListLiveData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        empleadosRepository = new EmpleadosRepository(application);
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

    public LiveData<String> getText() {
        return mText;
    }
}