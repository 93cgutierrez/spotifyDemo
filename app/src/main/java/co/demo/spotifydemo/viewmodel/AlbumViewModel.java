package co.demo.spotifydemo.viewmodel;

import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import co.demo.spotifydemo.model.data.Album;
import io.reactivex.disposables.CompositeDisposable;

public class AlbumViewModel extends ViewModel {
    private static final String TAG = AlbumViewModel.class.getCanonicalName();
    private final CompositeDisposable disposables = new CompositeDisposable();


    public void goToSpotify(FragmentActivity activity, Album albumRecieved) {
        //open browser to album's spotify page
        Uri uri = Uri.parse(albumRecieved.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}