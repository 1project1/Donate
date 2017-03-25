package app.project.donate.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import app.project.donate.R;

/**
 * Created by AmanPC on 15-03-2017.
 */

public class UtensilsFragment extends Fragment implements ShoesInterface {

    Activity zz;
    Button bzz;
    EditText ezz;
    CheckBox c1,c2,c3,c4;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        zz = getActivity();
        View vzz =  inflater.inflate(R.layout.utensils_fragment, container, false);
        bzz = (Button) vzz.findViewById(R.id.utensils_page);
        ezz = (EditText) vzz.findViewById(R.id.edit_quantity);
        c1 = (CheckBox) vzz.findViewById(R.id.pressureCooker);
        c2 = (CheckBox) vzz.findViewById(R.id.fryingPans);
        c3 = (CheckBox) vzz.findViewById(R.id.plates);
        c4 = (CheckBox) vzz.findViewById(R.id.glass);
        return vzz;
    }

    @Override
    public String getGender() {
        return null;
    }

    @Override
    public String getEDTValue() {
        String val = ezz.getText().toString();
        return val;
    }

    @Override
    public String getCategory() {
        String cat = "";
        if (c1.isChecked())
            cat += c1.getText().toString()+"\n";
        if (c2.isChecked())
            cat += c2.getText().toString()+"\n";
        if (c3.isChecked())
            cat += c3.getText().toString()+"\n";
        if (c4.isChecked())
            cat += c4.getText().toString()+"\n";
        return cat;
    }

    @Override
    public void sendValue() {

    }
}
