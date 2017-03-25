package app.project.donate.ngolocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import app.project.donate.R;

public class Gallerys extends AppCompatActivity {
    GridView listView;
    public static String[] eatFoodyImages = {
            "https://firebasestorage.googleapis.com/v0/b/hackathon-c7438.appspot.com/o/42220799_stimg_0307fw.jpg?alt=media&token=25db4ab6-65b2-4108-b0f1-df824ae22e69",
            "https://firebasestorage.googleapis.com/v0/b/hackathon-c7438.appspot.com/o/67633.png.jpg?alt=media&token=e8459c85-4448-40bc-9405-9192867a7e3f",
            "https://firebasestorage.googleapis.com/v0/b/hackathon-c7438.appspot.com/o/article-2424296-1BE07425000005DC-658_964x637.jpg?alt=media&token=7d861a95-078c-4721-a982-3488fd88a46e",
            "https://firebasestorage.googleapis.com/v0/b/hackathon-c7438.appspot.com/o/food_hero_1800.jpg?alt=media&token=58c89559-d113-43c8-981a-805f5abf86bf",
            "https://firebasestorage.googleapis.com/v0/b/hackathon-c7438.appspot.com/o/image.jpg?alt=media&token=d202bb63-cf99-45ec-9f9e-74948cee0d2e",
            "https://firebasestorage.googleapis.com/v0/b/hackathon-c7438.appspot.com/o/images.jpg?alt=media&token=7456ecde-5ff9-4184-b731-9976c367206d"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerys);
        listView = (GridView) findViewById(R.id.listImage);
        listView.setAdapter(new ImageListAdapter(Gallerys.this, eatFoodyImages));
    }


}
