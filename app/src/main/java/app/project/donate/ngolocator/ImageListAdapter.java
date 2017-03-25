package app.project.donate.ngolocator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bumptech.glide.Glide;

import app.project.donate.R;

/**
 * Created by grant on 25-03-2017.
 */

class ImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;

    private String[] imageUrls;

    public ImageListAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.galleryitems, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }
    ImageView   imageView;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.galleryitems, parent, false);
            imageView= (ImageView) convertView.findViewById(R.id.listphoto);
        }

        Glide
                .with(context)
                .load(imageUrls[position])
                .into(imageView);

        return convertView;
    }
    }

