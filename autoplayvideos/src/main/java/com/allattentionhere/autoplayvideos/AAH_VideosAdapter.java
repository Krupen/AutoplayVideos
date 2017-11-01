package com.allattentionhere.autoplayvideos;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class AAH_VideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public AAH_VideosAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AAH_CustomViewHolder(new View(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public void onViewDetachedFromWindow(final RecyclerView.ViewHolder mholder) {
        if (mholder instanceof AAH_CustomViewHolder) {
            AAH_CustomViewHolder holder = (AAH_CustomViewHolder) mholder;
            if (holder != null) {
                holder.getAah_vi().getCustomVideoView().clearAll();
                holder.getAah_vi().getCustomVideoView().invalidate();
            }
        }
        super.onViewDetachedFromWindow(mholder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder mholder) {
        if (mholder instanceof AAH_CustomViewHolder) {
            AAH_CustomViewHolder holder = (AAH_CustomViewHolder) mholder;
            if (holder != null) {
                holder.getAah_vi().getCustomVideoView().clearAll();
                holder.getAah_vi().getCustomVideoView().invalidate();
            }
        }
        super.onViewRecycled(mholder);
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