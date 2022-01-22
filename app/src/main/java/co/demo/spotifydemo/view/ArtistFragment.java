package co.demo.spotifydemo.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.demo.spotifydemo.viewmodel.ArtistViewModel;
import co.demo.spotifydemo.R;

public class ArtistFragment extends Fragment {

    private ArtistViewModel mViewModel;

    public static ArtistFragment newInstance() {
        return new ArtistFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.artist_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArtistViewModel.class);
        // TODO: Use the ViewModel
    }

}