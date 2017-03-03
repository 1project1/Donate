package app.project.donate.model;

/**
 * Created by AmanPC on 02-03-2017.
 */

public class GetDonationCardDetails {
    private String title, quantity;

    public GetDonationCardDetails(){}

    public GetDonationCardDetails(String title, String quantity){
        this.title = title;
        this.quantity = quantity;
    }
}
