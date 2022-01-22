package co.demo.spotifydemo.model.repository;

import co.demo.spotifydemo.io.SpotifyAdapter;
import co.demo.spotifydemo.model.response.AlbumResponse;
import co.demo.spotifydemo.model.response.QueryResponse;
import io.reactivex.Observable;
import retrofit2.Response;

public class AlbumRepository {
    private String TAG = AlbumRepository.class.getCanonicalName();

    //API
    public Observable<Response<AlbumResponse>> getAlbumsByArtistID(String artistId) {
        return SpotifyAdapter.getApiService().getAlbumsByArtistID(artistId);
    }
}
