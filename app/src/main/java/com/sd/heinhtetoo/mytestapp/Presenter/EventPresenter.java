package com.sd.heinhtetoo.mytestapp.Presenter;

import com.sd.heinhtetoo.mytestapp.contract.EventContract;
import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModel;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModelImpl;

import io.realm.RealmList;

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
         eModel.getEvent(new EventModelImpl.Callback() {
            @Override
            public void onSuccess(RealmList<Event> events) {
                eView.showList(events);
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public void getData() {

    }
}
