package com.example.restapidemo2.ui.photos;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restapidemo2.MainActivity;
import com.example.restapidemo2.data.DataModel;
import com.example.restapidemo2.data.model.Album;
import com.example.restapidemo2.data.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotosViewModel extends ViewModel {


    private final MutableLiveData<List<Photo>> repos = new MutableLiveData<>();
    private DataModel dataModel;
    public PhotosViewModel() {
        //super();

    }
    LiveData<List<Photo>> getPhotos() {
        return repos;
    }
    void queryPhotoData() {
        Cursor curosr = MainActivity.access.ReCmd("SELECT photo._id,photo.albumId,photo.title,photo.url,photo.thumbnailUrl FROM photo");
        List<Photo> items = new ArrayList<>();
        while (curosr.moveToNext()) {
            Photo a = new Photo();
            a.setId(curosr.getInt(0));
            a.setAlbumId(curosr.getInt(1));
            a.setTitle(curosr.getString(2));
            a.setUrl(curosr.getString(3));
            a.setThumbnailUrl(curosr.getString(4));
            items.add(a);
        }
        curosr.close();
        repos.setValue(items);
    }
}