package app.project.donate.model;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import app.project.donate.R;
import app.project.donate.controllers.NGOListAdapter;

public class NgoList extends AppCompatActivity {

    ListView lv;
    Context context;


    public static int[] prgmImages = {R.drawable.sos_logo, R.drawable.prakash_logo, R.drawable.goonj_logo, R.drawable.images, R.drawable.images, R.drawable.images, R.drawable.images, R.drawable.images};
    public static String[] prgmNameList;
    public static String[] intro;

    public static String[] links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_list);
        prgmNameList = getResources().getStringArray(R.array.title);
        intro = getResources().getStringArray(R.array.intro);
        links = getResources().getStringArray(R.array.link);


        context = this;

        lv = (ListView) findViewById(R.id.listview);


        lv.setAdapter(new NGOListAdapter(this, prgmNameList, prgmImages, intro, links));
    }
}
