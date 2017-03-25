package app.project.donate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.project.donate.CartDb.CartDatabase;
import app.project.donate.Fragments.BooksFragment;
import app.project.donate.Fragments.ClothesFragment;
import app.project.donate.Fragments.OthersFragment;
import app.project.donate.Fragments.ShoesFragment;
import app.project.donate.Fragments.ToysFragment;
import app.project.donate.Fragments.UtensilsFragment;
import app.project.donate.model.NgoList;
import de.hdodenhof.circleimageview.CircleImageView;

import static app.project.donate.R.id.nav_feedback;

public class MainUi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private GoogleApiClient mGoogleApiClient;
    public static final String LOGIN_FILE = "LogInFile";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    static  CartDatabase cartDatabase;
    String temp="";
    OthersFragment othersFragment;
    ClothesFragment clothesFragment;
    UtensilsFragment utensilsFragment;
    ToysFragment toysFragment;
    BooksFragment booksFragment;
    ShoesFragment shoesFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.uniform);
        tabLayout.getTabAt(1).setIcon(R.drawable.train);
        tabLayout.getTabAt(2).setIcon(R.drawable.books);
        tabLayout.getTabAt(3).setIcon(R.drawable.fryingpan);
        tabLayout.getTabAt(4).setIcon(R.drawable.shoes);
        tabLayout.getTabAt(5).setIcon(R.drawable.hamburguer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        init();
        databaseInit();
    }

    private void databaseInit() {
        cartDatabase = new CartDatabase(this);
        Toast.makeText(this, "DB created!!!", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, null /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, AccountSettings.class));
            return true;
        } else if (id == R.id.action_logout) {

            SharedPreferences logInPref = getSharedPreferences(LOGIN_FILE, 0);
            SharedPreferences.Editor logInEditor = logInPref.edit();
            logInEditor.clear().putBoolean("isLoggedIn", false).apply();

            //firebase signOut
            FirebaseAuth.getInstance().signOut();

            //Google signOut
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // [START_EXCLUDE]
                            // aakash updateUI(false);
                            // [END_EXCLUDE]
                        }
                    });

            startActivity(new Intent(getApplicationContext(), LogIn.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            startActivity(new Intent(this, About.class));
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(this, Cart.class));
        } else if (id == R.id.nav_history) {
            startActivity(new Intent(this, History.class));
        } else if (id == R.id.nav_ngo_list) {
            startActivity(new Intent(this, NgoList.class));
        } else if (id == R.id.nav_rate_us) {

        } else if (id == nav_feedback) {
            Uri uriUrl = Uri.parse("https://docs.google.com/forms/d/1yln_gJBWt7N-Mrk0z47MB-TIpRZ3PgOTE4H2iYblSGo/viewform?edit_requested=true");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        } else if (id == R.id.nav_credits) {

           startActivity(new Intent(this, CreditsUI.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    int quant=1;
    public void sendItemsToCart(View view) {
        String title="";
        quant = 1;

        //q.setText("");
        switch (view.getId()){

            case R.id.toys_page:

                title = "toys";
                temp = toysFragment.getEDTValue();
                try {
                    quant = Integer.parseInt(temp);
                }catch (NumberFormatException e){
                    quant = 1;

                    Toast.makeText(this, "Number format Excp:" + temp, Toast.LENGTH_SHORT).show();
                }
                if(quant == 0) quant  = 1;
                //quant = Integer.parseInt(q.getText().toString());
                sendToDb(title,quant);
                break;
            case R.id.clothes_page:
                title = "clothes";
                temp = clothesFragment.getEDTValue();
                try {
                    quant = Integer.parseInt(temp);
                }catch (NumberFormatException e){
                    quant = 1;

                    Toast.makeText(this, "Number format Excp:" + temp, Toast.LENGTH_SHORT).show();
                }
                if(quant == 0) quant  = 1;
                //quant = Integer.parseInt(q.getText().toString());
                sendToDb(title,quant);
                break;
            case R.id.shoes_page:
                title = "shoes";
                temp = shoesFragment.getEDTValue();
                try {
                    quant = Integer.parseInt(temp);
                }catch (NumberFormatException e){
                    quant = 1;

                    Toast.makeText(this, "Number format Excp:" + temp, Toast.LENGTH_SHORT).show();
                }
                if(quant == 0) quant  = 1;
                //quant = Integer.parseInt(q.getText().toString());
                sendToDb(title,quant);
                break;
            case R.id.utensils_page:
                title = "utensils";
                temp = utensilsFragment.getEDTValue();
                try {
                    quant = Integer.parseInt(temp);
                }catch (NumberFormatException e){
                    quant = 1;

                    Toast.makeText(this, "Number format Excp:" + temp, Toast.LENGTH_SHORT).show();
                }
                if(quant == 0) quant  = 1;
                //quant = Integer.parseInt(q.getText().toString());
                sendToDb(title,quant);
                break;
            case R.id.books_page:
                title = "books";
                temp = booksFragment.getEdTValue();
                try {
                    quant = Integer.parseInt(temp);
                }catch (NumberFormatException e){
                    quant = 1;

                    Toast.makeText(this, "Number format Excp:" + temp, Toast.LENGTH_SHORT).show();
                }
                if(quant == 0) quant  = 1;
                sendToDb(title,quant);
                //quant = Integer.parseInt(q.getText().toString());

                break;
            case R.id.food_page:
                title = "food";
                temp = othersFragment.getEDTValue();
                try {
                    quant = Integer.parseInt(temp);
                }catch (NumberFormatException e){
                    quant = 1;

                    Toast.makeText(this, "Number format Excp:" + temp, Toast.LENGTH_SHORT).show();
                }
                if(quant == 0) quant  = 1;
                sendToDb(title,quant);
                //quant = Integer.parseInt(q.getText().toString());
                break;
            default:
                title = "others";
                quant = 1;
                sendToDb(title,quant);
        }

        Toast.makeText(this, title + ":" + quant, Toast.LENGTH_SHORT).show();

    }

    public  static CartDatabase returnDb(){
        return cartDatabase ;
    }
    private void sendToDb(String title, int quant) {
        Toast.makeText(this, "Sent:" + title, Toast.LENGTH_SHORT).show();
        cartDatabase.addData(title,quant);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    clothesFragment = new ClothesFragment();

                    return clothesFragment;
                case 1:
                    toysFragment = new ToysFragment();
                    return toysFragment;
                case 2:
                    booksFragment = new BooksFragment();
                    return booksFragment;
                case 3:
                    utensilsFragment = new UtensilsFragment();
                    return utensilsFragment;
                case 4:
                    shoesFragment = new ShoesFragment();
                    return shoesFragment;
                case 5:
                    othersFragment = new OthersFragment();
                    return othersFragment;
            }
            return null;
        }


        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "CLOTHES";
                case 1:
                    return "TOYS";
                case 2:
                    return "BOOKS";
                case 3:
                    return "UTENSILS";
                case 4:
                    return "SHOES";
                case 5:
                    return "OTHERS";
            }
            return null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Setting Profile Picture and Name on Resume of Activity
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        ((TextView) hView.findViewById(R.id.display_name_drawer)).setText(user.getDisplayName());
        Log.w("Image", "" + user.getPhotoUrl());

        CircleImageView civ = (CircleImageView) hView.findViewById(R.id.profile_picture_drawer);
        if (user.getPhotoUrl() != null)
            Glide.with(this).load(user.getPhotoUrl()).into(civ);
    }
}