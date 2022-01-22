package co.demo.spotifydemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.model.repository.ArtistRepository;
import io.reactivex.disposables.CompositeDisposable;

public class ArtistViewModel extends ViewModel {
    private static final String TAG = ArtistViewModel.class.getCanonicalName();
    private List<Artist> artistList = new ArrayList<>();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final ArtistRepository artistRepository = new ArtistRepository();
    
    //loading
    private MutableLiveData<Boolean> isViewLoading = new MutableLiveData<>();

    public LiveData<Boolean> isViewLoading() {
        if (isViewLoading == null) {
            isViewLoading = new MutableLiveData<>();
        }
        return isViewLoading;
    }

    //data list<Artist>
    private MutableLiveData<List<Artist>> ArtistListResult = new MutableLiveData<>();

    public LiveData<List<Artist>> getArtistList() {
        if (ArtistListResult == null) {
            ArtistListResult = new MutableLiveData<>();
        }
        return ArtistListResult;
    }

    //empty
    private MutableLiveData<Boolean> isEmptyArtistList = new MutableLiveData<>();

    public LiveData<Boolean> isEmptyArtistList() {
        if (isEmptyArtistList == null) {
            isEmptyArtistList = new MutableLiveData<>();
        }
        return isEmptyArtistList;
    }

    //Error
    private MutableLiveData<String> onMessageError = new MutableLiveData<>();

    public LiveData<String> getOnMessageError() {
        if (onMessageError == null) {
            onMessageError = new MutableLiveData<>();
        }
        return onMessageError;
    }



    @Override
    protected void onCleared() {
        disposables.clear();
    }
}