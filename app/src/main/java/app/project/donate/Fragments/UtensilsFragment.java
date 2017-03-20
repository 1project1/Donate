package app.project.donate.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.project.donate.R;

/**
 * Created by AmanPC on 15-03-2017.
 */

public class UtensilsFragment extends Fragment implements IGetValue {

    Activity zz;
    Button bzz;
    EditText ezz;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        zz = getActivity();
        View vzz =  inflater.inflate(R.layout.utensils_fragment, container, false);
        bzz = (Button) vzz.findViewById(R.id.utensils_page);
        ezz = (EditText) vzz.findViewById(R.id.edit_quantity);
        return vzz;
    }

    @Override
    public String getEdTValue() {

        String ezzz= ezz.getText().toString();
        ezz.setText("");
        return ezzz;

    }

    @Override
    public void sendValue() {

    }
}
