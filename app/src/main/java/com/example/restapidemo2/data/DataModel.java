package com.example.restapidemo2.data;

import com.example.restapidemo2.MainActivity;
import com.example.restapidemo2.connect.AlbumService;
import com.example.restapidemo2.connect.RepoRepository;
import com.example.restapidemo2.data.model.Album;
import com.example.restapidemo2.data.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class DataModel {

    private AlbumService mAlbumService = RepoRepository.getInstance().getAPI();

    public void getPhoto(Album album, final onDataCallback callback) {
//        mAlbumService.getPhotos(album).enqueue(new Callback<Photo>() {
//            @Override
//            public void onResponse(Call<Photo> call, Response<Photo> response) {
//                if (response.isSuccessful()) {
//                    callback.getData(response.body());
//                } else {
//                    callback.unSuccessful(response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Photo> call, Throwable t) {
//
//            }
//        });
    }

    public void getAlbum(final onDataCallback callback) {

        mAlbumService.getAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()) {
                    List<Album> items = response.body();

                    for (Album album : items) {
                        MainActivity.access.add_Album(album.getId(), album.getUserId(), album.getTitle());
                    }
                    callback.getData(response.body());
                } else {
                    callback.unSuccessful(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
//
    }

    public void getDownLoadData(final onDataCallback callback) {
        mAlbumService.getAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()) {
                    List<Album> items = response.body();

//                    for (Album album : items) {
//                        MainActivity.access.add_Album(album.getId(), album.getUserId(), album.getTitle());
//                    }
                    mAlbumService.getPhotos().enqueue(new Callback<List<Photo>>() {
                        @Override
                        public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                            if (response.isSuccessful()) {
                                List<Photo> items = response.body();
//                                for (Photo photo : items) {
//                                    MainActivity.access.add_Photo(photo.getId(), photo.getAlbumId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl());
//                                }
                                callback.getData(null);
                            } else {
                                callback.unSuccessful(response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Photo>> call, Throwable t) {
                            callback.onFailure(t.getMessage());
                        }
                    });


                } else {
                    callback.unSuccessful(response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface onDataCallback {
        void getData(List<? extends Object> data);

        void unSuccessful(int resCode);

        void onFailure(String message);
    }
}
