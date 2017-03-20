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

public class BooksFragment extends Fragment implements IGetValue {

Activity activity1;
    Button b;
    EditText e;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity1 = getActivity();

        View v1 =  inflater.inflate(R.layout.books_fragment, container, false);

        b = (Button) v1.findViewById(R.id.books_page);
        e = (EditText) v1.findViewById(R.id.edit_quantity);

       return  v1;
    }



    @Override
    public String getEdTValue() {
    String s =  e.getText().toString();
        if(s.isEmpty()){
            e.requestFocus();

        }
        e.setText("");
        return s;
    }

    @Override
    public void sendValue() {

    }
}
