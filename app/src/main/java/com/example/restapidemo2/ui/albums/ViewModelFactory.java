package com.example.restapidemo2.ui.albums;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.restapidemo2.data.DataModel;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private DataModel dataModel;

    public ViewModelFactory() {
        this.dataModel = new DataModel();
    }
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AlbumsViewModel.class)) {
            return (T) new AlbumsViewModel(dataModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
