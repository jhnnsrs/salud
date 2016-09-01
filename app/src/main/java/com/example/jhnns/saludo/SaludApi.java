package com.example.jhnns.saludo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jhnns on 30.08.2016.
 */
public interface SaludApi {

    @GET("users/{id}")
    Call<SaludPatientWrapper> getPatient(@Path("id") String id);

    @GET("users/")
    Call<List<SaludPatientWrapper>> getPatients();

}
