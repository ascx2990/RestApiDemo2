package com.example.restapidemo2.data;

import android.util.Log;

import com.example.restapidemo2.connect.RepoRepository;
import com.example.restapidemo2.data.model.Photo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import com.example.restapidemo2.connect.AlbumService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class PhotoDownLoadTest {
    AlbumService mAlbumService;
    @Before
    public void setUp() throws Exception {
         mAlbumService = RepoRepository.getInstance().getAPI();
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void getPhoto(){
        //System.err.println("getPhoto");

    }
}