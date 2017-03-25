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
 * Created by AmanPC on 14-03-2017.
 */

public class ToysFragment extends Fragment implements ShoesInterface {

    Activity ar ;
    Button bt;
    EditText ets;
    CheckBox c1,c2,c3,c4;;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ar = getActivity();
        View vr =  inflater.inflate(R.layout.toys_fragment, container, false);
            bt = (Button) vr.findViewById(R.id.toys_page);
            ets = (EditText) vr.findViewById(R.id.edit_quantity);
        c1 = (CheckBox) vr.findViewById(R.id.toys1to3);
        c2 = (CheckBox) vr.findViewById(R.id.toys4to6);
        c3 = (CheckBox) vr.findViewById(R.id.toys7to12);
        c4 = (CheckBox) vr.findViewById(R.id.toys13andAbove);
        return  vr;
    }


    @Override
    public String getGender() {
        return null;
    }

    @Override
    public String getEDTValue() {
        String s = ets.getText().toString();
        return s;
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
