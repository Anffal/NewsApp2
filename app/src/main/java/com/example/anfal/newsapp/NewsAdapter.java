package com.example.anfal.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anfal on 8/26/17.
 */

public class NewsAdapter extends BaseAdapter {

    private Context context;

    private List<News> newsList = new ArrayList<News>();
    private LayoutInflater inflater;

    public NewsAdapter(Context context, List<News> newsList) {
        this.newsList = newsList;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        final News news = (News) getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView TitleTextView = convertView.findViewById(R.id.title);
        TitleTextView.setText(news.getTitle());
        TextView sectionTextView = convertView.findViewById(R.id.section);
        sectionTextView.setText(news.getSection());
        TextView dateTextView = convertView.findViewById(R.id.date);
        dateTextView.setText(news.getWebPublicationDate());

        LinearLayout layout = convertView.findViewById(R.id.item);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getWebUrl()));
                context.startActivity(browserIntent);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setNews(List<News> data) {
        newsList.addAll(data);
        notifyDataSetChanged();
    }
}
