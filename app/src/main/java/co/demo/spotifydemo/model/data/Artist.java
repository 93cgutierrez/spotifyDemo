package co.demo.spotifydemo.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import co.demo.spotifydemo.model.intermediary.Image;

public class Artist implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("followers")
    @Expose
    private Integer followers;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    //another request
    @SerializedName("albums")
    @Expose
    private List<Album> albums = null;

    public Artist(String name, Integer followers, Integer popularity, List<Image> images, List<Album> albums) {
        this.name = name;
        this.followers = followers;
        this.popularity = popularity;
        this.images = images;
        this.albums = albums;
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
