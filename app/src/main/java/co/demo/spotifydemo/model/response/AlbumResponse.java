package co.demo.spotifydemo.model.response;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.demo.spotifydemo.model.intermediary.ItemAlbum;

public class AlbumResponse implements Serializable
{

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("items")
    @Expose
    private List<ItemAlbum> itemAlbums = null;
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
    private final static long serialVersionUID = 4595847290658463625L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AlbumResponse() {
    }

    /**
     * 
     * @param next
     * @param total
     * @param offset
     * @param previous
     * @param limit
     * @param href
     * @param itemAlbums
     */
    public AlbumResponse(String href, List<ItemAlbum> itemAlbums, Integer limit, String next, Integer offset, String previous, Integer total) {
        super();
        this.href = href;
        this.itemAlbums = itemAlbums;
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

    public List<ItemAlbum> getItems() {
        return itemAlbums;
    }

    public void setItems(List<ItemAlbum> itemAlbums) {
        this.itemAlbums = itemAlbums;
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
