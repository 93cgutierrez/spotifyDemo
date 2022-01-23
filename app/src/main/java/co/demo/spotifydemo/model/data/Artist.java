package co.demo.spotifydemo.model.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import co.demo.spotifydemo.model.converter.GeneralConverter;
import co.demo.spotifydemo.model.intermediary.Image;

@Entity(tableName = "artist")
public class Artist implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private String id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "followers")
    @SerializedName("followers")
    @Expose
    private Integer followers;

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    @Expose
    private Integer popularity;

    @ColumnInfo(name = "images")
    @SerializedName("images")
    @Expose
    @TypeConverters(GeneralConverter.class)
    private List<Image> images;

    //another request
    @Ignore
    @ColumnInfo(name = "albums")
    @SerializedName("albums")
    @Expose
    @TypeConverters(GeneralConverter.class)
    private List<Album> albums;

    public Artist() {
    }

    public Artist(@NonNull String id, String name, Integer followers, Integer popularity, List<Image> images, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.followers = followers;
        this.popularity = popularity;
        this.images = images;
        this.albums = albums;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
