package ir.imandroid.moviesapp;

import android.app.Application;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;


import ir.imandroid.moviesapp.api.Service;
import ir.imandroid.moviesapp.util.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class G extends Application {
    HttpLoggingInterceptor interceptor;
    OkHttpClient client;
    Gson gson =new Gson();
    public static Service service;

    @Override
    public void onCreate() {
        super.onCreate();
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(Constant.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constant.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();

        service = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Service.class);




    }
}
