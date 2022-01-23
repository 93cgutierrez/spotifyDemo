package co.demo.spotifydemo.model.repository;

import android.content.Context;

import java.util.List;

import co.demo.spotifydemo.io.SpotifyAdapter;
import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.model.response.AlbumResponse;
import co.demo.spotifydemo.util.SpotifyDemoDatabaseUtil;
import io.reactivex.Observable;
import retrofit2.Response;

public class AlbumRepository {
    private final String TAG = AlbumRepository.class.getCanonicalName();

    //API
    public Observable<Response<AlbumResponse>> getAlbumsByArtistID(Context context, String artistId) {
        return SpotifyAdapter.getApiService(context).getAlbumsByArtistID(artistId);
    }

    //DAO
    public List<Album> getAlbumsByArtistPaged(Context context, String artistId, int offset) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().getAlbumsByArtistPaged(artistId, offset);
    }

    public int getAlbumsByArtistIdCount(Context context, String artistId) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().getAlbumsByArtistIdCount(artistId);
    }
    public List<Album> getAlbums(Context context) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().getAlbums();
    }

    public Album getAlbumById(Context context, String albumId) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().getAlbumById(albumId);
    }

    public List<Album> getAlbumsByArtistId(Context context, String artistId) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().getAlbumsByArtistId(artistId);
    }

    public long createdAlbum(Context context, Album album) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().createdAlbum(album);
    }

    public List<Long> createdAlbum(Context context, List<Album> albums) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().createdAlbum(albums);
    }

    public int updateAlbum(Context context, Album album) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().updateAlbum(album);
    }

    public int deleteAlbum(Context context, Album album) {
        return SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().deleteAlbum(album);
    }

    public void deleteAllAlbums(Context context) {
        SpotifyDemoDatabaseUtil
                .getSpotifyDemoDatabase(context)
                .albumDao().deleteAllAlbums();
    }
}
