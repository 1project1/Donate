package app.project.donate;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.project.donate.controllers.HistoryItemAdapter;
import app.project.donate.model.HistoryItem;

public class History extends AppCompatActivity {


    RecyclerView hist;
    HistoryItemAdapter adapter;
    List<HistoryItem> historyItemList;
    ProgressBar progressBar;
/*    List<String> Title1;
    List<Long> Quantity1;
    List<String> Message1;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("History");
        hist = (RecyclerView) findViewById(R.id.hist);
        historyItemList = new ArrayList<>();
        adapter = new HistoryItemAdapter(this, historyItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        progressBar = (ProgressBar) findViewById(R.id.pbar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        hist.setLayoutManager(layoutManager);
        hist.setAdapter(adapter);

        new BackTask(this).execute();

    }

    public int getThumbnailId(String title) {
        int id = 0;
        switch (title.toLowerCase()) {
            case "clothes":
                id = R.mipmap.dummy;
                break;
            case "utensils":
                id = R.mipmap.ic_launcher;
                break;
            case "shoes":
                id = R.mipmap.dummy;
                break;
            case "bags":
                id = R.mipmap.ic_launcher;
                break;
            case "furniture":
                id = R.mipmap.dummy;
                break;
            case "bedsheets":
                id = R.mipmap.ic_launcher;
                break;
            case "toys":
                id = R.mipmap.dummy;
                break;

        }
        return id;
    }
//No Use
    /*private void prepareItems() {
        Log.d("len:", Title1.size() + "");
        for (int i = 0; i < Title1.size(); i++) {
            HistoryItem item = new HistoryItem(Title1.get(i), getThumbnailId(Title1.get(i)),
                    "Dummy NGO", "User Address", Message1.get(i), Quantity1.get(i));
            Log.i("size", "Size:" + historyItemList.size());
            Log.i("History", Title1.get(i) + "\n" + Message1.get(i) + "\n" + Quantity1.get(i));

            historyItemList.add(item);
            Log.i("size", "Size:" + historyItemList.size());
        }

        for (String i : Title1) {
            Log.d("itemList", i);
        }
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "prepared", Toast.LENGTH_SHORT).show();
    }
*/
    private void checkEmpty() {

        if (historyItemList.isEmpty())
            Toast.makeText(this, "Empty history", Toast.LENGTH_SHORT).show();
        finish();

    }


    public class BackTask extends AsyncTask<Void, Void, Void> {


        Context mContext;

        public BackTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            final String Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mmyRef = database.getReference("endUsers").child(Uid).child("Donations_item_Details");
            mmyRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                        for (DataSnapshot postSnapshot : Snapshot.getChildren()) {
                            String t = (String) postSnapshot.child("title").getValue();
                            String m = (String) postSnapshot.child("message").getValue();
                            historyItemList.add(new HistoryItem(t, getThumbnailId(t), "Dummy NGO", "Dummy Address",
                                    m, (Long) postSnapshot.child("quantity").getValue()));

//                        Log.i("History" ,Title1.get(i)+"\n"+Message1.get(i)+"\n"+Quantity1.get(i));


                        }


                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            //checkEmpty();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }
}
