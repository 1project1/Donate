package app.project.donate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class EditDetail extends DialogActivity implements View.OnClickListener {

    EditText data;
    TextInputLayout layout;
    String title, type, currentValue;
    Button save,cancel;
    UserProfileChangeRequest profileUpdates;
    private static final String TAG = "EditDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_detail);
        Bundle b = getIntent().getExtras();

        if (b != null) {
            title = b.getString("title");
            type = b.getString("type");
            currentValue = b.getString("currentValue");
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);


        data = (EditText) findViewById(R.id.edited_data);
        layout = (TextInputLayout) findViewById(R.id.edited_data_layout);
        save = (Button) findViewById(R.id.save_change);
        cancel = (Button) findViewById(R.id.cancel_change);
        layout.setHint(type);
        data.setText(currentValue);
        data.selectAll();
        data.requestFocus();

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(data, InputMethodManager.SHOW_IMPLICIT);
        save.setOnClickListener(this);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return;

        switch (type) {
            case "Name":
                Log.d(TAG, "Name changed Button");
                String val = data.getText().toString();
                if (val.isEmpty() || val.contentEquals(currentValue)) return;

                profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(val)
                        .build();
                showProgressDialog();
                user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            Toast.makeText(getApplication(), "Update Success", Toast.LENGTH_SHORT);
                            finish();
                        }
                        hideProgressDialog();
                    }
                });
                break;
            case "Email":
                val = data.getText().toString();
                if (val.isEmpty() || val.contentEquals(currentValue)) return;

                showProgressDialog();
                user.updateEmail(data.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User email address updated.");
                                    user.sendEmailVerification();
                                    hideProgressDialog();
                                    finish();
                                }
                                hideProgressDialog();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                if (e instanceof FirebaseException) {
                                    String error = ((FirebaseException) e).getLocalizedMessage();

                                    TextView tv = (TextView) findViewById(R.id.edit_detail_error);
                                    tv.setText(error);
                                    tv.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                break;

            case "Address":
                //TODO
                // harsh to update address here

                break;
            case "Phone":
                //TODO
                // harsh to update phone number here
                break;
        }
    }
}
