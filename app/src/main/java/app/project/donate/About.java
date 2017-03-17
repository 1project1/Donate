package app.project.donate;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class About extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textView = (TextView) findViewById(R.id.abouttextView1);
        getSupportActionBar().setTitle("About JeevOne");

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/billabong.ttf");
        textView.setTypeface(typeface);
    }
}
