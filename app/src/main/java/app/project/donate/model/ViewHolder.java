package app.project.donate.model;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import app.project.donate.R;

/**
 * Created by AmanPC on 12-03-2017.
 */

public class ViewHolder {
    public TextView parentHeader;
    public ImageView childImage;
    public EditText quantity;

    public ViewHolder(View view){
        this.parentHeader = (TextView) view.findViewById(R.id.parent_header);
        this.childImage = (ImageView) view.findViewById(R.id.donationChildImage);
        this.quantity = (EditText) view.findViewById(R.id.quantityApprox);
    }

}
