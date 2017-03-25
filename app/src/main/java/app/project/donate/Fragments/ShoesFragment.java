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

public class ShoesFragment extends Fragment implements ShoesInterface {

    Activity ac;
    Button bs;
    EditText et;
    CheckBox male, female, kids, c1,c2,c3,c4,c5,c6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ac = getActivity();
        View  vs = inflater.inflate(R.layout.shoes_fragment, container, false);
            bs = (Button) vs.findViewById(R.id.shoes_page);
        et = (EditText)vs.findViewById(R.id.edit_quantity);
        male = (CheckBox) vs.findViewById(R.id.maleOption);
        female = (CheckBox) vs.findViewById(R.id.femaleOption);
        kids = (CheckBox) vs.findViewById(R.id.kidsOption);
        c1 = (CheckBox) vs.findViewById(R.id.shoeSize4);
        c2 = (CheckBox) vs.findViewById(R.id.shoeSize5);
        c3 = (CheckBox) vs.findViewById(R.id.shoeSize6);
        c4 = (CheckBox) vs.findViewById(R.id.shoeSize7);
        c5 = (CheckBox) vs.findViewById(R.id.shoeSize8);
        c6 = (CheckBox) vs.findViewById(R.id.shoeSize9);
        return  vs;
    }

    @Override
    public String getGender() {
        String gender = "";
        if (male.isChecked())
            gender += male.getText().toString()+"\n";
        if (female.isChecked())
            gender += female.getText().toString()+"\n";
        if (kids.isChecked())
            gender += kids.getText().toString()+"\n";
        return gender;
    }

    @Override
    public String getEDTValue() {
        String s = et.getText().toString();
        return s;
    }

    @Override
    public String getCategory() {
        String size = "";
        if (c1.isChecked())
            size += c1.getText().toString();
        if (c2.isChecked())
            size += c2.getText().toString();
        if (c3.isChecked())
            size += c3.getText().toString();
        if (c4.isChecked())
            size += c4.getText().toString();
        if (c5.isChecked())
            size += c5.getText().toString();
        if (c6.isChecked())
            size += c6.getText().toString();

        return size;
    }

    @Override
    public void sendValue() {

    }
}
