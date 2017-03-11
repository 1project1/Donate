package app.project.donate.model;

/**
 * Created by ArupPc on 11-03-2017.
 */

public class HistoryItem {

    String title, ngo,status,address;int quantity;
    int thumbnail;

    public HistoryItem(String title, int thumbnail,String ngo,String address, String status, int quantity) {
        this.title = title;
        this.ngo = ngo;
        this.status = status;
        this.quantity = quantity;
        this.address = address;
        this.thumbnail = thumbnail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNgo() {
        return ngo;
    }

    public void setNgo(String ngo) {
        this.ngo = ngo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
