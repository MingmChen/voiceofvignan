package com.dscviit.vov;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {
    private List<Events> eventsList;
    private Context context;
    private LayoutInflater inflater;

    public static final String KEY_TITLE = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "eventimage";
    public static final String KEY_DATE= "date";
    public static final String KEY_TIME="time";
    public static final String KEY_VENUE = "venue";
    public static final String KEY_CN = "contactname";
    public static final String KEY_CD = "contactdesc";
    public static final String KEY_CE = "contactentity";
    public static final String KEY_REG = "reglink";
    public static final String KEY_CATEGORY="category";
    public static final String KEY_POSTEDAT="postedat";
    public static final String KEY_RELEVANCE="relevance";
    public static final String KEY_REGBTNDESC="regbtndesc";




    public EventsAdapter(Context context, List<Events> eventsList) {

        this.context = context;
        this.eventsList = eventsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.events_list, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Events events = eventsList.get(position);
        //Pass the values of feeds object to Views

        Picasso.get().load(events.getImage()).placeholder(R.color.Recyclerbg).into(holder.eventimage);
        holder.name.setText(events.getName());
        holder.category.setText(events.getCategory());
        holder.relevance.setText(events.getRelevance());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent skipIntent = new Intent(v.getContext(), EventDetails.class);
                skipIntent.putExtra(KEY_TITLE, events.getName());
                skipIntent.putExtra(KEY_DESCRIPTION, events.getDescription());
                skipIntent.putExtra(KEY_IMAGE, events.getImage());
                skipIntent.putExtra(KEY_CN, events.getCn());
                skipIntent.putExtra(KEY_CD, events.getCd());
                skipIntent.putExtra(KEY_CE, events.getCe());
                skipIntent.putExtra(KEY_REG, events.getReg());
                skipIntent.putExtra(KEY_VENUE, events.getVenue());
                skipIntent.putExtra(KEY_DATE, events.getDate());
                skipIntent.putExtra(KEY_CATEGORY,events.getCategory());
                skipIntent.putExtra(KEY_POSTEDAT,events.getPostedAt());
                skipIntent.putExtra(KEY_TIME,events.getTime());
                skipIntent.putExtra(KEY_RELEVANCE,events.getRelevance());
                skipIntent.putExtra(KEY_REGBTNDESC,events.getRegbtndesc());
                skipIntent.putExtra("Source","EventsAdapter");
                v.getContext().startActivity(skipIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,category,relevance;
        public ImageView eventimage;
        LinearLayout cardView;



        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventname);
            category=itemView.findViewById(R.id.eventCategory);
            relevance=itemView.findViewById(R.id.eventRelevance);

            eventimage = itemView.findViewById(R.id.eventimage);
            cardView= itemView.findViewById(R.id.eventcard);


        }


    }

}
