package app.project.donate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.project.donate.controllers.DonateCardAdapter;
import app.project.donate.model.DonationCard;

public class MainUI extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private DonateCardAdapter donateCardAdapter;
    private List<DonationCard> donationCardList;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//TODO fix lag due to init() asynctask not working

        //new backTask().execute();
        init();
    }
//TODO for fixing lags
    public void init(){
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        donationCardList = new ArrayList<>();
        donateCardAdapter = new DonateCardAdapter(this, donationCardList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(donateCardAdapter);

        getDonations();
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.notifications) {

        } else if (id == R.id.cart) {

        } else if (id == R.id.history) {

        } else if (id == R.id.aboutUs) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void getDonations(){
        int cardBackground[] = new int[]{R.mipmap.clothes, R.mipmap.books, R.mipmap.food, R.mipmap.food, R.mipmap.clothes};

        DonationCard card = new DonationCard("CLOTHES DONATION", cardBackground[0]);
        donationCardList.add(card);

        card = new DonationCard("BOOKS DONATION", cardBackground[1]);
        donationCardList.add(card);

        card = new DonationCard("FOOD ITEM DONATION", cardBackground[2]);
        donationCardList.add(card);

        card = new DonationCard("UTENSILS DONATION", cardBackground[3]);
        donationCardList.add(card);

        card = new DonationCard("TOYS DONATION", cardBackground[4]);
        donationCardList.add(card);

        donateCardAdapter.notifyDataSetChanged();
    }

    public class backTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            init();
            return  null;
        }
    }
}
