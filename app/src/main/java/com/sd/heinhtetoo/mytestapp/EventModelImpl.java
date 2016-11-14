package com.sd.heinhtetoo.mytestapp;

import java.util.ArrayList;

/**
 * Created by HeinHtetOo on 14/11/2016.
 */

public class EventModelImpl implements EventModel {

    private static EventModel eventModel;
    private static ArrayList<Event> eventlist;

    private EventModelImpl() {
    }

    public static EventModel getInstance() {
        if(eventModel == null){
            eventModel = new EventModelImpl();
            eventlist = createSampleEventList(13);
        }
        return eventModel;
    }

    @Override
    public void saveEvent() {

    }

    @Override
    public ArrayList<Event> getEvent() {
        return eventlist;

    }
    private static ArrayList<Event> createSampleEventList(int num) {
        ArrayList<Event> events = new ArrayList<>();
        String [] title = {"YU 96 Anniversary", "Independence Day Seminar", "YU Valentine", "Study in UK", "Google Dev Seminar","Sample Event Title","Sample Event Title","Sample Event Title","Sample Event Title","Sample Event Title","Sample Event Title","Sample Event Title","Sample Event Title"};
        String [] time = {"08:30", "14:30", "10:00", "15:30", "09:30","hh:mm","hh:mm","hh:mm","hh:mm","hh:mm","hh:mm","hh:mm","hh:mm"};
        String [] date = {"01/12/16", "04/01/17", "14/02/17", "19/02/17", "24/02/17","dd/mm/yy","dd/mm/yy","dd/mm/yy","dd/mm/yy","dd/mm/yy","dd/mm/yy","dd/mm/yy","dd/mm/yy"};
        String [] place = {"University Campus", "Art Hall", "Art Hall Compound", "Art Hall", "ULB","sample Place","sample Place","sample Place","sample Place","sample Place","sample Place","sample Place","sample Place"};
        String [] description = {"You can enjoy YU Annual Science Fair, Entertainment Shows & Contests, Fun Fair and many Seminars.",
                "This event is for those who want to get and share knowledge about our national independence.",
                "Nothing to do here for FA",
                "For those who are interested in studying abroad.",
                "This event is for ComputerScience Students",
                "sample description","sample description","sample description","sample description","sample description","sample description","sample description","sample description"};
        String [] tags = {"All YU Students, Special Event, ScienceFair, FunFair, Seminar", "All Major", "Couples in YU", "Chemistry, Physics, Botany", "Computer Science","sample tags","sample tags","sample tags","sample tags","sample tags","sample tags","sample tags","sample tags"};

        for (int i = 0; i < num; i++) {
            events.add(new Event(title[i], time[i], date[i], place[i], description[i], tags[i], false));
        }

        return events;
    }
}
