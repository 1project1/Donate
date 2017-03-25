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

public class OthersFragment extends Fragment implements OthersInterface{

    EditText title;
    Activity activity;
    Button button;
    EditText quantity, itemName, itemDescription;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity=getActivity();
        View v = inflater.inflate(R.layout.others_fragment, container, false);
        quantity = (EditText)v.findViewById(R.id.edit_quantity);
        itemDescription = (EditText)v.findViewById(R.id.item_description);
        itemName = (EditText)v.findViewById(R.id.item_name);
        button = (Button)v.findViewById(R.id.others_page);
        return  v;
    }

    @Override
    public String getEDTValue() {
        String quant = quantity.getText().toString();
        return quant;
    }

    @Override
    public String getItemName() {
        String name = itemName.getText().toString();
        return name;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getDescription() {
        String desc = itemDescription.getText().toString();
        return desc;
    }

    @Override
    public void sendValue() {

    }
}
