package co.demo.spotifydemo.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.spotify.sdk.android.auth.AuthorizationClient;

import java.util.ArrayList;
import java.util.List;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.model.intermediary.Image;
import co.demo.spotifydemo.model.intermediary.ItemAlbum;
import co.demo.spotifydemo.model.intermediary.ItemArtist;
import co.demo.spotifydemo.model.repository.AlbumRepository;
import co.demo.spotifydemo.model.repository.ArtistRepository;
import co.demo.spotifydemo.util.SingleLiveEvent;
import co.demo.spotifydemo.util.UtilPreference;
import co.demo.spotifydemo.view.ArtistFragment;
import co.demo.spotifydemo.view.ArtistFragmentDirections;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ArtistViewModel extends ViewModel {
    private static final String TAG = ArtistViewModel.class.getCanonicalName();
    public List<Artist> artistList = new ArrayList<>();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final ArtistRepository artistRepository = new ArtistRepository();
    private final AlbumRepository albumRepository = new AlbumRepository();

    //loading
    private SingleLiveEvent<Boolean> isViewLoading = new SingleLiveEvent<>();
    private Artist artist;
    private NavController navController;

    public LiveData<Boolean> isViewLoading() {
        if (isViewLoading == null) {
            isViewLoading = new SingleLiveEvent<>();
        }
        return isViewLoading;
    }

    //data list<Artist>
    private SingleLiveEvent<Artist> artistResult = new SingleLiveEvent<>();

    public LiveData<Artist> getArtist() {
        if (artistResult == null) {
            artistResult = new SingleLiveEvent<>();
        }
        return artistResult;
    }

    //empty
    private SingleLiveEvent<Boolean> isEmptyArtistList = new SingleLiveEvent<>();

    public LiveData<Boolean> isEmptyArtistList() {
        if (isEmptyArtistList == null) {
            isEmptyArtistList = new SingleLiveEvent<>();
        }
        return isEmptyArtistList;
    }

    //Error
    private SingleLiveEvent<String> onMessageError = new SingleLiveEvent<>();

    public LiveData<String> getOnMessageError() {
        if (onMessageError == null) {
            onMessageError = new SingleLiveEvent<>();
        }
        return onMessageError;
    }

    public void getAllArtistList() {
        List<Image> images = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        List<String> availableMarkets = new ArrayList<>();

        availableMarkets.add("CO");
        availableMarkets.add("CA");
        availableMarkets.add("IT");


        images.add(new Image(200, "https://dummyimage.com/600x400/e356e3/0011ff", 200));
        Album album = new Album("album 1", images, availableMarkets, "https://dummyimage.com/600x400/e356e3/0011ff");
        albums.add(album);

        Artist artist = new Artist("Juanes", 345667777, 99, images, albums);
        artistList.add(artist);
    }

    public void searchArtists(Context context, String query) {
        isViewLoading.postValue(true);
        disposables.add(
                artistRepository.searchArtists(context, query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                result -> {
                                    artist = null;
                                    isViewLoading.postValue(false);
                                    if (result.code() == 200
                                            && result.body() != null) {
                                        if (result.isSuccessful()) {
                                            if (result.body().getArtists() != null
                                                    && result.body().getArtists().getItems() != null
                                                    && result.body().getArtists().getItems().size() > 0
                                                    && result.body().getArtists().getItems().get(0).getId() != null) {
                                                ItemArtist itemArtist = result.body().getArtists().getItems().get(0);
                                                artist = new Artist(itemArtist.getName(),
                                                        itemArtist.getFollowers().getTotal(),
                                                        itemArtist.getPopularity(),
                                                        itemArtist.getImages(),
                                                        null);
                                                getAlbumsByArtistID(context, result.body().getArtists().getItems().get(0).getId());
                                            } else {
                                                isEmptyArtistList.postValue(true);
                                            }
                                        } else {
                                            Log.d(TAG, "server error");
                                            throw new HttpException(result);
                                        }
                                    } else {
                                        throw new HttpException(result);
                                    }
                                },
                                throwable -> {
                                    isViewLoading.postValue(false);
                                    if (throwable != null) {
                                        if (throwable instanceof HttpException) {
                                            if (((HttpException) throwable).code() == 401) {
                                                //TODO: CG 20220122 CERRARSESION
                                                onMessageError.postValue("401");
                                            } else {
                                                onMessageError.postValue("ERROR API");
                                            }
                                            onMessageError.postValue("ERROR API");
                                        } else {
                                            onMessageError.postValue(throwable.getMessage());
                                        }
                                    }
                                }
                        )
        );
    }

    public void getAlbumsByArtistID(Context context, String artistId) {
        isViewLoading.postValue(true);
        disposables.add(
                albumRepository.getAlbumsByArtistID(context, artistId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                result -> {
                                    isViewLoading.postValue(false);
                                    if (result.code() == 200
                                            && result.body() != null) {
                                        if (result.isSuccessful()) {
                                            if (result.body().getItems() != null
                                                    && result.body().getItems().size() > 0) {
                                                List<Album> albums = new ArrayList<>();
                                                for (ItemAlbum itemAlbum : result.body().getItems()) {
                                                    Album album = new Album(itemAlbum.getName(), itemAlbum.getImages(),
                                                            itemAlbum.getAvailableMarkets(),
                                                            itemAlbum.getExternalUrls().getSpotify());
                                                    albums.add(album);
                                                }
                                                if(artist != null) {
                                                    artist.setAlbums(albums);
                                                    artistResult.postValue(artist);
                                                } else {
                                                    Log.d(TAG, "server error");
                                                    throw new HttpException(result);
                                                }
                                            } else {
                                                isEmptyArtistList.postValue(true);
                                            }
                                        } else {
                                            Log.d(TAG, "server error");
                                            throw new HttpException(result);
                                        }
                                    } else {
                                        throw new HttpException(result);
                                    }
                                },
                                throwable -> {
                                    isViewLoading.postValue(false);
                                    if (throwable != null) {
                                        if (throwable instanceof HttpException) {
                                            if (((HttpException) throwable).code() == 401) {
                                                //TODO: CG 20220122 CERRARSESION
                                                onMessageError.postValue("401");
                                            } else {
                                                onMessageError.postValue("ERROR API");
                                            }
                                        } else {
                                            onMessageError.postValue(throwable.getMessage());
                                        }
                                    }
                                }
                        )
        );
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    public void goToSpotify(View viewContext, int position) {
        Album albumSelected = artistList.get(0).getAlbums().get(position);
        //ir a pantalla principal
        if (navController == null) {
            navController = Navigation.findNavController(viewContext);
        }
        ArtistFragmentDirections.ActionArtistFragmentToAlbumFragment
                action = ArtistFragmentDirections.actionArtistFragmentToAlbumFragment(albumSelected);
        action.setAlbumSelected(albumSelected);
        navController.navigate(action);
    }

    public void logout(Context context, View viewContext) {
        UtilPreference.clearPreferences(context);
        AuthorizationClient.clearCookies(context);
        goToLogin(viewContext);
    }

    private void goToLogin(View viewContext) {
        if (navController == null) {
            navController = Navigation.findNavController(viewContext);
        }
        navController.navigate(R.id.action_artistFragment_to_loginFragment);
    }
}