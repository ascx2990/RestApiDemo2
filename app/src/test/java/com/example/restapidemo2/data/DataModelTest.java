package com.example.restapidemo2.data;

import com.example.restapidemo2.data.model.Album;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import timber.log.Timber;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class DataModelTest {
    DataModel dataModel;

    @Before
    public void setUp() throws Exception {
        dataModel = new DataModel();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAlbum() {
        dataModel.getAlbum(new DataModel.onDataCallback() {
            @Override
            public void getData(List<Album> data) {
                for (int i = 0; i < data.size(); i++) {
                    Timber.v(data.get(i).getTitle());
                }

            }

            @Override
            public void unSuccessful(int resCode) {
                Timber.e("resCode:" + resCode);
            }

            @Override
            public void onFailure(String message) {
                Timber.e("message:" + message);
            }
        });

    }
}