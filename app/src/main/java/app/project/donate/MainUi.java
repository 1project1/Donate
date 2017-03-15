package app.project.donate;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import app.project.donate.fragments.BooksFragment;
import app.project.donate.fragments.ClothesFragment;
import app.project.donate.fragments.FoodFragment;
import app.project.donate.fragments.ShoesFragment;
import app.project.donate.fragments.ToysFragment;
import app.project.donate.fragments.UtensilsFragment;
import app.project.donate.model.NgoList;

public class MainUi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private GoogleApiClient mGoogleApiClient;
    public static final String LOGIN_FILE = "LogInFile";

    private TabLayout tabLayout;
    private ViewPager viewPager;

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

        tabLayout.getTabAt(0).setIcon(R.drawable.hamburguer);
        tabLayout.getTabAt(1).setIcon(R.drawable.uniform);
        tabLayout.getTabAt(2).setIcon(R.drawable.train);
        tabLayout.getTabAt(3).setIcon(R.drawable.books);
        tabLayout.getTabAt(4).setIcon(R.drawable.fryingpan);
        tabLayout.getTabAt(5).setIcon(R.drawable.shoes);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        init();
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
            return true;
        }
        else if(id == R.id.action_logout){

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
            // Handle the camera action
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(this, Cart.class));
        } else if (id == R.id.nav_history) {
            startActivity(new Intent(this, History.class));
        } else if (id == R.id.nav_ngo_list) {
            startActivity(new Intent(this, NgoList.class));
        } else if (id == R.id.nav_rate_us) {

        } else if (id == R.id.nav_credits) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager manager){ super(manager); }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    FoodFragment foodFragment = new FoodFragment();
                    return foodFragment;
                case 1:
                    ClothesFragment clothesFragment = new ClothesFragment();
                    return clothesFragment;
                case 2:
                    ToysFragment toysFragment = new ToysFragment();
                    return toysFragment;
                case 3:
                    BooksFragment booksFragment = new BooksFragment();
                    return booksFragment;
                case 4:
                    UtensilsFragment utensilsFragment = new UtensilsFragment();
                    return utensilsFragment;
                case 5:
                    ShoesFragment shoesFragment = new ShoesFragment();
                    return shoesFragment;
            }
            return null;
        }


        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "FOOD ITEMS";
                case 1:
                    return "CLOTHES";
                case 2:
                    return "TOYS";
                case 3:
                    return "BOOKS";
                case 4:
                    return "UTENSILS";
                case 5:
                    return "SHOES";
            }
            return null;
        }
    }

}
