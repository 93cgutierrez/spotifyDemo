package co.demo.spotifydemo.io;

import co.demo.spotifydemo.model.response.AlbumResponse;
import co.demo.spotifydemo.model.response.QueryResponse;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface SpotifyApiService {

    @GET("search?type=artist&locale=es-CO%2Ces-US%3Bq%3D0.9%2Ces-419%3Bq%3D0.8%2Ces%3Bq%3D0.7&offset=0&limit=1")
    Observable<Response<QueryResponse>> searchArtists(@Query("query") String query);

    @GET("artists/{artistId}/albums?offset=0&limit=50&include_groups=album,single,compilation,appears_on&locale=es-CO,es-US;q=0.9,es-419;q=0.8,es;q=0.7")
    Observable<Response<AlbumResponse>> getAlbumsByArtistID(@Path("artistId") String artistId);


}
