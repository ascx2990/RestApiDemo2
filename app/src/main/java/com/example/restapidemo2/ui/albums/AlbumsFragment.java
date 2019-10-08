package com.example.restapidemo2.ui.albums;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.restapidemo2.MainActivity;
import com.example.restapidemo2.R;
import com.example.restapidemo2.data.model.Album;
import com.example.restapidemo2.databinding.FragmentAlbumsBinding;

import java.util.ArrayList;
import java.util.List;

public class AlbumsFragment extends Fragment {
    public static final String TAG = AlbumsFragment.class.getName();
    private ViewModelFactory mViewModelFactory=new ViewModelFactory();
    private FragmentAlbumsBinding binding;
    private AlbumsViewModel viewModel;
    private AlbumsAdapter repoAdapter = new AlbumsAdapter(new ArrayList<Album>());


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false);
        //View root = inflater.inflate(R.layout.fragment_albums, container, false);
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                viewModel.downLoadData();

            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.recyclerView.setAdapter(repoAdapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this,mViewModelFactory).get(AlbumsViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {

                repoAdapter.swapItems(albums);
                mHandler.sendEmptyMessage(1);
            }
        });
        //viewModel.queryAlbums();
        viewModel.downLoadData();
    }

    @Override
    public void onDestroyView() {
        viewModel.cancelDownLoadData();
        super.onDestroyView();
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    // 等操作完成之后关闭等待圈效果
                    binding.swipe.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
}