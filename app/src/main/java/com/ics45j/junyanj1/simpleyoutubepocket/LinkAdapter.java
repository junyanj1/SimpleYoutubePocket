package com.ics45j.junyanj1.simpleyoutubepocket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LinkAdapter extends ArrayAdapter<LinkClip> {
    private Context mContext;
    private List<LinkClip> lcList = new ArrayList<LinkClip>();

    public LinkAdapter(Context context, ArrayList<LinkClip> list){
        super(context,0, list);
        mContext = context;
        lcList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.linkclip_view, parent, false);

        LinkClip currentLC = lcList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_youtuberName);
        name.setText(currentLC.getYoutuberName());

        TextView link = (TextView) listItem.findViewById(R.id.textView_linkToVideo);
        link.setText(currentLC.getLinkToVideo());

        TextView time = (TextView) listItem.findViewById(R.id.textView_addingTime);
        time.setText(currentLC.getAddingTime());

        return listItem;
    }
}
