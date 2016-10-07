package sellit.soict.com.model;

import java.io.Serializable;

/**
 * Created by Dang Hien on 19/04/2016.
 */
public class BaseProduct implements Serializable {

    private long _id;
    private String name;
    private long price;
    private long date;
    private String pictures;
    private int count_pic;

    public int getCount_pic() {
        return count_pic;
    }

    public void setCount_pic(int count_pic) {
        this.count_pic = count_pic;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
