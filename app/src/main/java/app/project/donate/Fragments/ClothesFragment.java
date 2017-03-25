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
import android.widget.RadioButton;

import app.project.donate.R;

/**
 * Created by AmanPC on 14-03-2017.
 */

public class ClothesFragment extends Fragment implements ClothesInterface {


    Activity a;
    Button cl;
    EditText  q;
    CheckBox male,female,c1,c2,c3,c4,c5,c6,c7;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        a = getActivity();
        View v2 =  inflater.inflate(R.layout.clothes_fragment, container, false);
        cl = (Button)v2.findViewById(R.id.clothes_page);
        q = (EditText)v2.findViewById(R.id.edit_quantity);
        male = (CheckBox) v2.findViewById(R.id.maleOption);
        female = (CheckBox) v2.findViewById(R.id.femaleOption);
        c1 = (CheckBox) v2.findViewById(R.id.years0to3);
        c2 = (CheckBox) v2.findViewById(R.id.years4to6);
        c3 = (CheckBox) v2.findViewById(R.id.years7to12);
        c4 = (CheckBox) v2.findViewById(R.id.years13to18);
        c5 = (CheckBox) v2.findViewById(R.id.years18andAbove);
        return v2;
    }

    @Override
    public String getEDTValue() {
        String s =  q.getText().toString();
        q.setText("");
        return  s;
    }

    @Override
    public String getMale() {
        String s = male.getText().toString();
        if (male.isChecked())
            return s;
        return null;
    }

    @Override
    public String getFemale() {
        String s = female.getText().toString();
        if (female.isChecked())
            return s;
        return null;
    }

    @Override
    public String getCategory() {
        String s = "";
        if (c1.isChecked())
            s += c1.getText().toString()+",";
        if (c2.isChecked())
            s += c2.getText().toString()+",";
        if (c3.isChecked())
            s += c3.getText().toString()+",";
        if (c4.isChecked())
            s += c4.getText().toString()+",";
        if (c5.isChecked())
            s += c5.getText().toString()+",";

        return s;
    }

    @Override
    public void sendValue() {

    }
}
