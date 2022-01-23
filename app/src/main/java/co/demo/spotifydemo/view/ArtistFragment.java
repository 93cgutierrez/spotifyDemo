package co.demo.spotifydemo.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;
import java.util.Objects;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.ArtistFragmentBinding;
import co.demo.spotifydemo.databinding.ArtistListItemBinding;
import co.demo.spotifydemo.model.adapter.ArtistRecyclerAdapter;
import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.util.Parameters;
import co.demo.spotifydemo.viewmodel.ArtistViewModel;

public class ArtistFragment extends Fragment {
    private static final String TAG = ArtistFragment.class.getCanonicalName();
    private ArtistViewModel mViewModel;
    private ArtistFragmentBinding binding;
    private View viewContext;
    private ArtistRecyclerAdapter artistRecyclerAdapter;
    private String mQueryString;
    private final Handler mHandler = new Handler();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ArtistFragmentBinding
                .inflate(inflater, container, false);
        return binding.getRoot();
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
        if (binding != null && viewContext != null) {
            initUI();
            subscribeEvents();
        }
    }

    private void subscribeEvents() {
        mViewModel.isViewLoading().observe(getViewLifecycleOwner(), this::isLoadingObserver);
        mViewModel.getOnMessageError().observe(getViewLifecycleOwner(), this::getOnMessageErrorObserver);
        mViewModel.isEmptyArtistList().observe(getViewLifecycleOwner(), this::isEmptyArtistListObserver);
        mViewModel.getArtist().observe(getViewLifecycleOwner(), this::getArtistObserver);
        mViewModel.isEmptyAlbums().observe(getViewLifecycleOwner(), this::isEmptyAlbumsObserver);
        mViewModel.getAlbums().observe(getViewLifecycleOwner(), this::getAlbumsObserver);
    }

    private void getAlbumsObserver(List<Album> albums) {

    }

    private void isEmptyAlbumsObserver(Boolean isEmpty) {

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getArtistObserver(Artist artist) {
        if (artist != null) {
            mViewModel.artistList.clear();
            mViewModel.artistList.add(artist);
            artistRecyclerAdapter.notifyDataSetChanged();
        }
    }


    private void isEmptyArtistListObserver(Boolean isEmpty) {
        mViewModel.showEmptyMessage(binding, isEmpty);
    }

    private void getOnMessageErrorObserver(String errorMessage) {
        mViewModel.showErrorMessage(binding, true, errorMessage);
        if (Objects.equals(errorMessage, Parameters.INVALID_TOKEN)) {
            Toast.makeText(getContext(), R.string.msg_token_invalid, Toast.LENGTH_SHORT).show();
            mViewModel.logout(requireActivity(), viewContext);
        }
    }

    private void isLoadingObserver(Boolean showLoading) {
        mViewModel.showLoading(binding, showLoading);
    }

    private void initUI() {
        binding.rvArtistList.setHasFixedSize(true);
        binding.rvArtistList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        artistRecyclerAdapter = new ArtistRecyclerAdapter(getContext(), mViewModel.artistList, this::onArtistListener, this::onAlbumListener,
                this::onAlbumExpandListener);
        binding.rvArtistList.setAdapter(artistRecyclerAdapter);
        //listener for scroll
        mViewModel.managerScrollLazy(requireActivity(),
                artistRecyclerAdapter.getArtistListItemBinding());
        if (mViewModel.artistList.size() == 0) {
            mViewModel.showInitialMessage(binding, true);
        }
    }

    private void onAlbumExpandListener(ArtistListItemBinding binding, View view, int position) {
        Log.d(TAG, "onAlbumExpandListener: " + position);
        mViewModel.showHideAlbums(requireActivity(), binding);
    }

    private void onAlbumListener(int position) {
        mViewModel.goToSpotify(viewContext, position);
    }

    private void onArtistListener(int position) {
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.artistic_fragment_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem mSearchMenuItem = menu.findItem(R.id.action_toolbar_search);
        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.searchViewOnQueryTextChangeAndSubmit(requireActivity(), query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.searchViewOnQueryTextChangeAndSubmit(requireActivity(), newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected: " + itemId);
        if (itemId == R.id.action_toolbar_logout) {
            mViewModel.logout(getContext(), viewContext);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}