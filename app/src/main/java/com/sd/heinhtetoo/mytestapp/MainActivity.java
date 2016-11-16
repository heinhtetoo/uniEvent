package com.sd.heinhtetoo.mytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.LinearInterpolator;

import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModelImpl;

import java.util.ArrayList;

import io.realm.RealmList;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements EventContract.eventView {
    ArrayList<Event> events;
    private RecyclerView recyclerViewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEvent = (RecyclerView) findViewById(R.id.view_event);


        EventContract.eventPresenter eventPresenter = new EventPresenter(EventModelImpl.getInstance(), this);

        eventPresenter.initPresenter();


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
}
