package com.allattentionhere.autoplayvideos.Activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.allattentionhere.autoplayvideos.Adapter.VideosAdapter;
import com.allattentionhere.autoplayvideos.Customview.CustomRecyclerView;
import com.allattentionhere.autoplayvideos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.rv_home)
    CustomRecyclerView recyclerView;

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
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4");
        urls.add("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg");
        urls.add("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg");

        mAdapter = new VideosAdapter(urls, this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setUrls(urls);
        recyclerView.setAdapter(mAdapter);

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
