package com.allattentionhere.autoplayvideos.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allattentionhere.autoplayvideos.Model.MyModel;
import com.allattentionhere.autoplayvideos.R;
import com.allattentionhere.autplayvideoslibrary.AAH_CustomViewHolder;
import com.allattentionhere.autplayvideoslibrary.AAH_VideosAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;


public class MyVideosAdapter extends AAH_VideosAdapter {

    private List<MyModel> list;
    Picasso picasso;

    public class MyViewHolder extends AAH_CustomViewHolder {

        TextView tv;

        public MyViewHolder(View x) {
            super(x);
            tv = ButterKnife.findById(x, R.id.tv);
        }

    }

    public MyVideosAdapter(List<MyModel> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
    }

    @Override
    public AAH_CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AAH_CustomViewHolder holder, int position) {
        ((MyViewHolder) holder).tv.setText(list.get(position).getName());

        //todo
        holder.setImageUrl(list.get(position).getImage_url());
        holder.setVideoUrl(list.get(position).getVideo_url());
        //load image into imageview
        if (list.get(position).getImage_url() != null && !list.get(position).getImage_url().isEmpty())
            picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getAAH_ImageView());

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