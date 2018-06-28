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


public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.MyViewHolder> {
    private List<Clubs> clubsList;
    private Context context;
    private LayoutInflater inflater;

    public static final String KEY_TITLE = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "clubimage";
    public static final String KEY_ACTIVITIES = "activities";
    public static final String KEY_TEAM = "team";
    public static final String KEY_CONTACT = "poc";


    public ClubsAdapter(Context context, List<Clubs> clubsList) {

        this.context = context;
        this.clubsList = clubsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.clubs_list, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Clubs clubs = clubsList.get(position);
        //Pass the values of feeds object to Views

        holder.name.setText(clubs.getName());
        Picasso.get().load(clubs.getImage()).placeholder(R.color.Recyclerbg).into(holder.clubimage);



        holder.cardClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent skipIntent = new Intent(v.getContext(), ClubDetails.class);
                skipIntent.putExtra(KEY_TITLE, clubs.getName());
                skipIntent.putExtra(KEY_DESCRIPTION, clubs.getDescription());
                skipIntent.putExtra(KEY_IMAGE, clubs.getImage());
                skipIntent.putExtra(KEY_CONTACT, clubs.getPoc());
                skipIntent.putExtra(KEY_TEAM, clubs.getTeam());
                skipIntent.putExtra(KEY_ACTIVITIES, clubs.getActivities());
                skipIntent.putExtra("Source","ClubsAdapter");
                v.getContext().startActivity(skipIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return clubsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView clubimage;
        public LinearLayout cardClub;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.clubname);

            // Volley's NetworkImageView which will load Image from URL
            clubimage = itemView.findViewById(R.id.clubimage);
            cardClub = itemView.findViewById(R.id.clubcard);

        }


    }

}
