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
 * Created by AmanPC on 14-03-2017.
 */

public class ClothesFragment extends Fragment implements IGetValue {


    Activity a;
    Button cl;
    EditText  q;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        a = getActivity();
        View v2 =  inflater.inflate(R.layout.clothes_fragment, container, false);
        cl = (Button)v2.findViewById(R.id.clothes_page);
        q = (EditText)v2.findViewById(R.id.edit_quantity);
        return v2;
    }

    @Override
    public String getEdTValue() {

        String s =  q.getText().toString();
        q.setText("");
        return  s;
    }

    @Override
    public void sendValue() {

    }
}
