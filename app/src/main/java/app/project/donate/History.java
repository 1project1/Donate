package app.project.donate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import app.project.donate.model.CartItem;
import app.project.donate.model.HistoryItem;

public class History extends AppCompatActivity {


    RecyclerView hist;
    HistoryItemAdapter adapter;
    List<HistoryItem> historyItemList;

    final ArrayList<String>Title1=new ArrayList<>();
    final ArrayList<Object> Quantity1=new ArrayList<>();
    final ArrayList<String>Message1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        hist = (RecyclerView)findViewById(R.id.hist);
        historyItemList = new ArrayList<>();
        adapter = new HistoryItemAdapter(this,historyItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        hist.setLayoutManager(layoutManager);
        hist.setAdapter(adapter);
        abc();

        //checkEmpty();

    }

    private void checkEmpty() {

        if(historyItemList.isEmpty())
            Toast.makeText(this, "Empty history", Toast.LENGTH_SHORT).show();
            finish();

    }

    private void abc(){
        final String Uid=FirebaseAuth.getInstance().getCurrentUser().getUid();



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mmyRef = database.getReference("endUsers").child(Uid).child("Donations_item_Details");
        //final List<CartItem>car=new ArrayList<CartItem>();
        mmyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count= (int) dataSnapshot.getChildrenCount();
                int i=0;
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    for(DataSnapshot postSnapshot:Snapshot.getChildren()){



                        Title1.add(i, (String) postSnapshot.child("title").getValue());
                        Message1.add(i, (String) postSnapshot.child("message").getValue());
                        Quantity1.add(i, postSnapshot.child("quantity").getValue());


                        Toast.makeText(History.this, ""+Title1.get(i)+"\n"+Message1.get(i)+"\n"+Quantity1.get(i), Toast.LENGTH_SHORT).show();

                        /*CartItem cart=new CartItem();
                               String a=((String) postSnapshot.child("title").getValue());
                                String b=((String) postSnapshot.child("message").getValue());
                                int c= (int) postSnapshot.child("quantity").getValue();
                                cart.setTitle(a);
                                cart.setMessage(b);
                                cart.setQuantity(c);
                                car.add(i,cart);
                                Toast.makeText(History.this, ""+cart.getMessage()+cart.getTitle()+cart.getQuantity(), Toast.LENGTH_LONG).show();

                            */
                            //Toast.makeText(History.this, "\t" + postSnapshot.child("title").getValue()+"\t"+postSnapshot.child("quantity").getValue()+"\t"+postSnapshot.child("message").getValue(), Toast.LENGTH_LONG).show();
                        i++;

                    }




                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
