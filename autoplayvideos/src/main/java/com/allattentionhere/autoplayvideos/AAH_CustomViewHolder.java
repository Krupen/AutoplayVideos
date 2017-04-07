package com.allattentionhere.autoplayvideos;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import java.util.concurrent.Callable;


/**
 * Created by krupenghetiya on 03/02/17.
 */

public class AAH_CustomViewHolder extends RecyclerView.ViewHolder {
    private AAH_VideoImage aah_vi;
    private String imageUrl;
    private String videoUrl;

    public AAH_CustomViewHolder(View x) {
        super(x);

        aah_vi = (AAH_VideoImage) x.findViewWithTag("aah_vi");
    }

    public void playVideo() {
        this.aah_vi.getCustomVideoView().startVideo();
    }

    private void hideImagePlaceHolder() {
        this.aah_vi.getImageView().setVisibility(View.GONE);
    }

    public void initVideoView(String url, Activity _act) {
        this.aah_vi.getCustomVideoView().setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(url);
        this.aah_vi.getCustomVideoView().setSource(uri);
        this.aah_vi.getCustomVideoView().setLooping(true);
        this.aah_vi.getCustomVideoView().set_act(_act);
        this.aah_vi.getCustomVideoView().setMyFuncIn(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                hideImagePlaceHolder();
                return null;
            }
        });
    }
    public void pauseVideo() {
        this.aah_vi.getCustomVideoView().pauseVideo();
    }

    public void muteVideo(){
        this.aah_vi.getCustomVideoView().muteVideo();
    }

    public void unmuteVideo(){
        this.aah_vi.getCustomVideoView().unmuteVideo();
    }

    public AAH_VideoImage getAah_vi() {
        return aah_vi;
    }

    public ImageView getAAH_ImageView(){
        return aah_vi.getImageView();
    }

    public String getImageUrl() {
        return imageUrl+"";
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        this.aah_vi.getImageView().setVisibility(View.VISIBLE);
        this.aah_vi.getCustomVideoView().setVisibility(View.GONE);
    }

    public void setAah_vi(AAH_VideoImage aah_vi) {
        this.aah_vi = aah_vi;
    }

    public String getVideoUrl() {

        return videoUrl+"";
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}