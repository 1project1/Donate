package app.project.donate.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.project.donate.R;
import app.project.donate.model.NgoList;

/**
 * Created by grant on 11-03-2017.
 */
//
public class NGOListAdapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    String[] introduction;
    String[] link;
    private static LayoutInflater inflater=null;
    public NGOListAdapter(NgoList ngoList, String[] prgmNameList, int[] prgmImages, String[] intro, String[] links) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=ngoList;
        imageId=prgmImages;
        introduction=intro;
        link=links;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listitem, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv3=(TextView) rowView.findViewById(R.id.textView3);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        holder.tv3.setText(introduction[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(context, "You Clicked "+link[position], Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link[position]));
                context.startActivity(intent);
            }
        });
        return rowView;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
        TextView tv3;
    }
}
