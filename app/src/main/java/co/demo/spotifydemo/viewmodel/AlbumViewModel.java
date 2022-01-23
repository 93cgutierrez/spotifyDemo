package co.demo.spotifydemo.viewmodel;

import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;

import java.util.List;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.AlbumFragmentBinding;
import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.model.intermediary.Image;

public class AlbumViewModel extends ViewModel {
    private static final String TAG = AlbumViewModel.class.getCanonicalName();


    public void goToSpotify(FragmentActivity activity, Album albumRecieved) {
        //open browser to album's spotify page
        Uri uri = Uri.parse(albumRecieved.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void setTitleFragment(FragmentActivity context, String title) {
        context.setTitle(title);
    }

    public void setAlbumImage(AlbumFragmentBinding binding, List<Image> images) {
        Glide.with(binding.getRoot().getContext())
                .load(images.get(0).getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivAlbumImage);
    }
}