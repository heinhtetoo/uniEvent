package com.sd.heinhtetoo.mytestapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sd.heinhtetoo.mytestapp.data.Event;

import java.util.List;

/**
 * Created by HeinHtetOo on 05/11/2016.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Event> events;
    private Context context;

    public EventsAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View eventsView = inflater.inflate(R.layout.item_event, parent, false);

        final ViewHolder viewHolder = new ViewHolder(eventsView, new ViewHolder.MyViewHolderClicks() {
            @Override
            public void onExpand(View view, int position) {

                ImageView img_expand = (ImageView) view.findViewById(R.id.img_event_expand);

                if (img_expand.getTag() == null || img_expand.getTag().equals("ic_expand")) {
                    view.findViewById(R.id.txt_event_place_title).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.txt_event_place).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.txt_event_description_title).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.txt_event_description).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.txt_event_tags_title).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.txt_event_tags_body).setVisibility(View.VISIBLE);

                    img_expand.setImageResource(R.drawable.ic_unexpand);
                    img_expand.setTag("ic_unexpand");
                } else {
                    view.findViewById(R.id.txt_event_place_title).setVisibility(View.GONE);
                    view.findViewById(R.id.txt_event_place).setVisibility(View.GONE);
                    view.findViewById(R.id.txt_event_description_title).setVisibility(View.GONE);
                    view.findViewById(R.id.txt_event_description).setVisibility(View.GONE);
                    view.findViewById(R.id.txt_event_tags_title).setVisibility(View.GONE);
                    view.findViewById(R.id.txt_event_tags_body).setVisibility(View.GONE);

                    img_expand.setImageResource(R.drawable.ic_expand);
                    img_expand.setTag("ic_expand");
                }
            }

            @Override
            public void onStar(View view, int position) {
                ImageView img_star = (ImageView) view.findViewById(R.id.img_event_starred);

                if (img_star.getTag() == null || img_star.getTag().equals("ic_unstarred")) {
                    img_star.setImageResource(R.drawable.ic_starred);
                    img_star.setTag("ic_starred");
                    Toast.makeText(context, "Event saved.", Toast.LENGTH_SHORT).show();
                } else {
                    img_star.setImageResource(R.drawable.ic_unstarred);
                    img_star.setTag("ic_unstarred");
                    Toast.makeText(context, "Event unsaved.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, time, date, place, description, tags;
        public ImageView star, expand;
        public MyViewHolderClicks mListener;

        public ViewHolder(final View itemView, MyViewHolderClicks listener) {
            super(itemView);

            mListener = listener;
            title = (TextView) itemView.findViewById(R.id.txt_title);
            time = (TextView) itemView.findViewById(R.id.txt_event_time);
            date = (TextView) itemView.findViewById(R.id.txt_event_date);
            place = (TextView) itemView.findViewById(R.id.txt_event_place);
            description = (TextView) itemView.findViewById(R.id.txt_event_description);
            tags = (TextView) itemView.findViewById(R.id.txt_event_tags_body);
            star = (ImageView) itemView.findViewById(R.id.img_event_starred);
            expand = (ImageView) itemView.findViewById(R.id.img_event_expand);

            itemView.findViewById(R.id.bodyLayout).setOnClickListener(this);
            star.setOnClickListener(this);
            expand.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.bodyLayout) {
                mListener.onExpand(v, getAdapterPosition());
            } else if (v.getId() == R.id.img_event_starred) {
                mListener.onStar(v, getAdapterPosition());
            } else if (v.getId() == R.id.img_event_expand) {
                mListener.onExpand(itemView, getAdapterPosition());
            }
        }

        public interface MyViewHolderClicks {
            void onExpand(View view, int position);

            void onStar(View view, int position);
        }
    }

    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder viewHolder, int position) {
        Event event = events.get(position);

        viewHolder.title.setText(event.getTitle());
        viewHolder.time.setText(event.getTime());
        viewHolder.date.setText(event.getDate());
        viewHolder.place.setText(event.getPlace());
        viewHolder.description.setText(event.getDescription());
        viewHolder.tags.setText(event.getTags());
    }
}
