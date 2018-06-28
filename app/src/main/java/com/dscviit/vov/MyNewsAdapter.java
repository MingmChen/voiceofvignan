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


public class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.MyViewHolder> {
    private List<News> newsList;
    private Context context;
    private LayoutInflater inflater;

    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "content";
    public static final String KEY_IMAGE = "newsimage";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_POSTEDBY="postedby";
    public static final String KEY_POSTEDAT = "postedat";



    public MyNewsAdapter(Context context, List<News> newsList) {

        this.context = context;
        this.newsList = newsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.news_list, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final News news = newsList.get(position);
        //Pass the values of feeds object to Views


        Picasso.get().load(news.getImage()).placeholder(R.color.Recyclerbg).into(holder.newsimage);
        holder.title.setText(news.getHeadline());
        holder.category.setText(news.getCategory());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent skipIntent = new Intent(v.getContext(), NewsDetails.class);
                skipIntent.putExtra(KEY_TITLE, news.getHeadline());
                skipIntent.putExtra(KEY_DESCRIPTION, news.getContent());
                skipIntent.putExtra(KEY_IMAGE, news.getImage());
                skipIntent.putExtra(KEY_CATEGORY,news.getCategory());
                skipIntent.putExtra(KEY_POSTEDAT,news.getPostedAt());
                skipIntent.putExtra(KEY_POSTEDBY,news.getPostedBy());
                skipIntent.putExtra("Source","NewsAdapter");
                v.getContext().startActivity(skipIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, content,category;
        public ImageView newsimage;
        public LinearLayout cardView;



        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newstitle);
            category=itemView.findViewById(R.id.tv_news_category);
            newsimage = itemView.findViewById(R.id.newsimage);
            cardView= itemView.findViewById(R.id.newscard);


        }


    }

}
