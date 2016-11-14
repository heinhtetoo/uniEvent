package com.sd.heinhtetoo.mytestapp;

import java.util.ArrayList;

/**
 * Created by HeinHtetOo on 14/11/2016.
 */

public class EventPresenter implements EventContract.eventPresenter {
    private final EventModel eModel;
    private final EventContract.eventView eView;

    public EventPresenter(EventModel eventModel, EventContract.eventView eventView) {
        this.eModel = eventModel;
        this.eView = eventView;
    }

    @Override
    public void initPresenter() {
        ArrayList<Event> eventArrayList = eModel.getEvent();
        eView.showList(eventArrayList);
    }

    @Override
    public void getData() {

    }
}
