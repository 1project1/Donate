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

public class BooksFragment extends Fragment implements BookInterface {

Activity activity1;
    Button b;
    EditText e;
    CheckBox ec,sb,tb,rb,nb;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity1 = getActivity();

        View v1 =  inflater.inflate(R.layout.books_fragment, container, false);

        b = (Button) v1.findViewById(R.id.books_page);
        e = (EditText) v1.findViewById(R.id.edit_quantity);
        ec = (CheckBox) v1.findViewById(R.id.encyclopediaBooks);
        sb = (CheckBox) v1.findViewById(R.id.storyBooks);
        tb = (CheckBox) v1.findViewById(R.id.textBooks);
        rb = (CheckBox) v1.findViewById(R.id.referenceBooks);
        nb = (CheckBox) v1.findViewById(R.id.novelBooks);
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

    public String getEncyclopedia(){
        String s = ec.getText().toString();
        if(ec.isChecked())
            return s;
        return null;
    }

    public String getStoryBooks(){
        String s = sb.getText().toString();
        if(sb.isChecked())
            return s;
        return null;
    }

    public String getTextBooks(){
        String s = tb.getText().toString();
        if(tb.isChecked()) {
            return s;
        }
        tb.setChecked(false);
        return null;
    }

    public String getReferenceBooks(){
        String s = rb.getText().toString();
        if(rb.isChecked())
            return s;
        return null;
    }

    public String getNovelBooks(){
        String s = nb.getText().toString();
        if (nb.isChecked())
            return s;
        return null;
    }
    @Override
    public void sendValue() {

    }
}
