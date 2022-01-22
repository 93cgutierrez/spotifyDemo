package co.demo.spotifydemo.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.ArtistListItemBinding;
import co.demo.spotifydemo.model.data.Artist;

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistRecyclerAdapter.ViewHolderArtist> {
    private String TAG = ArtistRecyclerAdapter.class.getCanonicalName();
    private List<Artist> artistList;
    private Context context;
    private OnArtistListener mOnArtistListener;

    public ArtistRecyclerAdapter(Context context,
                                 List<Artist> artistList,
                                 OnArtistListener onArtistListener) {
        this.context = context;
        this.artistList = artistList;
        this.mOnArtistListener = onArtistListener;
    }

    @NonNull
    @Override
    public ViewHolderArtist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ArtistListItemBinding binding =
                ArtistListItemBinding.inflate(layoutInflater,
                        parent, false);
        return new ViewHolderArtist(binding, mOnArtistListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArtist holder, int position) {
        holder.setDataArtist(context, artistList.get(position));
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public static class ViewHolderArtist extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ArtistListItemBinding binding;
        OnArtistListener onArtistListener;

        public ViewHolderArtist(@NonNull ArtistListItemBinding binding,
                                OnArtistListener onArtistListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onArtistListener = onArtistListener;
            binding.cvArtistItem.setOnClickListener(this);
        }


        public void setDataArtist(Context context, Artist artist) {
            binding.tvArtistName.setText(artist.getName());
            binding.tvArtistFollowers.setText(artist.getFollowers());
            binding.csbArtistPopularity.setProgressDisplay(artist.getPopularity());
            Glide.with(context)
                    .load(artist.getImages().get(0))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.ivArtistImage);
        }

        @Override
        public void onClick(View view) {
            onArtistListener.onArtistListener(getLayoutPosition());
        }
    }

    public interface OnArtistListener {
        void onArtistListener(int position);
    }

}

