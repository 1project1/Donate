package app.project.donate;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.project.donate.controllers.CartItemAdapter;
import app.project.donate.model.CartItem;
import app.project.donate.model.DonateItem;

public class Cart extends AppCompatActivity {

    RecyclerView sv;
    CartItemAdapter adapter;
    List<CartItem> itemList;
    List<DonateItem> donationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        sv = (RecyclerView) findViewById(R.id.sv);
        itemList = new ArrayList<>();
        adapter = new CartItemAdapter(this, itemList);
        RecyclerView.LayoutManager mLM = new LinearLayoutManager(this);
        sv.setLayoutManager(mLM);
        sv.setAdapter(adapter);
        prepareItems();
        checkEmpty();
    }

    //TEST DATA SET
    private void prepareItems() {

        int[] thumbs = new int[]{
                R.mipmap.dummy,
                R.mipmap.ic_launcher,
                R.mipmap.dummy,
                R.mipmap.ic_launcher,
                R.mipmap.dummy,
                R.mipmap.ic_launcher,
                R.mipmap.dummy,
                R.mipmap.ic_launcher,
                R.mipmap.dummy,
                R.mipmap.ic_launcher,
                R.mipmap.dummy,
                R.mipmap.ic_launcher,
                R.mipmap.dummy,
                R.mipmap.ic_launcher
        };

        String[] titles = new String[]{
                "Clothes",
                "Utensils",
                "Shoes",
                "Bags",
                "Furniture",
                "BedSheets",
                "Toys",
                "Clothes",
                "Utensils",
                "Shoes",
                "Bags",
                "Furniture",
                "BedSheets",
                "Toys"

        };
        CartItem[] x = new CartItem[14];
        for (int i = 0; i < 14; i++) {
            x[i] = new CartItem(thumbs[i], titles[i], "Being Human", "995 1st Floor, Sectot-37 ," +
                    " Faridabad, Haryana-121003 near Devi Sahai Market", i + 1);
            itemList.add(x[i]);
            Log.i("Added", itemList.get(i).getTitle());
        }
        adapter.notifyDataSetChanged();
    }

    public void removeAll(View view) {
        new AlertDialog.Builder(this)
                .setIcon(null)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to delete all the items in your cart?")
                .setCancelable(false)
                .setPositiveButton("Yes! Do it.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        itemList.clear();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(Cart.this, "All Items deleted from the cart!", Toast.LENGTH_SHORT).show();
                        checkEmpty();
                    }
                }).setNegativeButton("Nopes", null)
                .show();


    }

    public void donateAll(View view) {
        new AlertDialog.Builder(this)
                .setIcon(null)
                .setTitle("Confirmation")
                .setMessage("Proceed with the donation process?")
                .setCancelable(false)
                .setPositiveButton("Yes! Do it.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Thank you for donating!"
                                , Toast.LENGTH_SHORT).show();
                        List<DonateItem> temp = getAllCartItems();
                        for (DonateItem x : temp) {
                            Log.i("Item", "Title:" + x.getTitle() + "\tMessage:" +
                                    x.getMessage() + "\tQuantity:" + x.getQuantity());
                        }

                        NotificationCompat.Builder b =
                                (NotificationCompat.Builder)
                                        new NotificationCompat.Builder(getApplicationContext()).
                                setSmallIcon(R.mipmap.happy)
                                .setContentTitle("Donation")
                                .setContentText("Your donation list is successfully confirmed").
                                        setColor(Color.parseColor("#43A047"));
                        NotificationManager mNotificationManager = (NotificationManager)
                                getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, b.build());
                        //TODO send the list to Harsh
                        itemList.clear();
                        adapter.notifyDataSetChanged();
                        checkEmpty();

                    }
                }).setNegativeButton("Nopes", null)
                .show();
    }

    private void checkEmpty() {
        if (itemList.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setIcon(null)
                    .setTitle("Oops!")
                    .setMessage("Your Donation Cart is Empty at this moment.\n Please Go back and select item to donate.\n Thank you")
                    .setCancelable(false)
                    .setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
        }
    }

    public List<DonateItem> getAllCartItems() {
        donationList = new ArrayList<>();
        for (CartItem x : itemList) {
            DonateItem l = new DonateItem(x.getTitle(), x.getMessage(), x.getQuantity(),true);
            donationList.add(l);
        }
        return donationList;
    }

}
