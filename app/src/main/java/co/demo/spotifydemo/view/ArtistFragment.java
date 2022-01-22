package co.demo.spotifydemo.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import co.demo.spotifydemo.databinding.ArtistFragmentBinding;
import co.demo.spotifydemo.model.adapter.ArtistRecyclerAdapter;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.viewmodel.ArtistViewModel;
import co.demo.spotifydemo.R;

public class ArtistFragment extends Fragment {
    private static final String TAG = ArtistFragment.class.getCanonicalName();
    private ArtistViewModel mViewModel;
    private ArtistFragmentBinding binding;
    private View viewContext;
    private ArtistRecyclerAdapter artistRecyclerAdapter;

    public static ArtistFragment newInstance() {
        return new ArtistFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ArtistFragmentBinding
                .inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewContext = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArtistViewModel.class);
        //todo: cg 20220122 editar
        requireActivity().setTitle("HOLAAAA");
        if (binding != null && viewContext != null) {
            initUI();
            subscribeEvents();
        }
    }

    private void subscribeEvents() {
        mViewModel.isViewLoading().observe(getViewLifecycleOwner(), this::isLoadingObserver);
        mViewModel.getOnMessageError().observe(getViewLifecycleOwner(), this::getOnMessageErrorObserver);
        mViewModel.isEmptyArtistList().observe(getViewLifecycleOwner(), this::isEmptyArtistListObserver);
        mViewModel.getArtistList().observe(getViewLifecycleOwner(), this::getArtistListObserver);

    }

    private void getArtistListObserver(List<Artist> artists) {

    }

    private void isEmptyArtistListObserver(Boolean isEmpty) {

    }

    private void getOnMessageErrorObserver(String errorMessage) {

    }

    private void isLoadingObserver(Boolean showLoading) {

    }

    private void initUI() {



        //TODO: CG 20220122 DATA DUMMY
        mViewModel.getAllArtistList();

        binding.rvArtistList.setHasFixedSize(true);
        binding.rvArtistList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        artistRecyclerAdapter = new ArtistRecyclerAdapter(getContext(),  mViewModel.artistList, this::onArtistListener, this::onAlbumListener);
                binding.rvArtistList.setAdapter(artistRecyclerAdapter);
    }

    private void onAlbumListener(int position) {
        Toast.makeText(requireActivity(), "Album: " + position, Toast.LENGTH_SHORT).show();
    }

    private void onArtistListener(int position) {
        Toast.makeText(requireActivity(), "artist: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}