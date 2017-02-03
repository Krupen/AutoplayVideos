package com.allattentionhere.autplayvideoslibrary;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;


public class AAH_VideosAdapter extends RecyclerView.Adapter<AAH_CustomViewHolder> {


    private int list_size;
    private int layout;

    public AAH_VideosAdapter() {
    }

    @Override
    public AAH_CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new AAH_CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AAH_CustomViewHolder holder, int position) {
    }


    @Override
    public void onViewDetachedFromWindow(final AAH_CustomViewHolder holder) {
        if (holder instanceof AAH_CustomViewHolder) {
            Log.d("trace", "onViewDetachedFromWindow AAH_CustomViewHolder: ");
            AAH_CustomViewHolder viewHolder = (AAH_CustomViewHolder) holder;
            viewHolder.getAah_vi().getCustomVIdeoView().clearAll();
            viewHolder.getAah_vi().getCustomVIdeoView().invalidate();
        }
        super.onViewDetachedFromWindow(holder);
    }


    public void renderImageVideo(final AAH_CustomViewHolder vh, String url, Picasso p) {
//        Log.d("trace", "renderCardAtPosition: ");
        if (url != null && !TextUtils.isEmpty(url)) {
            //todo change logic
            String newurl = "";
            if (url.endsWith("mp4")) {
                newurl = url.replace("mp4", "jpg").replace(",q_70", ",q_70,so_0");

            } else {
                newurl = url;
            }
            p.load(newurl).config(Bitmap.Config.RGB_565).into(vh.getAah_vi().getImageView());

            vh.getAah_vi().getCustomVIdeoView().setVisibility(View.GONE);
            vh.getAah_vi().getImageView().setVisibility(View.VISIBLE);
        } else {
            vh.getAah_vi().getImageView().setImageBitmap(null);
        }
    }


    @Override
    public void onViewRecycled(AAH_CustomViewHolder holder) {
        if (holder instanceof AAH_CustomViewHolder) {
            Log.d("trace", "onViewRecycled AAH_CustomViewHolder: ");

            AAH_CustomViewHolder viewHolder = (AAH_CustomViewHolder) holder;
            viewHolder.getAah_vi().getCustomVIdeoView().clearAll();
            viewHolder.getAah_vi().getCustomVIdeoView().invalidate();
        }
        super.onViewRecycled(holder);

    }

    public void setListSize(int list_size) {
        this.list_size = list_size;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getItemCount() {
        return list_size;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


}