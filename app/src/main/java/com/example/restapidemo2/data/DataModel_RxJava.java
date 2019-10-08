package com.example.restapidemo2.data;

import com.example.restapidemo2.connect.RepoRepository;
import com.example.restapidemo2.data.model.Album;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * @author Will
 * @version 1.0
 * @data today
 */
public class DataModel_RxJava {
    //private AlbumService mAlbumService = RepoRepository.getInstance().getAPI();


    private static CompositeDisposable disposables = null;

    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void getAlbum(Observer<Response<List<Album>>> observer) {
        ApiSubscribe(RepoRepository.getInstance().getAPI().getAlbumsRx(), observer);
    }

    //暫不使用
//    public static void ApiSubscribe1(Observable observable, Observer observer) {
//        disposables= new CompositeDisposable();
//
//        disposables.add(RepoRepository.getInstance().getAPI().getAlbums()
//                .subscribeOn(Schedulers.io())
//                .flatMap( new Function<Response<List<Album>>,ObservableSource<Album>>(){
//            @Override
//            public ObservableSource<Album> apply(Response<List<Album>> listResponse) throws Exception {
//                List<Album> albums =  listResponse.body();
//                return Observable.fromIterable(albums);
//            }
//        }).observeOn(AndroidSchedulers.mainThread()));
//        new Function<Response<List<Album>>,ObservableSource<Album>>(){
//            @Override
//            public ObservableSource<Album> apply(Response<List<Album>> listResponse) throws Exception {
//                return null;
//            }
//        };
//    }


    //    public void getAlbum( final onDataReadyCallback callback) {
//        mAlbumService.getAlbums()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response<Album>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response<Album> albumResponse) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
    public interface onDataReadyCallback {
        void onDataReady(List<Album> data);
    }
}
