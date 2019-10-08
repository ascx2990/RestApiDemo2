package com.example.restapidemo2.ui.albums;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class AlbumsBindings {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void bindImage(final ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).thumbnail(0.5f)
                .into(imageView);
//                .into(new SimpleTarget<Drawable>() {
//                          @Override
//                          public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
//                              if(drawable!=null){
//                                  imageView.setImageDrawable(drawable);
//                              }
//                          }
//                      });

    }
}