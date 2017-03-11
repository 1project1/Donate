package app.project.donate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import app.project.donate.controllers.HistoryItemAdapter;
import app.project.donate.model.HistoryItem;

public class History extends AppCompatActivity {


    RecyclerView hist;
    HistoryItemAdapter adapter;
    List<HistoryItem> historyItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        hist = (RecyclerView)findViewById(R.id.hist);
        adapter = new HistoryItemAdapter(this,historyItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        hist.setLayoutManager(layoutManager);
        hist.setAdapter(adapter);

    }


}
