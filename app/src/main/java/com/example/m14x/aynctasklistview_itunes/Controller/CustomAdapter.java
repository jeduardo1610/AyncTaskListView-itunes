package com.example.m14x.aynctasklistview_itunes.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.m14x.aynctasklistview_itunes.Model.Pojo;
import com.example.m14x.aynctasklistview_itunes.R;

import java.util.List;

/**
 * Created by m14x on 04/14/2016.
 */
public class CustomAdapter extends ArrayAdapter<Pojo> {
    public CustomAdapter(Context context, int resource) {
        super(context, resource);
    }
    public CustomAdapter(Context context, int resource, List<Pojo> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){

            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.item,null);
        }
        Pojo item = getItem(position);
        if(item != null){
            TextView artistName = (TextView) view.findViewById(R.id.artistTextView);
            TextView trackName = (TextView) view.findViewById(R.id.trackTextView);
            TextView collectionName = (TextView) view.findViewById(R.id.collectionTextView);

            if(artistName != null){
                artistName.setText(item.getArtistName());
            }
            if(trackName != null){
                trackName.setText(item.getTrackName());
            }
            if(collectionName != null){
                collectionName.setText(item.getCollectionName());
            }
        }

        return view;
    }
}
