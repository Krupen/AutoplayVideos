package com.allattentionhere.autplayvideoslibrary;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.concurrent.Callable;


/**
 * Created by krupenghetiya on 03/02/17.
 */

public class AAH_CustomViewHolder extends RecyclerView.ViewHolder {
    private AAH_VideoImage aah_vi;
    private String url;

    public AAH_CustomViewHolder(View x) {
        super(x);

        aah_vi = (AAH_VideoImage) x.findViewWithTag("aah_vi");
    }

    public void playVideo() {
        this.aah_vi.getCustomVIdeoView().startVideo();
    }

    public void hideImagePlaceHolder() {
        this.aah_vi.getImageView().setVisibility(View.GONE);
    }

    public void initVideoView(String url, Activity _act) {
        this.aah_vi.getCustomVIdeoView().setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(url);
        this.aah_vi.getCustomVIdeoView().setSource(uri);
        this.aah_vi.getCustomVIdeoView().setLooping(true);
        this.aah_vi.getCustomVIdeoView().set_act(_act);
        this.aah_vi.getCustomVIdeoView().setMyFuncIn(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                hideImagePlaceHolder();
                return null;
            }
        });
    }
    public void pauseVideo() {
        this.aah_vi.getCustomVIdeoView().pauseVideo();
    }

    public AAH_VideoImage getAah_vi() {
        return aah_vi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}