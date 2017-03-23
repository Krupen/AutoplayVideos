package com.allattentionhere.autoplayvideos;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupenghetiya on 16/12/16.
 */

public class AAH_CustomRecyclerView extends RecyclerView {

    private Activity _act;

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
                        for (int i = firstCompletelyVisiblePosition; i <= lastCompletelyVisiblePosition; i++) {
                            final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                            try {
                                AAH_CustomViewHolder cvh = (AAH_CustomViewHolder)holder;
                                if (i >= 0 && cvh!=null && cvh.getVideoUrl().endsWith(".mp4")) {
                                    ((AAH_CustomViewHolder) holder).initVideoView(cvh.getVideoUrl(),_act);
                                    Thread t = new Thread() {
                                        public void run() {
                                            ((AAH_CustomViewHolder) holder).playVideo();
                                        }
                                    };
                                    t.start();
                                    threads.add(t);
                                }
                            }catch (Exception e){

                            }

                        }
                    }

                    if (firstCompletelyVisiblePosition >= 0 && firstCompletelyVisiblePosition != firstVisiblePosition) {
                        for (int i = firstVisiblePosition; i < firstCompletelyVisiblePosition; i++) {
                            final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                            try {
                                AAH_CustomViewHolder cvh = (AAH_CustomViewHolder)holder;
                                if (i >= 0 && cvh!=null && cvh.getVideoUrl().endsWith(".mp4")) {
                                    ((AAH_CustomViewHolder) holder).pauseVideo();
                                }
                            }catch (Exception e){

                            }

                        }

                    }
                    if (lastCompletelyVisiblePosition >= 0 && lastCompletelyVisiblePosition != lastVisiblePosition) {
                        for (int i = lastVisiblePosition; i > lastCompletelyVisiblePosition; i--) {
                            final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                            try {
                                AAH_CustomViewHolder cvh = (AAH_CustomViewHolder)holder;
                                if (i >= 0 && cvh!=null && cvh.getVideoUrl().endsWith(".mp4")) {
                                    ((AAH_CustomViewHolder) holder).pauseVideo();
                                }
                            }catch (Exception e){

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


}
