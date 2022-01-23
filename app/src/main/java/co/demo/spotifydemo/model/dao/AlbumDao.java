package co.demo.spotifydemo.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.util.Parameters;

@Dao
public interface AlbumDao {
    //getAlbumsByArtistPaged (2) offset Page
    @Query("SELECT * FROM album WHERE album.artistId_fk = :artistId LIMIT "
            + Parameters.LIMIT_FOR_PAGE
            + " OFFSET :offset")
    List<Album> getAlbumsByArtistPaged(String artistId, int offset);

    //getAlbumsByArtistCount
    @Query("SELECT COUNT(*) FROM album WHERE album.artistId_fk = :artistId")
    int getAlbumsByArtistIdCount(String artistId);

    //getAll
    @Query("SELECT * FROM album")
    List<Album> getAlbums();

    //getById
    @Query("SELECT * FROM album WHERE album.id = :albumId")
    Album getAlbumById(String albumId);

    //getAlbumsByArtistId
    @Query("SELECT * FROM album WHERE album.artistId_fk = :artistId")
    List<Album> getAlbumsByArtistId(String artistId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createdAlbum(Album album);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> createdAlbum(List<Album> albums);

    @Update
    int updateAlbum(Album album);

    @Delete
    int deleteAlbum(Album album);

    @Query("DELETE FROM Album")
    void deleteAllAlbums();
}
