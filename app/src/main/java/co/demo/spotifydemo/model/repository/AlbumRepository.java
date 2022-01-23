package co.demo.spotifydemo.model.repository;

import android.content.Context;

import co.demo.spotifydemo.io.SpotifyAdapter;
import co.demo.spotifydemo.model.response.AlbumResponse;
import io.reactivex.Observable;
import retrofit2.Response;

public class AlbumRepository {
    private final String TAG = AlbumRepository.class.getCanonicalName();

    //API
    public Observable<Response<AlbumResponse>> getAlbumsByArtistID(Context context, String artistId) {
        return SpotifyAdapter.getApiService(context).getAlbumsByArtistID(artistId);
    }
}
