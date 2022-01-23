package co.demo.spotifydemo.model.intermediary;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ExternalUrls implements Serializable
{

    @SerializedName("spotify")
    @Expose
    private String spotify;
    private final static long serialVersionUID = -8888343451144100209L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ExternalUrls() {
    }

    /**
     * 
     * @param spotify
     */
    public ExternalUrls(String spotify) {
        super();
        this.spotify = spotify;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }


}
