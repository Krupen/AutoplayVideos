package com.allattentionhere.autoplayvideos.Activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.allattentionhere.autoplayvideos.Adapter.VideosAdapter;
import com.allattentionhere.autoplayvideos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.rv_home)
    RecyclerView recyclerView;

    private VideosAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    List<String> urls = new ArrayList<>();
    private static Picasso p ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        p = Picasso.with(this);
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4");

        mAdapter = new VideosAdapter(urls, this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                             @Override
                                             public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
                                                 super.onScrollStateChanged(recyclerView, newState);
                                                 List<Thread> threads = new ArrayList<Thread>();
                                                 if (newState == 0) {
                                                     int firstCompletelyVisiblePosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                                                     int lastCompletelyVisiblePosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
                                                     int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                                                     int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                                                     if (firstCompletelyVisiblePosition >= 0) {
                                                         for (int i = firstCompletelyVisiblePosition; i <= lastCompletelyVisiblePosition; i++) {
                                                             final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                                                             if (holder != null) {
                                                                 if (i >= 0 && holder instanceof VideosAdapter.CustomViewHolder && urls.get(i).endsWith(".mp4")) {
                                                                     ((VideosAdapter.CustomViewHolder) holder).initVideoView(urls.get(i));
//                                                                     ((NewHomeAdapter.ArticleViewHolder) holder).playVideo();
                                                                     Thread t = new Thread() {
                                                                         public void run() {
                                                                             ((VideosAdapter.CustomViewHolder) holder).playVideo();
                                                                         }
                                                                     };
                                                                     t.start();
                                                                     threads.add(t);
//                                                                     ((NewHomeAdapter.ArticleViewHolder) holder).hideImagePlaceHolder();
                                                                 }
                                                             }
                                                         }
                                                     }
                                                     if (firstCompletelyVisiblePosition>=0 && firstCompletelyVisiblePosition!=firstVisiblePosition){
                                                         final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(firstVisiblePosition);
                                                         if (holder != null) {
                                                             if (firstVisiblePosition >= 0 && holder instanceof VideosAdapter.CustomViewHolder && urls.get(firstVisiblePosition).endsWith(".mp4")) {
                                                                 ((VideosAdapter.CustomViewHolder) holder).pauseVideo();
                                                             }
                                                         }
                                                     }
                                                     if (lastCompletelyVisiblePosition>=0 && lastCompletelyVisiblePosition!=lastVisiblePosition){
                                                         final RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(lastVisiblePosition);
                                                         if (holder != null) {
                                                             if (lastVisiblePosition >= 0 && holder instanceof VideosAdapter.CustomViewHolder && urls.get(lastVisiblePosition).endsWith(".mp4")) {
                                                                 ((VideosAdapter.CustomViewHolder) holder).pauseVideo();
                                                             }
                                                         }
                                                     }
                                                 } else if (threads.size() > 0) {
                                                     for (Thread t : threads) {
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
                                         }
        );

        //to init videos before scrolling
        recyclerView.smoothScrollBy(0,1);
        recyclerView.smoothScrollBy(0,-1);

    }



    public void loadImageWithPicasso(final String url, final ImageView iv) {
       String newurl="";
        if (url.endsWith("mp4")){
            newurl=url.replace("mp4","jpg").replace(",q_70",",q_70,so_0");

        }else {
            newurl=url;
        }
        p.load(newurl).config(Bitmap.Config.RGB_565).into(iv);
    }
}
