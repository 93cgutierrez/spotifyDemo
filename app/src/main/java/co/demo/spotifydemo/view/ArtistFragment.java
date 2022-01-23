package co.demo.spotifydemo.view;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import co.demo.spotifydemo.databinding.ArtistFragmentBinding;
import co.demo.spotifydemo.model.adapter.ArtistRecyclerAdapter;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.util.DelayedOnQueryTextListener;
import co.demo.spotifydemo.util.Parameters;
import co.demo.spotifydemo.viewmodel.ArtistViewModel;
import co.demo.spotifydemo.R;

public class ArtistFragment extends Fragment {
    private static final String TAG = ArtistFragment.class.getCanonicalName();
    private ArtistViewModel mViewModel;
    private ArtistFragmentBinding binding;
    private View viewContext;
    private ArtistRecyclerAdapter artistRecyclerAdapter;
    private String mQueryString;
    private Handler mHandler = new Handler();

    public static ArtistFragment newInstance() {
        return new ArtistFragment();
    }

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
        mViewModel.getArtist().observe(getViewLifecycleOwner(), this::getArtistObserver);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getArtistObserver(Artist artist) {
        if(artist != null) {
            mViewModel.artistList.clear();
            mViewModel.artistList.add(artist);
            artistRecyclerAdapter.notifyDataSetChanged();
        }
    }


    private void isEmptyArtistListObserver(Boolean isEmpty) {
        showEmptyMessage(isEmpty);
    }

    private void getOnMessageErrorObserver(String errorMessage) {
        showErrorMessage(true, errorMessage);
        if(Objects.equals(errorMessage, "401")) {
            Toast.makeText(getContext(), "Usuario no autorizado", Toast.LENGTH_SHORT).show();
            mViewModel.logout(requireActivity(), viewContext);
        }
    }

    private void isLoadingObserver(Boolean showLoading) {
        showLoading(showLoading);
    }

    //show loading
    private void showLoading(boolean showLoading) {
            binding.cpiLoading.setVisibility(showLoading ? View.VISIBLE : View.GONE);
            binding.rvArtistList.setVisibility(showLoading ? View.GONE : View.VISIBLE);
            binding.layoutEmptyState.getRoot().setVisibility(showLoading ? View.GONE : View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void showErrorMessage(boolean showError, String errorMessage) {
        binding.layoutEmptyState.getRoot().setVisibility(showError ? View.VISIBLE : View.GONE);
        //modifica texto
        ImageView iv_empty_state_icon  = (ImageView) binding.layoutEmptyState.getRoot()
                .findViewById(R.id.iv_empty_state_icon);
        TextView tv_empty_state_message = (TextView) binding.layoutEmptyState.getRoot()
                .findViewById(R.id.tv_empty_state_message);
        iv_empty_state_icon.setImageResource(R.drawable.ic_baseline_error_24);
        tv_empty_state_message.setText(getString(R.string.msg_error)  + errorMessage);
        binding.cpiLoading.setVisibility(showError ? View.GONE : View.VISIBLE);
        binding.rvArtistList.setVisibility(showError ? View.GONE : View.VISIBLE);
    }

    private void showEmptyMessage(boolean showEmptyMessage) {
        binding.layoutEmptyState.getRoot().setVisibility(showEmptyMessage ? View.VISIBLE : View.GONE);
        //modifica texto
        ImageView iv_empty_state_icon  = (ImageView) binding.layoutEmptyState.getRoot()
                .findViewById(R.id.iv_empty_state_icon);
        TextView tv_empty_state_message = (TextView) binding.layoutEmptyState.getRoot()
                .findViewById(R.id.tv_empty_state_message);
        iv_empty_state_icon.setImageResource(R.drawable.ic_baseline_all_inbox_24);
        tv_empty_state_message.setText(R.string.msg_empty_filter);
        binding.cpiLoading.setVisibility(showEmptyMessage ? View.GONE : View.VISIBLE);
        binding.rvArtistList.setVisibility(showEmptyMessage ? View.GONE : View.VISIBLE);
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
        mViewModel.goToSpotify(viewContext, position);
    }

    private void onArtistListener(int position) {
        Toast.makeText(requireActivity(), "artist: " + position, Toast.LENGTH_SHORT).show();
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
                Log.d(TAG, "onQueryTextSubmit: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange1: " + newText);
                mQueryString = newText;
                mHandler.removeCallbacksAndMessages(null);
                mHandler.postDelayed(() -> {
                    if(!mQueryString.isEmpty()) {
                        Log.d(TAG, "onQueryTextChange2: " + newText);
                        mViewModel.searchArtists(getContext(), mQueryString);
                    } else {
                        isEmptyArtistListObserver(true);
                    }
                }, Parameters.DELAY_ON_QUERY_TEXT_CHANGE);
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