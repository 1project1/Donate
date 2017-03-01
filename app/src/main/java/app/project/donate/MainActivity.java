package app.project.donate;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private DonateCardAdapter donateCardAdapter;
    private List<DonationCard> donationCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        donationCardList = new ArrayList<>();
        donateCardAdapter = new DonateCardAdapter(this, donationCardList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(donateCardAdapter);



        getDonations();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    private void getDonations(){
        int cardBackground[] = new int[]{R.drawable.clothes, R.drawable.books, R.drawable.food, R.drawable.utensils, R.drawable.toys};

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

    @Override
    public void onBackPressed() {
        DrawerLayout layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (layout.isDrawerOpen(GravityCompat.START))
            layout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menu){
        int id = menu.getItemId();

        if (id == R.id.history){

        } else if (id == R.id.notifications){

        } else if (id == R.id.aboutUs){

        } else if (id == R.id.ngoList){

        } else if (id == R.id.cart){

        } else if (id == R.id.home){

        }

        DrawerLayout layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        layout.closeDrawer(GravityCompat.START);
        return true;
    }
}
