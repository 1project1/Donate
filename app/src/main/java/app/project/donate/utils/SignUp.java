package app.project.donate.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import app.project.donate.R;

/**
 * Created by Aakash on 20-Feb-17.
 */

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    Button signUp;
    EditText userName, password, confirmPassword;
    private FirebaseAuth mAuth;

    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        init();

        signUp.setOnClickListener(this);


        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.equals(null) || s.length() == 0 || s.equals(""))
                    userName.setError("Required");
                else {
                    userName.setError(null);
                }
            }
        });
    }

    private void init() {
        // FireBase
        mAuth = FirebaseAuth.getInstance();

        userName = (EditText) findViewById(R.id.email_signup);
        password = (EditText) findViewById(R.id.password_signup);
        confirmPassword = (EditText) findViewById(R.id.confirm_password_signup);

        signUp = (Button) findViewById(R.id.signUp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp:
                String u = userName.getText().toString();
                String p = password.getText().toString();
                String cp = confirmPassword.getText().toString();
                if (u.isEmpty() || p.isEmpty() ||cp.isEmpty()) {
                    Snackbar.make(v, "Fill All details", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(!p.equals(cp)) {
                    password.setError("Passowords don't Match");
                    confirmPassword.setError("Passowords don't Match");
                }

                mAuth.createUserWithEmailAndPassword(u, p)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("Firebase", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

                break;

        }
    }
}

