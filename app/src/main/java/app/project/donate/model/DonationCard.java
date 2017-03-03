package app.project.donate.model;

/**
 * Created by AmanPC on 26-02-2017.
 */

public class DonationCard {
    private String title;
    private int cardImages;

    public DonationCard() { }

    public DonationCard(String title, int cardImages){
        this.title = title;
        this.cardImages = cardImages;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){ this.title = title; }

    public int getCardImages(){
        return cardImages;
    }

    public void setCardImages(int cardImages){ this.cardImages = cardImages; }
}
