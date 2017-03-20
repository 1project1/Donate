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

public class ShoesFragment extends Fragment implements IGetValue{

    Activity ac;
    Button bs;
    EditText et;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ac = getActivity();
        View  vs = inflater.inflate(R.layout.shoes_fragment, container, false);
            bs = (Button) vs.findViewById(R.id.shoes_page);
        et = (EditText)vs.findViewById(R.id.edit_quantity);
        return  vs;
    }

    @Override
    public String getEdTValue() {
        String a =  et.getText().toString();
        et.setText("");
        return  a;
    }

    @Override
    public void sendValue() {

    }
}
