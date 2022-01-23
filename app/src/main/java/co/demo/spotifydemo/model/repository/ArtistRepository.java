package co.demo.spotifydemo.model.repository;

import android.content.Context;

import co.demo.spotifydemo.io.SpotifyAdapter;
import co.demo.spotifydemo.model.response.QueryResponse;
import io.reactivex.Observable;
import retrofit2.Response;

public class ArtistRepository {
    private String TAG = ArtistRepository.class.getCanonicalName();

    //API
    public Observable<Response<QueryResponse>> searchArtists(Context context, String query) {
        return SpotifyAdapter.getApiService(context).searchArtists(query);
    }
}
