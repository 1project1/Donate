package app.project.donate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

public class Acknowledgement extends AppCompatActivity {

    private String our_username = "jeevanmru@gmail.com";
    private String our_password = "bahubali";
    String name;
    String ngo_name;
    String user_email;
    String mail_subject;
    String mail_body;
    String donated_item_name;
    String donated_item_quantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mail_subject = "Donation Confirmation - Your Donation request with " + ngo_name + " has " +
                "been made successfully.";

        mail_body = "Hi " + name + ",\n\nThank you for your donation.\n\nYou will soon be " +
                "contacted by " + ngo_name + " for further processing.\n\nPlease find below, the " +
                "summary of items donated by you.\n\n" + donated_item_name + "\n"
                + donated_item_quantity;

        BackgroundMail.newBuilder(Acknowledgement.this)
                .withUsername(our_username)
                .withPassword(our_password)
                .withMailto(user_email)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject(mail_subject)
                .withBody(mail_body)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //do some magic
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                    }
                })
                .send();
    }
}