package co.demo.spotifydemo.io;

import co.demo.spotifydemo.util.Parameters;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotifyAdapter {
    private static final String BASE_URL = Parameters.API_SERVER_BASE_URL;
    private static SpotifyApiService API_SERVICE;

    public static SpotifyApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.addInterceptor(new ServiceInterceptor()
                    ).build()) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(SpotifyApiService.class);
        }

        return API_SERVICE;
    }
}
