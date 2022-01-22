package co.demo.spotifydemo.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.AlbumListItemVariantBinding;
import co.demo.spotifydemo.model.data.Album;

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.ViewHolderAlbum> {
    private String TAG = AlbumRecyclerAdapter.class.getCanonicalName();
    private List<Album> albumList;
    private Context context;
    private OnAlbumListener mOnAlbumListener;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public AlbumRecyclerAdapter(Context context,
                                List<Album> albumList,
                                OnAlbumListener onAlbumListener) {
        this.context = context;
        this.albumList = albumList;
        this.mOnAlbumListener = onAlbumListener;
    }

    @NonNull
    @Override
    public AlbumRecyclerAdapter.ViewHolderAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        AlbumListItemVariantBinding binding =
                AlbumListItemVariantBinding.inflate(layoutInflater,
                        parent, false);
        return new AlbumRecyclerAdapter.ViewHolderAlbum(binding, mOnAlbumListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumRecyclerAdapter.ViewHolderAlbum holder, int position) {
        holder.setDataAlbum(context, viewPool, albumList.get(position));
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class ViewHolderAlbum extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private AlbumListItemVariantBinding binding;
        OnAlbumListener onAlbumListener;

        public ViewHolderAlbum(@NonNull AlbumListItemVariantBinding binding, OnAlbumListener onAlbumListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onAlbumListener = onAlbumListener;
            binding.cvAlbumItem.setOnClickListener(this);
        }


        @SuppressLint("SetTextI18n")
        public void setDataAlbum(Context context,
                                 RecyclerView.RecycledViewPool viewPool,
                                 Album album) {
            binding.tvAlbumName.setText(album.getName());
            Glide.with(context)
                    .load(album.getImages().get(0))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .circleCrop()
                    .into(binding.ivAlbumImage);

            //setter list inside country
            // Create layout manager with initial prefetch item count
            LinearLayoutManager layoutManager = new LinearLayoutManager(binding.rvCountryList.getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            layoutManager.setInitialPrefetchItemCount(album.getAvailableMarkets().size());

            // Create album view adapter
            binding.rvCountryList.setHasFixedSize(true);
            binding.rvCountryList.setLayoutManager(layoutManager);
            CountryRecyclerAdapter countryRecyclerAdapter
                    = new CountryRecyclerAdapter(context, album.getAvailableMarkets());
            binding.rvCountryList.setAdapter(countryRecyclerAdapter);
            binding.rvCountryList.setRecycledViewPool(viewPool);

            binding.rvCountryList.setVisibility(album.getAvailableMarkets().size() < 5
                    ? View.VISIBLE
                    : View.GONE);

        }

        @Override
        public void onClick(View view) {
            onAlbumListener.onAlbumListener(getLayoutPosition());
        }
    }

    public interface OnAlbumListener {
        void onAlbumListener(int position);
    }

}


