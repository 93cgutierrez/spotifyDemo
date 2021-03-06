package co.demo.spotifydemo.model.intermediary;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artists implements Serializable
{

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("items")
    @Expose
    private List<ItemArtist> itemArtists = null;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("total")
    @Expose
    private Integer total;
    private final static long serialVersionUID = -6610510277654468364L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Artists() {
    }

    /**
     * 
     * @param next
     * @param total
     * @param offset
     * @param previous
     * @param limit
     * @param href
     * @param itemArtists
     */
    public Artists(String href, List<ItemArtist> itemArtists, Integer limit, String next, Integer offset, String previous, Integer total) {
        super();
        this.href = href;
        this.itemArtists = itemArtists;
        this.limit = limit;
        this.next = next;
        this.offset = offset;
        this.previous = previous;
        this.total = total;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<ItemArtist> getItems() {
        return itemArtists;
    }

    public void setItems(List<ItemArtist> itemArtists) {
        this.itemArtists = itemArtists;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


}
