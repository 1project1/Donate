package app.project.donate.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import app.project.donate.LogIn;
import app.project.donate.R;

public class Splash extends Activity {
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startAnimation();

        textanimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this, LogIn.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                finish();
            }
        },2000);
    }
    private void startAnimation(){
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.fadein);
        animation.reset();
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        imageView.clearAnimation();
        imageView.startAnimation(animation);

        /*RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.activity_main);
        relativeLayout.clearAnimation();
        relativeLayout.startAnimation(animation);
        */
    }
    private void textanimation(){
        Animation animation1=AnimationUtils.loadAnimation(this, R.anim.translate);
        animation1.reset();
        TextView textView=(TextView)findViewById(R.id.textView2);
        textView.clearAnimation();
        textView.startAnimation(animation1);
    }
}
