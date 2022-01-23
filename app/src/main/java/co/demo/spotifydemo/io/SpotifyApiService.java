package co.demo.spotifydemo.io;

import co.demo.spotifydemo.model.response.AlbumResponse;
import co.demo.spotifydemo.model.response.QueryResponse;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface SpotifyApiService {

    @GET("search?type=artist&offset=0&limit=1")
    Observable<Response<QueryResponse>> searchArtists(@Query("query") String query);

    @GET("artists/{artistId}/albums?offset=0&limit=50")
    Observable<Response<AlbumResponse>> getAlbumsByArtistID(@Path("artistId") String artistId);


}
