package app.project.donate.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.project.donate.Cart;
import app.project.donate.R;

/**
 * Created by AmanPC on 15-03-2017.
 */

public class BooksFragment extends Fragment {

    Activity context;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        return inflater.inflate(R.layout.books_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button = (Button) context.findViewById(R.id.cartButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText quantity = (EditText) context.findViewById(R.id.edit_quantity);
                Intent intent = new Intent(context, Cart.class);
                intent.putExtra("text",quantity.getText().toString());
                startActivity(intent);
            }
        });
    }
}
