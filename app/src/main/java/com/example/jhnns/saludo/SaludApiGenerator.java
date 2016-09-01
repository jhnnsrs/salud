package com.example.jhnns.saludo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jhnns on 30.08.2016.
 */
public class SaludApiGenerator {

    public static final String API_BASE_URL = "http://192.168.0.18:9000/app/patientaccess/1/app/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {

        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
