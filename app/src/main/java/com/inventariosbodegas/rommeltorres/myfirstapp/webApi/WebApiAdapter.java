package com.inventariosbodegas.rommeltorres.myfirstapp.webApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebApiAdapter {
    private static webApiServices API_SERVICE;

    public static <T> T getWebApiService(Class<T> serviceClass){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-NN-dd'T'HH:mm:ss").create();

        String url = "http://192.168.100.223/WebApiCapacitacion/API/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(serviceClass);

    }

}
