package com.dscviit.vov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {

    public TextView title, content,category,postedby;
    public ImageView newsimageview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        title= findViewById(R.id.newsheader);
        content= findViewById(R.id.newscontent);
        newsimageview= findViewById(R.id.newsthumbnail);
        category=findViewById(R.id.newscategory);
        postedby=findViewById(R.id.newspostedby);


        if(getIntent().getStringExtra("Source").equals("NewsAdapter"))
        {

            Intent intent = getIntent();
            final String newstitle = intent.getStringExtra(MyNewsAdapter.KEY_TITLE);
            final String newscontent = intent.getStringExtra(MyNewsAdapter.KEY_DESCRIPTION);
            final String newscategory = intent.getStringExtra(MyNewsAdapter.KEY_CATEGORY);
            final String newspostedby= intent.getStringExtra(MyNewsAdapter.KEY_POSTEDBY);
            final String newspostedon = intent.getStringExtra(MyNewsAdapter.KEY_POSTEDAT);

            String newsimage = intent.getStringExtra(MyNewsAdapter.KEY_IMAGE);

            actionBar.setTitle(newstitle);


            title.setText(newstitle);
            content.setText(newscontent);
            category.setText(newscategory);
            postedby.setText(newspostedby);
            Picasso.get().load(newsimage).placeholder(R.color.Recyclerbg).into(newsimageview);

        }
        else if(getIntent().getStringExtra("Source").equals("Notification"))
        {

            Intent intent = getIntent();

            final String  nheadline = intent.getStringExtra("Headline");
            final String ncontent= intent.getStringExtra("Content");
            final String ncategory= intent.getStringExtra("Category");
            final String npostedby= intent.getStringExtra("PostedBy");
            final String nposteddate = intent.getStringExtra("PostedDate");
            String nimage = intent.getStringExtra("Image");

            actionBar.setTitle(nheadline);

            title.setText(nheadline);
            content.setText(ncontent);
            category.setText(ncategory);
            postedby.setText(npostedby);
            Picasso.get().load(nimage).placeholder(R.color.Recyclerbg).into(newsimageview);


        }





    }


}
