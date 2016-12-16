package com.allattentionhere.autoplayvideos.Adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.allattentionhere.autoplayvideos.Activity.MainActivity;
import com.allattentionhere.autoplayvideos.Customview.CustomVideoView;
import com.allattentionhere.autoplayvideos.R;

import java.util.List;
import java.util.concurrent.Callable;

import butterknife.ButterKnife;




public class VideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list_urls;
    Activity _activity;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private CustomVideoView cvv;

        public CustomViewHolder(View x) {
            super(x);
            image = ButterKnife.findById(x, R.id.image);
            cvv = ButterKnife.findById(x, R.id.cvv);
        }

        public void playVideo() {
            this.cvv.startVideo();
        }

        public void hideImagePlaceHolder() {
            this.image.setVisibility(View.GONE);
        }

        public void initVideoView(String url) {
            this.cvv.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(url);
            this.cvv.setSource(uri);
            this.cvv.setLooping(true);
            this.cvv.set_act(_activity);
            this.cvv.setMyFuncIn(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    hideImagePlaceHolder();
                    return null;
                }
            });
        }
        public void pauseVideo() {
            this.cvv.pauseVideo();
        }

    }


    public VideosAdapter(List<String> list_urls, Activity _activity) {
        this.list_urls = list_urls;
        this._activity = _activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card, parent, false);
        return new CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderCardAtPosition( (CustomViewHolder) holder,list_urls.get(position));
    }



    @Override
    public void onViewDetachedFromWindow(final RecyclerView.ViewHolder holder) {
        if (holder instanceof CustomViewHolder) {
            CustomViewHolder viewHolder = (CustomViewHolder) holder;
            viewHolder.cvv.clearAll();
            viewHolder.cvv.invalidate();
        }
        super.onViewDetachedFromWindow(holder);
    }



    private void renderCardAtPosition(final CustomViewHolder vh, String url) {
        if (url != null && !TextUtils.isEmpty(url)) {
            ((MainActivity) _activity).loadImageWithPicasso(url, vh.image);
            vh.cvv.setVisibility(View.GONE);
            vh.image.setVisibility(View.VISIBLE);
        } else {
            vh.image.setImageBitmap(null);
        }


    }


    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof CustomViewHolder) {
            CustomViewHolder viewHolder = (CustomViewHolder) holder;
            viewHolder.cvv.clearAll();
            viewHolder.cvv.invalidate();
        }
        super.onViewRecycled(holder);

    }

    @Override
    public int getItemCount() {
        return list_urls.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }



}