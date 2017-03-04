package app.project.donate.model;

/**
 * Created by ArupPc on 28-02-2017.
 */

public class DonateItem {
    String title,message;
    int quantity;
    boolean requestPending;

    public DonateItem(String title, String message, int quantity, boolean pending) {
        this.title = title;
        this.message = message;
        this.quantity = quantity;
        this.requestPending=pending;
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
