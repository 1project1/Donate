package app.project.donate.model;

/**
 * Created by ArupPc on 26-02-2017.
 */

public class CartItem {

    int thumbnail;
    String title,message,address;
    int quantity;


    public CartItem(int thumbnail, String title, String message, String address, int quantity) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.message = message;
        this.address = address;
        this.quantity = quantity;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
