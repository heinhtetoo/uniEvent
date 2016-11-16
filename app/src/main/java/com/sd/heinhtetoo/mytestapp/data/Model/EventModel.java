package com.sd.heinhtetoo.mytestapp.data.Model;

import com.sd.heinhtetoo.mytestapp.data.Event;

import java.util.ArrayList;

/**
 * Created by HeinHtetOo on 14/11/2016.
 */

public interface EventModel {
    void saveEvent();

    ArrayList<Event> getEvent();

    void getEvent(EventModelImpl.Callback c);
}
