package com.allattentionhere.autoplayvideos.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allattentionhere.autoplayvideos.Activity.MainActivity;
import com.allattentionhere.autoplayvideos.Model.MyModel;
import com.allattentionhere.autoplayvideos.R;
import com.allattentionhere.autoplayvideos.Viewholder.CustomViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;


public class MyVideosAdapter extends VideosAdapter {

    private List<MyModel> list;
    Picasso p;

    public class MyCustomViewHolder extends CustomViewHolder {

        TextView tv;
        public MyCustomViewHolder(View x) {
            super(x);
            tv= ButterKnife.findById(x,R.id.tv);
        }

    }

    public MyVideosAdapter(List<MyModel> list_urls, Picasso p) {
        this.list = list_urls;
        this.p = p;
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
        renderImageVideo(holder,list.get(position).getUrl(),p);
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