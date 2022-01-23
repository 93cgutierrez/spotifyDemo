package co.demo.spotifydemo.io;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import co.demo.spotifydemo.model.converter.GeneralConverter;
import co.demo.spotifydemo.model.dao.AlbumDao;
import co.demo.spotifydemo.model.dao.ArtistDao;
import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.model.data.Artist;

@Database(entities = {Artist.class, Album.class}, version = 1, exportSchema = false)
@TypeConverters({GeneralConverter.class})
public abstract class SpotifyDemoDatabase extends RoomDatabase {
    public abstract ArtistDao artistDao();

    public abstract AlbumDao albumDao();
}