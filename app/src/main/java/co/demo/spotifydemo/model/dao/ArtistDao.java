package co.demo.spotifydemo.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import co.demo.spotifydemo.model.data.Artist;

@Dao
public interface ArtistDao {
    //getAll
    @Query("SELECT * FROM artist")
    List<Artist> getArtists();

    //getById
    @Query("SELECT * FROM artist WHERE artist.id = :artistId")
    Artist getArtistById(String artistId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createdArtist(Artist artist);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> createdArtist(List<Artist> artists);

    @Update
    int updateArtist(Artist artist);

    @Delete
    int deleteArtist(Artist artist);

    @Query("DELETE FROM artist")
    void deleteAllArtists();
}