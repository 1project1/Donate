package app.project.donate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;

import app.project.donate.controllers.RecyclerViewRecyclerAdapter;
import app.project.donate.model.ItemModel;

/**
 * Created by AmanPC on 12-03-2017.
 */

public class RecyclerViewActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, RecyclerViewActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getSupportActionBar().setTitle(RecyclerViewActivity.class.getSimpleName());

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<ItemModel> data = new ArrayList<>();
        data.add(new ItemModel("CLOTHES", R.color.material_orange_500, R.mipmap.clothes,
                Utils.createInterpolator(Utils.ACCELERATE_INTERPOLATOR)));

        data.add(new ItemModel("BOOKS", R.color.material_orange_500, R.mipmap.books,
                Utils.createInterpolator(Utils.ACCELERATE_INTERPOLATOR)));

        data.add(new ItemModel("TOYS", R.color.material_orange_500, R.mipmap.toys,
                Utils.createInterpolator(Utils.ACCELERATE_INTERPOLATOR)));

        data.add(new ItemModel("FOOD ITEMS", R.color.material_orange_500, R.mipmap.food,
                Utils.createInterpolator(Utils.ACCELERATE_INTERPOLATOR)));

        data.add(new ItemModel("UTENSILS", R.color.material_orange_500, R.mipmap.utensils,
                Utils.createInterpolator(Utils.ACCELERATE_INTERPOLATOR)));

        recyclerView.setAdapter(new RecyclerViewRecyclerAdapter(data));
    }
}
