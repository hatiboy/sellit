package sellit.soict.com.model;


import java.io.Serializable;

/**
 * Created by Dang Hien on 07/04/2016.
 */
public class Product extends BaseProduct implements Serializable {
    private String content;
    private String phone;
    private String status;
    private String id_category;
    private String id_address;
    private String user_name;
    public Address address = new Address();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getId_address() {
        return id_address;
    }

    public void setId_address(String id_address) {
        this.id_address = id_address;
    }
}
