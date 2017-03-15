package app.project.donate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountSettings extends AppCompatActivity implements View.OnFocusChangeListener, View.OnTouchListener {

    FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView name, email, address,phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        init();
        setDetails();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        name.setOnFocusChangeListener(this);
        email.setOnFocusChangeListener(this);


        name.setOnTouchListener(this);
        email.setOnTouchListener(this);
        address.setOnTouchListener(this);
        phone.setOnTouchListener(this);
    }

    private void init() {
        name = (TextView) findViewById(R.id.display_name);
        email = (TextView) findViewById(R.id.display_email);
        address = (TextView) findViewById(R.id.display_address);
        phone =(TextView) findViewById(R.id.display_phone);
        mAuth = FirebaseAuth.getInstance();
    }

    private void setDetails() {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            ((TextView) findViewById(R.id.email_verified)).setText("eMailVerified=" + user.isEmailVerified());
            ((TextView) findViewById(R.id.display_name)).setText(user.getDisplayName());
            ((TextView) findViewById(R.id.display_email)).setText(user.getEmail());
            //((EditText) findViewById(R.id.display_providerId)).setText(user.getProviderId());
            //((EditText) findViewById(R.id.display_gender)).setText(mFirebaseAnalytics.);
            //TODO display values here harsh


        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            v.getBackground().clearColorFilter();

        } else {
            //v.getBackground().setColorFilter(getResources().getColor(R.color.color_primary_accent_login), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    protected void onStart() {
        setDetails();
        super.onStart();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        switch (v.getId()) {

            //For email Button
            case R.id.display_email:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Log.e("Clicked", "Pencil");
                        //mFirebaseAnalytics.setUserProperty("Gender", "Male");
                        Intent i = new Intent(getApplicationContext(), EditDetail.class);
                        i.putExtra("title", "Edit Your Email");
                        i.putExtra("type", "Email");
                        i.putExtra("currentValue", email.getText().toString());
                        startActivity(i);
                        return true;
                    }
                }
                return false;

            //for name pencil Click
            case R.id.display_name:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Log.e("Clicked", "Pencil");
                        //mFirebaseAnalytics.setUserProperty("Gender", "Male");
                        Intent i = new Intent(getApplicationContext(), EditDetail.class);
                        i.putExtra("title", "Edit Your Name");
                        i.putExtra("type", "Name");
                        i.putExtra("currentValue", name.getText().toString());
                        startActivity(i);
                        return true;
                    }
                }
                return false;

            case R.id.display_address:
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // your action here
                    Log.e("Clicked", "Pencil");
                    //mFirebaseAnalytics.setUserProperty("Gender", "Male");
                    Intent i = new Intent(getApplicationContext(), EditDetail.class);
                    i.putExtra("title", "Edit Your Address");
                    i.putExtra("type", "Address");
                    i.putExtra("currentValue", address.getText().toString());
                    startActivity(i);
                    return true;
                }
            }
            return false;

            case R.id.display_phone:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Log.e("Clicked", "Pencil");
                        //mFirebaseAnalytics.setUserProperty("Gender", "Male");
                        Intent i = new Intent(getApplicationContext(), EditDetail.class);
                        i.putExtra("title", "Edit Your Phone");
                        i.putExtra("type", "Phone");
                        i.putExtra("currentValue", phone.getText().toString());
                        startActivity(i);
                        return true;
                    }
                }
                return false;
        }

        return false;



    }
}
