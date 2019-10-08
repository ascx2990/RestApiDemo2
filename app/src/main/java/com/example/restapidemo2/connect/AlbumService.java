package com.example.restapidemo2.connect;

import com.example.restapidemo2.data.model.Album;
import com.example.restapidemo2.data.model.Photo;
import com.example.restapidemo2.data.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;
/**
 * @author Will
 * @version 1.0
 * @data today
 */
//@Path與@Query 的差異
// https://stackoverflow.com/questions/37698501/retrofit-2-path-vs-query
//Single 是 Observable 的另外一個版本。不像 Observable 可以發出多個元素，它要么只能發出一個元素，要么產生一個 error 事件
//https://beeth0ven.github.io/RxSwift-Chinese-Documentation/content/rxswift_core/observable/single.html
public interface AlbumService {
    @GET("albums")
    Call<List<Album>> getAlbums();
    @GET("photos")
    Call<List<Photo>> getPhotos();
    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("albumId") String id);
    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    //如果要使用RxJava可改回來
    @GET("albums")
    Observable<Response<List<Album>>> getAlbumsRx();

    @POST("photos")
    Observable<Response<Album>> getPhotosRx(@Body Album albums);

    @GET("users/{id}")
    Observable<Response<User>> getUserRx(@Path("id") String id);
}
