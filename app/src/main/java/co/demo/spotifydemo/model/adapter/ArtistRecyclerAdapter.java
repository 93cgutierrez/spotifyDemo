package co.demo.spotifydemo.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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
import co.demo.spotifydemo.databinding.ArtistListItemBinding;
import co.demo.spotifydemo.model.data.Artist;

public class ArtistRecyclerAdapter
        extends RecyclerView.Adapter<ArtistRecyclerAdapter.ViewHolderArtist> {
    private static final String TAG = ArtistRecyclerAdapter.class.getCanonicalName();
    private final List<Artist> artistList;
    private final Context context;
    private final OnArtistListener mOnArtistListener;
    private final OnAlbumExpandListener mOnAlbumExpandListener;
    private final AlbumRecyclerAdapter.OnAlbumListener mOnAlbumListener;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private ArtistListItemBinding binding;

    public ArtistRecyclerAdapter(Context context,
                                 List<Artist> artistList,
                                 OnArtistListener onArtistListener,
                                 AlbumRecyclerAdapter.OnAlbumListener onAlbumListener,
                                 OnAlbumExpandListener mOnAlbumExpandListener) {
        this.context = context;
        this.artistList = artistList;
        this.mOnArtistListener = onArtistListener;
        this.mOnAlbumListener = onAlbumListener;
        this.mOnAlbumExpandListener = mOnAlbumExpandListener;
    }

    @NonNull
    @Override
    public ViewHolderArtist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        binding = ArtistListItemBinding.inflate(layoutInflater,
                        parent, false);
        return new ViewHolderArtist(binding, mOnArtistListener, mOnAlbumListener, mOnAlbumExpandListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArtist holder, int position) {
        holder.setDataArtist(context, viewPool, artistList.get(position));
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public static class ViewHolderArtist extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final ArtistListItemBinding binding;
        OnArtistListener onArtistListener;
        AlbumRecyclerAdapter.OnAlbumListener onAlbumListener;
        OnAlbumExpandListener mOnAlbumExpandListener;

        public ViewHolderArtist(@NonNull ArtistListItemBinding binding,
                                OnArtistListener onArtistListener,
                                AlbumRecyclerAdapter.OnAlbumListener onAlbumListener,
                                OnAlbumExpandListener mOnAlbumExpandListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onArtistListener = onArtistListener;
            binding.cvArtistItem.setOnClickListener(this);
            this.onAlbumListener = onAlbumListener;
            binding.llArtistAlbumDropdown.setOnClickListener(this);
            this.mOnAlbumExpandListener = mOnAlbumExpandListener;
        }

        @SuppressLint("SetTextI18n")
        public void setDataArtist(Context context,
                                  RecyclerView.RecycledViewPool viewPool,
                                  Artist artist) {
            binding.tvArtistName.setText(artist.getName());
            binding.tvArtistFollowers.setText(artist.getFollowers().toString());
            binding.csbArtistPopularity.setProgressDisplay(artist.getPopularity());
            Glide.with(context)
                    .load(artist.getImages().get(0).getUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .circleCrop()
                    .into(binding.ivArtistImage);

            //setter list inside albums

            // Create layout manager with initial prefetch item count
            GridLayoutManager layoutManager = new
                    GridLayoutManager(binding.rvAlbumList.getContext(), 2);
            layoutManager.setInitialPrefetchItemCount(artist.getAlbums().size());

            // Create album view adapter
            binding.rvAlbumList.setHasFixedSize(true);
            binding.rvAlbumList.setLayoutManager(layoutManager);
            AlbumRecyclerAdapter albumRecyclerAdapter
                    = new AlbumRecyclerAdapter(context, artist.getAlbums(),
                    position -> onAlbumListener.onAlbumListener(position));
            binding.rvAlbumList.setAdapter(albumRecyclerAdapter);
            binding.rvAlbumList.setRecycledViewPool(viewPool);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + view.getContext());
            if(view.getId() == R.id.cv_artist_item) {
                onArtistListener.onArtistListener(getLayoutPosition());
            } else if(view.getId() == R.id.ll_artist_album_dropdown) {
                mOnAlbumExpandListener.onAlbumExpandListener(binding, view, getLayoutPosition());
            }

        }
    }

    public interface OnArtistListener {
        void onArtistListener(int position);
    }

    public interface OnAlbumExpandListener {
        void onAlbumExpandListener(ArtistListItemBinding binding, View view, int position);
    }

    public ArtistListItemBinding getArtistListItemBinding() {
        return this.binding;
    }

}

