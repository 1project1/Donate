package app.project.donate.model;

import android.animation.TimeInterpolator;

/**
 * Created by AmanPC on 12-03-2017.
 */

public class ItemModel {
    public final String description;
    public final int colorId1;
    public final int childImages;
    public final TimeInterpolator interpolator;

    public ItemModel(String description, int colorId1, int childImages, TimeInterpolator interpolator){
        this.description = description;
        this.colorId1 = colorId1;
        this.childImages = childImages;
        this.interpolator = interpolator;
    }
}
