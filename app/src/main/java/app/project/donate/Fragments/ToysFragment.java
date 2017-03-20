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

public class ToysFragment extends Fragment implements IGetValue{

    Activity ar ;
    Button bt;
    EditText ets;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ar = getActivity();
        View vr =  inflater.inflate(R.layout.toys_fragment, container, false);
            bt = (Button) vr.findViewById(R.id.toys_page);
            ets = (EditText) vr.findViewById(R.id.edit_quantity);
        return  vr;
    }

    @Override
    public String getEdTValue() {

        String s =  ets.getText().toString();
        ets.setText("");
        return s;
    }

    @Override
    public void sendValue() {

    }
}
