package com.sd.heinhtetoo.mytestapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;

import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModelImpl;

import java.util.ArrayList;

import io.realm.RealmList;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements EventContract.eventView,NavigationView.OnNavigationItemSelectedListener {
    ArrayList<Event> events;
    private RecyclerView recyclerViewEvent;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu leftMenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

        recyclerViewEvent = (RecyclerView) findViewById(R.id.view_event);


        EventContract.eventPresenter eventPresenter = new EventPresenter(EventModelImpl.getInstance(), this);

        eventPresenter.initPresenter();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public void showList(RealmList<Event> eList) {
        final EventsAdapter adapter = new EventsAdapter(this, eList);

        recyclerViewEvent.setAdapter(adapter);
        recyclerViewEvent.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewEvent.setItemAnimator(new SlideInUpAnimator(new LinearInterpolator()));

    }

    @Override
    public void hideList() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();

        return true;
    }
}
