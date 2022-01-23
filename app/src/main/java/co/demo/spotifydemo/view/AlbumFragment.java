package co.demo.spotifydemo.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Objects;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.AlbumFragmentBinding;
import co.demo.spotifydemo.model.adapter.ArtistRecyclerAdapter;
import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.viewmodel.AlbumViewModel;
import co.demo.spotifydemo.viewmodel.ArtistViewModel;

public class AlbumFragment extends Fragment {
    private static final String TAG = AlbumFragment.class.getCanonicalName();
    private AlbumFragmentBinding binding;
    private View viewContext;
    private AlbumViewModel mViewModel;
    private Album albumRecieved;

    public static AlbumFragment newInstance() {
        return new AlbumFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AlbumFragmentBinding
                .inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
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
                albumRecieved = AlbumFragmentArgs.fromBundle(getArguments()).getAlbumSelected();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        //todo: cg 20220122 editar
        requireActivity().setTitle("HOLAAAA");
        if (binding != null && viewContext != null) {
            initUI();
        }
    }

    private void initUI() {
        Glide.with(requireActivity())
                .load(albumRecieved.getImages().get(0).getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivAlbumImage);
        binding.btnGoToSpotify.setOnClickListener(v -> mViewModel.goToSpotify(requireActivity(), albumRecieved));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}