package com.sd.heinhtetoo.mytestapp.api;


import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.EventResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by hhtet on 3/17/16.
 */
public interface RetrofitService {

  @GET(APIConfig.EVENT_LIST)
  Call<EventResponse> getEventList();

  @POST(APIConfig.EVENT_LIST)
  Call<Event> postEvent(@Body Event e);







}
