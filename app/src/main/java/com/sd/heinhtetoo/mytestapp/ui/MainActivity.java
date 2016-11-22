package com.sd.heinhtetoo.mytestapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.sd.heinhtetoo.mytestapp.contract.EventContract;
import com.sd.heinhtetoo.mytestapp.Presenter.EventPresenter;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModel;
import com.sd.heinhtetoo.mytestapp.recyclerview.EventsAdapter;
import com.sd.heinhtetoo.mytestapp.R;
import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModelImpl;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements EventContract.eventView {
    ArrayList<Event> events;
    private RecyclerView recyclerViewEvent;
    private Button btn_requestData;
    private EventModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEvent = (RecyclerView) findViewById(R.id.view_event);
        btn_requestData = (Button) findViewById(R.id.btn_postData);
        model =EventModelImpl.getInstance();
        EventContract.eventPresenter eventPresenter = new EventPresenter(model, this);

        eventPresenter.initPresenter();

    }

    @Override
    public void showList(RealmList<Event> eList) {
        final EventsAdapter adapter = new EventsAdapter(this, eList);

        recyclerViewEvent.setAdapter(adapter);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvent.setItemAnimator(new SlideInUpAnimator(new LinearInterpolator()));


    }

    @Override
    public void hideList() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
