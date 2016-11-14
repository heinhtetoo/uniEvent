package com.sd.heinhtetoo.mytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static android.R.attr.description;
import static android.R.attr.publicKey;
import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity implements EventContract.eventView {
    ArrayList<Event> events;
    private RecyclerView recyclerViewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEvent = (RecyclerView) findViewById(R.id.view_event);

        //events = Event.createSampleEventList(13);

        //final EventsAdapter adapter = new EventsAdapter(this, eList);

        //recyclerViewEvent.setAdapter(adapter);

        //recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));

        /*RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerViewEvent.addItemDecoration(itemDecoration);*/

        EventContract.eventPresenter eventPresenter = new EventPresenter(EventModelImpl.getInstance(), this);

        eventPresenter.initPresenter();


    }

    @Override
    public void showList(ArrayList<Event> eList) {
        final EventsAdapter adapter = new EventsAdapter(this, eList);

        recyclerViewEvent.setAdapter(adapter);
        recyclerViewEvent.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewEvent.setItemAnimator(new SlideInUpAnimator(new LinearInterpolator()));

    }

    @Override
    public void hideList() {

    }
}
