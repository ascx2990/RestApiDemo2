package com.example.restapidemo2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.restapidemo2.connect.AlbumService;
import com.example.restapidemo2.connect.RepoRepository;
import com.example.restapidemo2.data.db.DBAccess;
import com.example.restapidemo2.data.model.Photo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    /* 資料庫 */
    public static DBAccess access;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDB();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_albums, R.id.navigation_photos, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);




    }
    private void openDB() {
        if (access == null)
            access = new DBAccess(this);

    }

    private void closeDB() {
        if (access != null)
            access.close();

    }

    @Override
    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }
}
