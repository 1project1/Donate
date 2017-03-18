package app.project.donate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountSettings extends DialogActivity implements View.OnFocusChangeListener, View.OnTouchListener {
    DatabaseReference mref;

    FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView name, email, address,phone;
    CircleImageView profilePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);
        init();
        setDetails();

        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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
        profilePicture = (CircleImageView)findViewById(R.id.display_profile_picture);
        mAuth = FirebaseAuth.getInstance();
    }

    private void setDetails() {

        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            ((TextView) findViewById(R.id.email_verified)).setText("eMailVerified=" + user.isEmailVerified());
            ((TextView) findViewById(R.id.display_name)).setText(user.getDisplayName());
            ((TextView) findViewById(R.id.display_email)).setText(user.getEmail());

            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl()).fitCenter().into(profilePicture);
            }
                //((EditText) findViewById(R.id.display_providerId)).setText(user.getProviderId());
                //((EditText) findViewById(R.id.display_gender)).setText(mFirebaseAnalytics.);
                //TODO display values here harsh
                mref= FirebaseDatabase.getInstance().getReference();
                showProgressDialog();
                //final FirebaseUser User=FirebaseAuth.getInstance().getCurrentUser();
                // mref.child("endUsers").child(user.getUid()).child("User_details");
                mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        address.setText ((String) dataSnapshot.child("endUsers").child(user.getUid()).child("User_details").child("address").getValue());
                        phone.setText((String) dataSnapshot.child("endUsers").child(user.getUid()).child("User_details").child("phone").getValue());
                        //address.setText(addr);
                        //phone.setText(ph);
                        // Toast.makeText(AccountSettings.this, "" + addr + ph, Toast.LENGTH_SHORT).show();
                        hideProgressDialog();
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });


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

    public void lang(View view) {
        final ArrayAdapter<String> languages = new ArrayAdapter<String>(view.getContext(), android.R.layout.select_dialog_item, getResources().getStringArray(R.array.languages));

        AlertDialog.Builder ad = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.select_language))
                .setNegativeButton(getResources().getString(R.string.cancel), null);
        ad.setAdapter(languages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedLang = languages.getItem(which);
                changeLanguage(selectedLang);
            }
        }).show();

    }

    private void changeLanguage(String lang) {
        Log.i("Lang", lang);
        if (lang.equals("Hindi")) {
            LocaleHelper.setLocale(this, "hi");
        } else {
            Context context = LocaleHelper.setLocale(this, "en");
        }
    }
}
