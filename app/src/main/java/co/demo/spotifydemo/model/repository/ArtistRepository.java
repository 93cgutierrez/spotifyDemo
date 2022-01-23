package co.demo.spotifydemo.model.repository;

import android.content.Context;

import java.util.List;

import co.demo.spotifydemo.io.SpotifyAdapter;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.model.response.QueryResponse;
import co.demo.spotifydemo.util.SpotifyDemoDatabaseUtil;
import io.reactivex.Observable;
import retrofit2.Response;

public class ArtistRepository {
    private final String TAG = ArtistRepository.class.getCanonicalName();

    //API
    public Observable<Response<QueryResponse>> searchArtists(Context context, String query) {
        return SpotifyAdapter.getApiService(context).searchArtists(query);
    }

    //DAO
    public List<Artist> getArtists(Context context) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .artistDao().getArtists();
    }

    public Artist getArtistById(Context context, String artistId) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .artistDao().getArtistById(artistId);
    }

    public long createdArtist(Context context, Artist artist) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .artistDao().createdArtist(artist);
    }

    public List<Long> createdArtist(Context context, List<Artist> artists) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .artistDao().createdArtist(artists);
    }

    public int updateArtist(Context context, Artist artist) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .artistDao().updateArtist(artist);
    }

    public int deleteArtist(Context context, Artist artist) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .artistDao().deleteArtist(artist);
    }

    public void deleteAllArtists(Context context) {
        SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .artistDao().deleteAllArtists();
    }
}
