package co.demo.spotifydemo.model.intermediary;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Followers implements Serializable
{

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("total")
    @Expose
    private Integer total;
    private final static long serialVersionUID = -4387397495985157901L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Followers() {
    }

    /**
     * 
     * @param total
     * @param href
     */
    public Followers(String href, Integer total) {
        super();
        this.href = href;
        this.total = total;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


}
