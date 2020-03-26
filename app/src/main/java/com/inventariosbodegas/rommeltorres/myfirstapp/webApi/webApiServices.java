package com.inventariosbodegas.rommeltorres.myfirstapp.webApi;

import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface webApiServices {

    @GET("EnvioEmpleados")
    Call<List<EMPLEADOS>> getEmpleados();

    @POST("EnvioEmpleados")
    Call<Integer> postEmpleados(@Body EMPLEADOS empleados);

    @PUT("EnvioEmpleados")
    Call<Integer> putEmpleados(@Body EMPLEADOS empleados, @Query("id") int id);
}
