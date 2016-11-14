package com.sd.heinhtetoo.mytestapp;

import java.util.ArrayList;

/**
 * Created by HeinHtetOo on 14/11/2016.
 */

public interface EventContract {
    interface eventView {
        void showList(ArrayList<Event> eList);
        void hideList();
    }

    interface eventPresenter {
        void initPresenter();
        void getData();
    }
}
