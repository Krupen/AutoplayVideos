package com.allattentionhere.autoplayvideos;

import android.support.v7.widget.RecyclerView;
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
        if (holder instanceof AAH_CustomViewHolder) {
//            Log.d("trace", "onViewDetachedFromWindow AAH_CustomViewHolder: ");
            AAH_CustomViewHolder viewHolder = (AAH_CustomViewHolder) holder;
            viewHolder.getAah_vi().getCustomVideoView().clearAll();
            viewHolder.getAah_vi().getCustomVideoView().invalidate();
        }
        super.onViewDetachedFromWindow(holder);
    }


//    public void renderImageVideo(final AAH_CustomViewHolder vh, Picasso p) {
//        Log.d("trace", "renderCardAtPosition: ");
//        if (vh.getImageUrl() != null && !TextUtils.isEmpty(vh.getImageUrl())) {
//            String newurl = "";
//            if (url.endsWith("mp4")) {
//                newurl = url.replace("mp4", "jpg").replace(",q_70", ",q_70,so_0");
//            } else {
//                newurl = vh.getImageUrl();
//            }
//            p.load(vh.getImageUrl()).config(Bitmap.Config.RGB_565).into(vh.getAah_vi().getImageView());
//
//            vh.getAah_vi().getCustomVideoView().setVisibility(View.GONE);
//            vh.getAah_vi().getImageView().setVisibility(View.VISIBLE);
//        } else {
//            vh.getAah_vi().getImageView().setImageBitmap(null);
//        }
//    }


    @Override
    public void onViewRecycled(AAH_CustomViewHolder holder) {
        if (holder instanceof AAH_CustomViewHolder) {
//            Log.d("trace", "onViewRecycled AAH_CustomViewHolder: ");
            AAH_CustomViewHolder viewHolder = (AAH_CustomViewHolder) holder;
            viewHolder.getAah_vi().getCustomVideoView().clearAll();
            viewHolder.getAah_vi().getCustomVideoView().invalidate();
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