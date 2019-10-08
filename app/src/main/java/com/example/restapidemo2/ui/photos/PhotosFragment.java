package com.example.restapidemo2.ui.photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.restapidemo2.R;
import com.example.restapidemo2.data.model.Album;
import com.example.restapidemo2.data.model.Photo;
import com.example.restapidemo2.databinding.FragmentAlbumsBinding;
import com.example.restapidemo2.databinding.FragmentPhotosBinding;
import com.example.restapidemo2.ui.albums.AlbumsAdapter;
import com.example.restapidemo2.ui.albums.AlbumsFragment;
import com.example.restapidemo2.ui.albums.AlbumsViewModel;
import com.example.restapidemo2.ui.albums.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class PhotosFragment extends Fragment {

    public static final String TAG = PhotosFragment.class.getName();
    //private ViewModelFactory mViewModelFactory=new ViewModelFactory();
    private FragmentPhotosBinding binding;
    private PhotosViewModel viewModel;
    private PhotoAdapter repoAdapter = new PhotoAdapter(new ArrayList<Photo>());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        //View root = inflater.inflate(R.layout.fragment_albums, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.recyclerView.setAdapter(repoAdapter);
        return binding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PhotosViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.getPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {

                repoAdapter.swapItems(photos);
                //mHandler.sendEmptyMessage(1);
            }
        });
        //viewModel.queryAlbums();
        viewModel.queryPhotoData();
    }
}