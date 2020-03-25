package com.inventariosbodegas.rommeltorres.myfirstapp.webApi;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface webApiServices {

    @GET("EnvioEmpleados")
    Call<List<EMPLEADOS>> getEmpleados();
}
