package com.allattentionhere.autoplayvideos;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class AAH_VideosAdapter extends RecyclerView.Adapter<AAH_CustomViewHolder> {

    public AAH_VideosAdapter() {
    }

    @Override
    public AAH_CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AAH_CustomViewHolder(new View(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(AAH_CustomViewHolder holder, int position) {
    }

    @Override
    public void onViewDetachedFromWindow(final AAH_CustomViewHolder holder) {
        if (holder != null && holder.getAah_vi()!=null) {
            holder.getAah_vi().getCustomVideoView().clearAll();
            holder.getAah_vi().getCustomVideoView().invalidate();
        }
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(AAH_CustomViewHolder holder) {
        if (holder != null && holder.getAah_vi()!=null) {
            holder.getAah_vi().getCustomVideoView().clearAll();
            holder.getAah_vi().getCustomVideoView().invalidate();
        }
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }



}