package app.project.donate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateUs extends AppCompatActivity {

    private RatingBar rating;
    public String ratingValue="";
    private Button ratingButton, ratingLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        setTitle(R.string.rate_us_title);

        this.setFinishOnTouchOutside(false);

        rating = (RatingBar) findViewById(R.id.ratingBar);
        ratingButton = (Button) findViewById(R.id.rateButton);
        ratingLater = (Button) findViewById(R.id.laterButton);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    ratingValue = String.valueOf(rating);
            }
        });

        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ratingValue.equals("")|| ratingValue.equals("0.0")){
                    Toast.makeText(RateUs.this, R.string.rate_us_toast_ask , Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RateUs.this, R.string.rate_us_toast_thanks, Toast.LENGTH_SHORT).show();

                    SharedPreferences rated_sharedPref = getSharedPreferences("RateProgress", MODE_PRIVATE);
                    int apprated = rated_sharedPref.getInt("apprated", 1);
                    SharedPreferences.Editor editor = rated_sharedPref.edit();
                    editor.putInt("apprated", apprated);
                    editor.apply();

                    finish(RateUs.class);
                }
            }
        });

        ratingLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(RateUs.class);
            }
        });

    }

    private int rateresult() {
        return 1;
    }


    private void finish(Class<RateUs> rateUsClass) {
        super.finish();
    }
}
