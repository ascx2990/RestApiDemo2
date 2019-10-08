package com.example.restapidemo2.ui.albums;

import android.database.Cursor;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restapidemo2.MainActivity;
import com.example.restapidemo2.data.DataModel;
import com.example.restapidemo2.data.model.Album;

import java.util.ArrayList;
import java.util.List;


public class AlbumsViewModel extends ViewModel {

    public final ObservableBoolean isLoading = new ObservableBoolean(false);

    //private final MutableLiveData<List<Album>> repos = new MutableLiveData<>();
    private final MutableLiveData<List<Album>> repos = new MutableLiveData<>();
    private DataModel dataModel;

    public AlbumsViewModel(DataModel dataModel) {
        super();
        this.dataModel = dataModel;
    }

    LiveData<List<Album>> getAlbums() {
        return repos;
    }

    void downLoadData() {
        isLoading.set(true);
        dataModel.getDownLoadData(new DataModel.onDataCallback() {
            @Override
            public void getData(List<?> data) {
                Cursor curosr = MainActivity.access.ReCmd("select album ._id,album.userId,album.title,p1.thumbnailUrl from album left join (SELECT * FROM photo group by albumId) as p1 on p1.albumId=album._id");
                List<Album> items=new ArrayList<>();
                while (curosr.moveToLast()){
                    Album a=new Album();
                    a.setId(curosr.getInt(0));
                    a.setUserId(curosr.getInt(1));
                    a.setTitle(curosr.getString(2));
                    a.setThumbnailUrl(curosr.getString(3));
                    items.add(a);
                }
                curosr.close();
                repos.setValue(items);
                isLoading.set(false);
            }

            @Override
            public void unSuccessful(int resCode) {
                isLoading.set(false);
            }

            @Override
            public void onFailure(String message) {
                isLoading.set(false);
            }
        });
    }

    void queryAlbums() {
        isLoading.set(true);
        dataModel.getAlbum(new DataModel.onDataCallback() {
            @Override
            public void getData(List<?> data) {
//                for(Album element : data) {
//                    Timber.v("Title:"+element.getTitle());
//                }


                repos.setValue((List<Album>) data);
                isLoading.set(false);
            }

            @Override
            public void unSuccessful(int resCode) {
                isLoading.set(false);
            }

            @Override
            public void onFailure(String message) {
                isLoading.set(false);
            }
        });
    }
//    void queryAlbums() {
//        isLoading.set
//        isLoading.set(true);
//        dataModel.getAlbum(new DataModel.onDataCallback() {
//            @Override
//            public void getData(List<Album> data) {
//                for (int i = 0; i < data.size(); i++) {
//                    Timber.v(data.get(i).getTitle());
//                }
//
//            }
//
//            @Override
//            public void unSuccessful(int resCode) {
//                Timber.e("resCode:"+resCode);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                Timber.e("message:"+message);
//            }
//        });
//
//    }
}