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

public class AccountSettings extends AppCompatActivity implements View.OnFocusChangeListener {

    FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView name;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        setDetailsAndInit();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        name.setOnFocusChangeListener(this);
        email.setOnFocusChangeListener(this);

        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Log.e("Clicked", "Pencil");
                        //mFirebaseAnalytics.setUserProperty("Gender", "Male");
                        Intent i = new Intent(getApplicationContext(),EditDetail.class);
                        i.putExtra("title","Edit Your Name");
                        i.putExtra("type","Name");
                        i.putExtra("currentValue",name.getText().toString());
                        startActivity(i);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void setDetailsAndInit() {

        name = (TextView) findViewById(R.id.display_name);
        email = (EditText) findViewById(R.id.display_email);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.isEmailVerified();
            ((TextView) findViewById(R.id.email_verified)).setText("eMailVerified=" + user.isEmailVerified());
            ((TextView) findViewById(R.id.display_name)).setText(user.getDisplayName());
            ((EditText) findViewById(R.id.display_email)).setText(user.getEmail());
            ((EditText) findViewById(R.id.display_providerId)).setText(user.getProviderId());
            //((EditText) findViewById(R.id.display_gender)).setText(mFirebaseAnalytics.);


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
}
