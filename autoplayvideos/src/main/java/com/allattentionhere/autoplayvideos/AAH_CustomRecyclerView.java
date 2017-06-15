package com.allattentionhere.autoplayvideos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.allattentionhere.autoplayvideos.AAH_Utils.getString;

/**
 * Created by krupenghetiya on 16/12/16.
 */

public class AAH_CustomRecyclerView extends RecyclerView {

    private Activity _act;
    private boolean playOnlyFirstVideo = false;
    private boolean downloadVideos = false;
    private boolean checkForMp4 = true;
    private String downloadPath = Environment.getExternalStorageDirectory() + "/Video";

    public AAH_CustomRecyclerView(Context context) {
        super(context);
    }

    public AAH_CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public AAH_CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void setActivity(Activity _act) {
        this._act = _act;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        addCustomOnScrollListener();

    }

    private void addCustomOnScrollListener() {
        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                List<Thread> threads = new ArrayList<Thread>();
                if (newState == 0) {
                    int firstCompletelyVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                    int lastCompletelyVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastCompletelyVisibleItemPosition();
                    int firstVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                    int lastVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                    if (firstCompletelyVisiblePosition >= 0) {
                        if (playOnlyFirstVideo) {
                            boolean foundFirstVideo = false;
                            for (int i = firstCompletelyVisiblePosition; i <= lastCompletelyVisiblePosition; i++) {
                                final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                                try {
                                    AAH_CustomViewHolder cvh = (AAH_CustomViewHolder) holder;
                                    if (i >= 0 && cvh != null && (cvh.getVideoUrl().endsWith(".mp4") || !checkForMp4)) {
                                        if (!foundFirstVideo) {
                                            foundFirstVideo = true;
                                            if (getString(_act, cvh.getVideoUrl()) != null && new File(getString(_act, cvh.getVideoUrl())).exists()) {
                                                ((AAH_CustomViewHolder) holder).initVideoView(getString(_act, cvh.getVideoUrl()), _act);
                                            } else {
                                                ((AAH_CustomViewHolder) holder).initVideoView(cvh.getVideoUrl(), _act);
                                            }
                                            if (downloadVideos) {
                                                startDownloadInBackground(cvh.getVideoUrl());
                                            }
                                            Thread t = new Thread() {
                                                public void run() {
                                                    ((AAH_CustomViewHolder) holder).playVideo();
                                                }
                                            };
                                            t.start();
                                            threads.add(t);
                                        } else {
                                            ((AAH_CustomViewHolder) holder).pauseVideo();
                                        }
                                    }
                                } catch (Exception e) {

                                }

                            }
                        } else {
                            for (int i = firstCompletelyVisiblePosition; i <= lastCompletelyVisiblePosition; i++) {
                                final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                                try {
                                    AAH_CustomViewHolder cvh = (AAH_CustomViewHolder) holder;
                                    if (i >= 0 && cvh != null && (cvh.getVideoUrl().endsWith(".mp4") || !checkForMp4)) {
                                        if (getString(_act, cvh.getVideoUrl()) != null && new File(getString(_act, cvh.getVideoUrl())).exists()) {
                                            ((AAH_CustomViewHolder) holder).initVideoView(getString(_act, cvh.getVideoUrl()), _act);
                                        } else {
                                            ((AAH_CustomViewHolder) holder).initVideoView(cvh.getVideoUrl(), _act);
                                        }
                                        if (downloadVideos) {
                                            startDownloadInBackground(cvh.getVideoUrl());
                                        }
                                        Thread t = new Thread() {
                                            public void run() {
                                                ((AAH_CustomViewHolder) holder).playVideo();
                                            }
                                        };
                                        t.start();
                                        threads.add(t);
                                    }
                                } catch (Exception e) {

                                }

                            }
                        }
                    }

                    if (firstCompletelyVisiblePosition >= 0 && firstCompletelyVisiblePosition != firstVisiblePosition) {
                        for (int i = firstVisiblePosition; i < firstCompletelyVisiblePosition; i++) {
                            final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                            try {
                                AAH_CustomViewHolder cvh = (AAH_CustomViewHolder) holder;
                                if (i >= 0 && cvh != null && (cvh.getVideoUrl().endsWith(".mp4") || !checkForMp4)) {
                                    ((AAH_CustomViewHolder) holder).pauseVideo();
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                    if (lastCompletelyVisiblePosition >= 0 && lastCompletelyVisiblePosition != lastVisiblePosition) {
                        for (int i = lastVisiblePosition; i > lastCompletelyVisiblePosition; i--) {
                            final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                            try {
                                AAH_CustomViewHolder cvh = (AAH_CustomViewHolder) holder;
                                if (i >= 0 && cvh != null && (cvh.getVideoUrl().endsWith(".mp4") || !checkForMp4)) {
                                    ((AAH_CustomViewHolder) holder).pauseVideo();
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                } else if (threads.size() > 0) {
                    for (Thread t : threads) {
                        t.interrupt();
                        t.stop();
                        t.destroy();
                    }
                    threads.clear();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void setPlayOnlyFirstVideo(boolean playOnlyFirstVideo) {
        this.playOnlyFirstVideo = playOnlyFirstVideo;
    }

    public void startDownloadInBackground(String url) {
        /* Starting Download Service */
        if ((AAH_Utils.getString(_act, url) == null || !(new File(getString(_act, url)).exists())) && url != null && !url.equalsIgnoreCase("null")) {
            Intent intent = new Intent(Intent.ACTION_SYNC, null, _act, AAH_DownloadService.class);
        /* Send optional extras to Download IntentService */
            intent.putExtra("url", url);
            intent.putExtra("path", downloadPath);
            intent.putExtra("requestId", 101);
            _act.startService(intent);
        }
    }

    public void setDownloadVideos(boolean downloadVideos) {
        this.downloadVideos = downloadVideos;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public void preDownload(List<String> urls) {
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(urls);
        urls.clear();
        urls.addAll(hashSet);
        for (int i = 0; i < urls.size(); i++) {
            if ((AAH_Utils.getString(_act, urls.get(i)) == null || !(new File(getString(_act, urls.get(i))).exists())) && urls.get(i) != null && !urls.get(i).equalsIgnoreCase("null")) {
                Intent intent = new Intent(Intent.ACTION_SYNC, null, _act, AAH_DownloadService.class);
                intent.putExtra("url", urls.get(i));
                intent.putExtra("path", downloadPath);
                intent.putExtra("requestId", 101);
                _act.startService(intent);
            }
        }
    }

    public void setCheckForMp4(boolean checkForMp4) {
        this.checkForMp4 = checkForMp4;
    }
}
