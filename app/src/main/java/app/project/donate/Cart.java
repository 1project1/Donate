package app.project.donate;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.project.donate.controllers.CartItemAdapter;
import app.project.donate.model.CartItem;
import app.project.donate.model.DonateItem;
import app.project.donate.ngolocator.GIS;

public class Cart extends AppCompatActivity implements LocationListener {
    DatabaseReference myRef;
    RecyclerView sv;
    CartItemAdapter adapter;
    List<CartItem> itemList;
    List<DonateItem> donationList;
    double longitude;
    double latitude;

    LocationManager locationManager;
    Location location;

    private final int REQUEST_PERMISSION_PHONE_STATE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else
                finish();
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
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
                "Books",
                "Food",
                "Toys",
                "Clothes",
                "Utensils",
                "Shoes",
                "Books",
                "Food",
                "Toys"

        };
        CartItem[] x = new CartItem[14];
        for (int i = 0; i < 12; i++) {
            x[i] = new CartItem(thumbs[i], titles[i], "Pending", "995 1st Floor, Sectot-37 ," +
                    " Faridabad, Haryana-121003 near Devi Sahai Market", i + 1);
            itemList.add(x[i]);
            Log.i("Added", itemList.get(i).getTitle());
        }

        for (CartItem d : itemList) {
            d.setThumbnail(mapThumb(d.getTitle()));
        }
        adapter.notifyDataSetChanged();
    }

    private int mapThumb(String title) {
        int id = R.mipmap.dummy;

        switch (title.toLowerCase()) {
            case "clothes":
                id = R.drawable.uniform;
                break;
            case "utensils":
                id = R.drawable.fryingpan;
                break;
            case "shoes":
                id = R.drawable.shoes;
                break;
            case "books":
                id = R.drawable.books;
                break;
            case "toys":
                id = R.drawable.train;
                break;
            case "food":
                id = R.drawable.food;
                break;

            default:
                id = R.mipmap.dummy;
        }

        return id;

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
                        firebasesavedonationitem();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 99) {
            // for each permission check if the user granted/denied them
            // you may want to group the rationale in a single dialog,
            // this is just an example
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission
                    boolean showRationale = shouldShowRequestPermissionRationale( permission );
                    if (! showRationale) {
                        // user also CHECKED "never ask again"
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting
                    } else if (Manifest.permission.WRITE_CONTACTS.equals(permission)) {
                        showRationale(new String[]{permission}, R.string.permission_denied_contacts);
                        // user did NOT check "never ask again"
                        // this is a good place to explain the user
                        // why you need the permission and ask if he wants
                        // to accept it (the rationale)

                    }
                }
            }
        }
    }

    private void showRationale(String permission[], int permission_denied_contacts) {
        ActivityCompat.requestPermissions(this,permission,1);
    }


    public List<DonateItem> getAllCartItems() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling -- not working
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},99);
        }
        else{
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
            //location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.i("Current Location", latitude + " " + longitude);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Location Not Found", Toast.LENGTH_SHORT).show();
            }
        }



        donationList = new ArrayList<>();
        GIS g = new GIS(); // using the algorithm implementation to get centres inside 5km radius
        Log.i("NGO Location", g.getNgoLocation(latitude, longitude));
        String date = DateFormat.getDateTimeInstance().format(new Date());
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        Log.i("currdate", date);
        for (CartItem x : itemList) {
            DonateItem l = new DonateItem(x.getTitle(), x.getMessage(), x.getQuantity(), g.getNgoLocation(latitude, longitude), date);
            donationList.add(l);
        }

        for (DonateItem x: donationList){
            Log.i("Items",x.getTitle()+"-" +x.getMessage()+"-" + x.getQuantity()+"-" +x.getNgoLocation()+"-" +x.getDate());
        }
        return donationList;
    }

    private void firebasesavedonationitem() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String Uid = firebaseAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("endUsers").child(Uid).child("Donations_item_Details").push().setValue(getAllCartItems());
        /*Person person = new Person();
        person.setName("harsh");
        person.setAddress("delhi");
        person.setPhone("9716490060");
        myRef.child("endUsers").child(Uid).child("User_details").setValue(person);
   */ }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
