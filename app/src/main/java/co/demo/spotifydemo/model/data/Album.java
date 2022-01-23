package co.demo.spotifydemo.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import co.demo.spotifydemo.model.intermediary.Image;

public class Album implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("images")
    @Expose
    private List<Image> images;
    @SerializedName("available_markets")
    @Expose
    private List<String> availableMarkets;
    @SerializedName("spotify_url")
    @Expose
    private String url;

    public Album(String name, List<Image> images, List<String> availableMarkets, String url) {
        this.name = name;
        this.images = images;
        this.availableMarkets = availableMarkets;
        this.url = url;
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
}
