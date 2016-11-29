package com.sd.heinhtetoo.mytestapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModelImpl;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by Administrator's user on 26-Nov-16.
 */

public class EventListFragment extends Fragment implements EventContract.eventView, View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {

    EventContract.eventPresenter eventPresenter;

    ArrayList<Event> events;
    private RecyclerView recyclerViewEvent;

    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventPresenter = new EventPresenter(EventModelImpl.getInstance(), this);
        eventPresenter.initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        recyclerViewEvent = (RecyclerView) view.findViewById(R.id.view_event);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_layout);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add_event);
        fab.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void showList(RealmList<Event> eList) {
        final EventsAdapter adapter = new EventsAdapter(UniEventApp.getContext(), eList);

        recyclerViewEvent.setAdapter(adapter);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void hideList() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_add_event) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            AddEventDialogFragment addEventDialogFragment = new AddEventDialogFragment();
            addEventDialogFragment.show(fragmentManager,"Add Event");
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                eventPresenter.initPresenter();
                swipeRefreshLayout.setRefreshing(false);
;            }
        }, 3000);
    }
}
