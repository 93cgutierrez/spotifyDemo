package co.demo.spotifydemo.model.response;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.demo.spotifydemo.model.Artists;

public class QueryResponse implements Serializable
{

    @SerializedName("artists")
    @Expose
    private Artists artists;
    private final static long serialVersionUID = -5461773220041243202L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public QueryResponse() {
    }

    /**
     * 
     * @param artists
     */
    public QueryResponse(Artists artists) {
        super();
        this.artists = artists;
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(QueryResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("artists");
        sb.append('=');
        sb.append(((this.artists == null)?"<null>":this.artists));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
