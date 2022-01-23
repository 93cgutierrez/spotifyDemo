package co.demo.spotifydemo.model.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import co.demo.spotifydemo.model.converter.GeneralConverter;
import co.demo.spotifydemo.model.intermediary.Image;

@Entity(tableName = "album",
        foreignKeys = {
                @ForeignKey(entity = Artist.class,
                        parentColumns = "id",
                        childColumns = "artistId_fk",
                        onDelete = ForeignKey.CASCADE)})
public class Album implements Serializable {
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

    @ColumnInfo(name = "images")
    @SerializedName("images")
    @Expose
    @TypeConverters(GeneralConverter.class)
    private List<Image> images;

    @ColumnInfo(name = "available_markets")
    @SerializedName("available_markets")
    @Expose
    @TypeConverters(GeneralConverter.class)
    private List<String> availableMarkets;

    @ColumnInfo(name = "spotify_url")
    @SerializedName("spotify_url")
    @Expose
    private String url;

    //RELACIONES
    @NonNull
    @ColumnInfo(name = "artistId_fk")
    @SerializedName("artistId")
    @Expose
    private String artistId;

    public Album() {
    }

    public Album(@NonNull String id, String name, List<Image> images, List<String> availableMarkets, String url, @NonNull String artistId) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.availableMarkets = availableMarkets;
        this.url = url;
        this.artistId = artistId;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(@NonNull String artistId) {
        this.artistId = artistId;
    }
}
