package com.example.restapidemo2.ui.albums;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.*;
import com.example.restapidemo2.data.model.Album;
import com.example.restapidemo2.databinding.ItemAlbumBinding;

import java.util.List;
import java.util.Objects;



public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {

    private List<Album> items;

    AlbumsAdapter(List<Album> items) {
        this.items = items;
    }

    class AlbumsViewHolder extends RecyclerView.ViewHolder{


        private final ItemAlbumBinding binding;
        AlbumsViewHolder(ItemAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Album album) {
            binding.setData(album);
            binding.executePendingBindings();
        }
    }

    @Override
    public AlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAlbumBinding binding = ItemAlbumBinding.inflate(layoutInflater, parent, false);
        return new AlbumsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AlbumsViewHolder holder, int position) {
        Album album = items.get(position);
        holder.bind(album);
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

    void swapItems(List<Album> newItems) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new RepoDiffCallback(this.items, newItems));
        this.items.clear();
        this.items.addAll(newItems);
        result.dispatchUpdatesTo(this);
    }

    private class RepoDiffCallback extends DiffUtil.Callback {

        private List<Album> mOldList;
        private List<Album> mNewList;

        RepoDiffCallback(List<Album> oldList, List<Album> newList) {
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
            Album oldRepo = mOldList.get(oldItemPosition);
            Album newRepo = mNewList.get(newItemPosition);
            return Objects.equals(oldRepo, newRepo);
        }
    }
}