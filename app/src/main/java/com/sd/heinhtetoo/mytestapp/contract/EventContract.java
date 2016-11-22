package com.sd.heinhtetoo.mytestapp.contract;

import com.sd.heinhtetoo.mytestapp.data.Event;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by HeinHtetOo on 14/11/2016.
 */

public interface EventContract {
    interface eventView {
        void showList(RealmList<Event> eList);
        void hideList();
    }

    interface eventPresenter {
        void initPresenter();
        void getData();
    }
}
