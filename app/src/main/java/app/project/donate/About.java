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

        TextView textView1 = (TextView) findViewById(R.id.abouttextView1);
        TextView textView2 = (TextView) findViewById(R.id.abouttextView2);

        getSupportActionBar().setTitle("About JeeOne");

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/billabong.ttf");
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
    }
}
