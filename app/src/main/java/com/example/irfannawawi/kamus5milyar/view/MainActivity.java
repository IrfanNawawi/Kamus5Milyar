package com.example.irfannawawi.kamus5milyar.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.irfannawawi.kamus5milyar.R;
import com.example.irfannawawi.kamus5milyar.adapter.KamusAdapter;
import com.example.irfannawawi.kamus5milyar.db.KamusHelper;
import com.example.irfannawawi.kamus5milyar.model.KamusItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private KamusAdapter kamusAdapter;
    private ArrayList<KamusItem> kamusItems;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private KamusHelper kamusHelper;
    private boolean inggris = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViews();
        loadDataInd();
        searchEngine();
        navigationView.getMenu().getItem(0).setCheckable(true);
    }

    private void initViews() {
        searchView = findViewById(R.id.searchMain);
        searchView.setQueryHint("Search..");
        searchView.setIconified(false);
        recyclerView = findViewById(R.id.rvMain);
        kamusHelper = new KamusHelper(this);
        kamusAdapter = new KamusAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(kamusAdapter);
    }

    private void searchEngine() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (inggris) {
                    loadDataEng(String.valueOf(query));
                } else {
                    loadDataInd(String.valueOf(query));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (inggris) {
                    loadDataEng(String.valueOf(newText));
                } else {
                    loadDataInd(String.valueOf(newText));
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_inggris) {
            inggris = true;
            loadDataEng();
        } else if (id == R.id.nav_indonesia) {
            inggris = false;
            loadDataInd();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadDataEng() {
        loadDataEng("");
    }

    private void loadDataInd() {
        loadDataInd("");
    }

    private void loadDataEng(String cari) {
        kamusHelper.open();
        if (cari.isEmpty()) {
            kamusItems = kamusHelper.getAllDataEng();
        } else {
            kamusItems = kamusHelper.getKamusByKataInggris(cari);
        }
        kamusHelper.close();
        kamusAdapter.addItem(kamusItems);
    }

    private void loadDataInd(String cari) {
        kamusHelper.open();
        if (cari.isEmpty()) {
            kamusItems = kamusHelper.getAllDataInd();
        } else {
            kamusItems = kamusHelper.getKamusByKataIndonesia(cari);
        }
        kamusHelper.close();
        kamusAdapter.addItem(kamusItems);
    }
}
