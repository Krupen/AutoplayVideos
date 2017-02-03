package com.allattentionhere.autoplayvideos.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allattentionhere.autoplayvideos.Model.MyModel;
import com.allattentionhere.autoplayvideos.R;
import com.allattentionhere.autoplayvideos.Viewholder.CustomViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;


public class MyVideosAdapter extends VideosAdapter {

    private List<MyModel> list;
    Picasso picasso;

    public class MyCustomViewHolder extends CustomViewHolder {

        TextView tv;
        public MyCustomViewHolder(View x) {
            super(x);
            tv= ButterKnife.findById(x,R.id.tv);
        }

    }

    public MyVideosAdapter(List<MyModel> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
        //todo
        setList_size(list.size());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card, parent, false);
        return new MyCustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ((MyCustomViewHolder) holder).tv.setText(list.get(position).getName());
        //todo
        ((MyCustomViewHolder) holder).setUrl(list.get(position).getUrl());
        renderImageVideo(holder,list.get(position).getUrl(), picasso);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }



}