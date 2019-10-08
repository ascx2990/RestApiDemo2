package com.example.restapidemo2.connect;

import com.example.restapidemo2.help.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class RepoRepository {
    // 以Singleton模式建立
    private static RepoRepository mInstance = new RepoRepository();
    private AlbumService mAlbumService;

    public RepoRepository() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)   // 設置連線Timeout
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BASIC))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.HTTP.BASE_URL)
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mAlbumService = retrofit.create(AlbumService.class);
    }

    public static RepoRepository getInstance() {
        return mInstance;
    }

    public AlbumService getAPI() {
        return mAlbumService;
    }
}
