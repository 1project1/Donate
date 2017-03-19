package app.project.donate.model;

/**
 * Created by ArupPc on 28-02-2017.
 */

public class DonateItem {
    String title,message,ngoLocation;
    int quantity;
    boolean requestPending;
    String date;

    public String getNgoLocation() {
        return ngoLocation;
    }

    public void setNgoLocation(String ngoLocation) {
        this.ngoLocation = ngoLocation;
    }

    public boolean isRequestPending() {
        return requestPending;
    }

    public void setRequestPending(boolean requestPending) {
        this.requestPending = requestPending;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DonateItem(String title, String message, int quantity,  String ngo, String date) {
        this.title = title;
        this.message = message;
        this.quantity = quantity;

        this.ngoLocation = ngo;
        this.date = date;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
