package com.example.restapidemo2.ui.photos;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restapidemo2.data.model.Album;
import com.example.restapidemo2.data.model.Photo;
import com.example.restapidemo2.databinding.ItemAlbumBinding;
import com.example.restapidemo2.databinding.ItemPhotoBinding;

import java.util.List;
import java.util.Objects;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotosViewHolder> {

    private List<Photo> items;

    PhotoAdapter(List<Photo> items) {
        this.items = items;
    }

    class PhotosViewHolder extends RecyclerView.ViewHolder{


        private final ItemPhotoBinding binding;
        PhotosViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Photo photo) {
            binding.setData(photo);
            binding.executePendingBindings();
        }
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPhotoBinding binding = ItemPhotoBinding.inflate(layoutInflater, parent, false);
        return new PhotosViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PhotosViewHolder holder, int position) {
        Photo photo = items.get(position);
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void clearItems() {
        int size = this.items.size();
        this.items.clear();
        notifyItemRangeRemoved(0, size);

    }

    void swapItems(List<Photo> newItems) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new RepoDiffCallback(this.items, newItems));
        this.items.clear();
        this.items.addAll(newItems);
        result.dispatchUpdatesTo(this);
    }

    private class RepoDiffCallback extends DiffUtil.Callback {

        private List<Photo> mOldList;
        private List<Photo> mNewList;

        RepoDiffCallback(List<Photo> oldList, List<Photo> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList != null ? mOldList.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return mNewList != null ? mNewList.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            int oldId = mOldList.get(oldItemPosition).getId();
            int newId = mNewList.get(newItemPosition).getId();
            return Objects.equals(oldId, newId);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Photo oldRepo = mOldList.get(oldItemPosition);
            Photo newRepo = mNewList.get(newItemPosition);
            return Objects.equals(oldRepo, newRepo);
        }
    }
}