package co.demo.spotifydemo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import co.demo.spotifydemo.databinding.AlbumFragmentBinding;
import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.viewmodel.AlbumViewModel;

public class AlbumFragment extends Fragment {
    private AlbumFragmentBinding binding;
    private View viewContext;
    private AlbumViewModel mViewModel;
    private Album albumReceived;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AlbumFragmentBinding
                .inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewContext = view;
        if (getArguments() != null) {
            AlbumFragmentArgs.fromBundle(getArguments()).getAlbumSelected();
            if (Objects.requireNonNull(AlbumFragmentArgs.fromBundle(getArguments()).getAlbumSelected()).getImages() != null
                    && !Objects.requireNonNull(AlbumFragmentArgs.fromBundle(getArguments()).getAlbumSelected()).getImages().isEmpty()
                    && !Objects.requireNonNull(AlbumFragmentArgs.fromBundle(getArguments()).getAlbumSelected()).getImages().get(0).getUrl().isEmpty()
                    && Objects.requireNonNull(AlbumFragmentArgs.fromBundle(getArguments()).getAlbumSelected()).getUrl() != null) {
                albumReceived = AlbumFragmentArgs.fromBundle(getArguments()).getAlbumSelected();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        if (binding != null && viewContext != null) {
            initUI();
        }
    }

    private void initUI() {
        mViewModel.setTitleFragment(requireActivity(), albumReceived.getName());
        mViewModel.setAlbumImage(binding, albumReceived.getImages());
        binding.btnGoToSpotify.setOnClickListener(v -> mViewModel.goToSpotify(requireActivity(), albumReceived));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}