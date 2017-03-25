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
import android.widget.Toast;

import app.project.donate.R;

/**
 * Created by AmanPC on 14-03-2017.
 */

public class FoodFragment extends Fragment implements BookInterface {

    EditText title;
    Activity activity;
    Button button;
    EditText quantity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity=getActivity();
        View v = inflater.inflate(R.layout.medicines_layout, container, false);
        quantity = (EditText)v.findViewById(R.id.edit_quantity);
        button = (Button)v.findViewById(R.id.food_page);
        return  v;
    }


    @Override
    public String getEdTValue() {

        String x=  quantity.getText().toString();
        quantity.setText("");
        return  x;
    }

    @Override
    public void sendValue() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Qty:" + getEdTValue(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
