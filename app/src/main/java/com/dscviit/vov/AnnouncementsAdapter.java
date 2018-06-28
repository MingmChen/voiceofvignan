package com.dscviit.vov;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.MyViewHolder> {
    private List<Announcements> announcementsList;
    private Context context;
    private LayoutInflater inflater;


    public AnnouncementsAdapter(Context context, List<Announcements> announcementsList) {

        this.context = context;
        this.announcementsList = announcementsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.announcements_list, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Announcements announcements = announcementsList.get(position);
        //Pass the values of feeds object to Views

        Picasso.get().load(announcements.getImageurl()).placeholder(R.color.Recyclerbg).into(holder.announcementimage);
        holder.description.setText(announcements.getDescription());
        holder.postedat.setText(announcements.getPostedDate());
        holder.relevance.setText(announcements.getRelevance());
        holder.postedby.setText(announcements.getPostedBy());

    }

    @Override
    public int getItemCount() {
        return announcementsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView description, postedat, postedby,relevance;
        public ImageView announcementimage;


        public MyViewHolder(View itemView) {
            super(itemView);
            announcementimage = itemView.findViewById(R.id.announcementimage);
            description = itemView.findViewById(R.id.announcementTitle);
            postedat = itemView.findViewById(R.id.AnnouncementDate);
            relevance = itemView.findViewById(R.id.AnnouncementRelevance);
            postedby = itemView.findViewById(R.id.announcementPostedBy);


        }


    }

}
