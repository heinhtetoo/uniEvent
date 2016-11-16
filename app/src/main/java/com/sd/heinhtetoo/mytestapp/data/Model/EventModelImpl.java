package com.sd.heinhtetoo.mytestapp.data.Model;

import com.sd.heinhtetoo.mytestapp.api.RetrofitClient;
import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.EventResponse;

import java.util.ArrayList;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kaungkhantthu on 11/16/16.
 */

public class EventModelImpl implements EventModel {

    private static EventModelImpl eventModel;

    private EventModelImpl() {

    }

    public static EventModel getInstance() {
        if (eventModel == null) {
            eventModel = new EventModelImpl();

        }
        return eventModel;
    }

    @Override
    public void saveEvent() {

    }

    @Override
    public ArrayList<Event> getEvent() {
        return null;
    }

    @Override
    public void getEvent(final Callback c) {
        RetrofitClient.getInstance().getService().getEventList().enqueue(new retrofit2.Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        c.onSuccess(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                c.onError();
            }
        });

    }

    public interface Callback {
        void onSuccess(RealmList<Event> events);

        void onError();
    }
}
