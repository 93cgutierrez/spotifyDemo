package co.demo.spotifydemo.util;

import android.content.Context;

import androidx.room.Room;
import co.demo.spotifydemo.io.SpotifyDemoDatabase;

public class SpotifyDemoDatabaseUtil {
    private static SpotifyDemoDatabase spotifyDemoDatabase;

    public static SpotifyDemoDatabase getSpotifyDemoDatabase(Context context) {
        if (spotifyDemoDatabase == null) {
            spotifyDemoDatabase
                    = Room.databaseBuilder(context,
                            SpotifyDemoDatabase.class,
                            Parameters.DATABASE_NAME)
                    .build();
        }
        return spotifyDemoDatabase;
    }
}
