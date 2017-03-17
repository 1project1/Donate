package app.project.donate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Aakash on 20-Feb-17.
 */

public class Temp extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;

    TextView tempDisplay;
    public static final String LOGIN_FILE = "LogInFile";
    SharedPreferences logInPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        init();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, null /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Log.i("name", logInPref.getString("name", "a user Name Here"));

        String temp = logInPref.getString("Uid", "long random Uid");
        temp += "\n" + logInPref.getString("user", "User LogInId");
        temp += "\n" + logInPref.getString("name", "The user Name");


        tempDisplay.setText(temp);

        Button b = (Button) findViewById(R.id.log_out);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences logInPref = getSharedPreferences(LOGIN_FILE, 0);
                SharedPreferences.Editor logInEditor = logInPref.edit();
                logInEditor.clear().putBoolean("isLoggedIn", false).apply();

                //firebase signOut
                FirebaseAuth.getInstance().signOut();

                //Google signOut
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // [START_EXCLUDE]
                                // aakash updateUI(false);
                                // [END_EXCLUDE]
                            }
                        });

                startActivity(new Intent(getApplicationContext(), LogIn.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
    }

    private void init() {
        tempDisplay = (TextView) findViewById(R.id.tempDisplay);
        logInPref = getSharedPreferences(LOGIN_FILE, 0);
    }


    public void Bu1(View view) {
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

    public void openCart(View view) {
        startActivity(new Intent(this,Cart.class));
    }

    public void startSettings(View v ){
        startActivity(new Intent(this,AccountSettings.class));
    }
}

