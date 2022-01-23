package co.demo.spotifydemo.model.intermediary;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemAlbum implements Serializable
{

    @SerializedName("album_group")
    @Expose
    private String albumGroup;
    @SerializedName("album_type")
    @Expose
    private String albumType;
    @SerializedName("artists")
    @Expose
    private List<ArtistResponse> artistResponses = null;
    @SerializedName("available_markets")
    @Expose
    private List<String> availableMarkets = null;
    @SerializedName("external_urls")
    @Expose
    private ExternalUrls externalUrls;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("release_date_precision")
    @Expose
    private String releaseDatePrecision;
    @SerializedName("total_tracks")
    @Expose
    private Integer totalTracks;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("uri")
    @Expose
    private String uri;
    private final static long serialVersionUID = -3632377418177789755L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemAlbum() {
    }

    /**
     * 
     * @param images
     * @param releaseDate
     * @param externalUrls
     * @param type
     * @param albumGroup
     * @param uri
     * @param artistResponses
     * @param totalTracks
     * @param albumType
     * @param availableMarkets
     * @param name
     * @param releaseDatePrecision
     * @param href
     * @param id
     */
    public ItemAlbum(String albumGroup, String albumType, List<ArtistResponse> artistResponses, List<String> availableMarkets, ExternalUrls externalUrls, String href, String id, List<Image> images, String name, String releaseDate, String releaseDatePrecision, Integer totalTracks, String type, String uri) {
        super();
        this.albumGroup = albumGroup;
        this.albumType = albumType;
        this.artistResponses = artistResponses;
        this.availableMarkets = availableMarkets;
        this.externalUrls = externalUrls;
        this.href = href;
        this.id = id;
        this.images = images;
        this.name = name;
        this.releaseDate = releaseDate;
        this.releaseDatePrecision = releaseDatePrecision;
        this.totalTracks = totalTracks;
        this.type = type;
        this.uri = uri;
    }

    public String getAlbumGroup() {
        return albumGroup;
    }

    public void setAlbumGroup(String albumGroup) {
        this.albumGroup = albumGroup;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public List<ArtistResponse> getArtists() {
        return artistResponses;
    }

    public void setArtists(List<ArtistResponse> artistResponses) {
        this.artistResponses = artistResponses;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public void setReleaseDatePrecision(String releaseDatePrecision) {
        this.releaseDatePrecision = releaseDatePrecision;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(Integer totalTracks) {
        this.totalTracks = totalTracks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
