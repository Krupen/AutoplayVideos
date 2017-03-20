package com.allattentionhere.autoplayvideossample.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.allattentionhere.autoplayvideossample.Adapter.MyVideosAdapter;
import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;
import com.allattentionhere.autoplayvideossample.Model.MyModel;
import com.allattentionhere.autoplayvideossample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.rv_home)
    AAH_CustomRecyclerView recyclerView;

    private MyVideosAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    List<MyModel> urls = new ArrayList<>();
    private static Picasso p ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        p = Picasso.with(this);
//        urls.add(new MyModel("http://www.betcoingaming.com/webdesigns/animatedslider/images/liveroulette2.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name1"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","video1"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg","video2"));
//        urls.add(new MyModel("http://www.betcoingaming.com/webdesigns/animatedslider/images/liveroulette3.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name1"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","image3"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg","video4"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg","video5"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg","video6"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg","image7"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg","image8"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","video9"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","name10"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg","video11"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","video12"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","image13"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg","image14"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg","image15"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg","video16"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg","video17"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","video18"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg","image19"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","image20"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg","image21"));


        mAdapter = new MyVideosAdapter(urls, p);
        mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //todo before setAdapter
        recyclerView.setActivity(this);
        recyclerView.setAdapter(mAdapter);
        //to init videos before scrolling
        recyclerView.smoothScrollBy(0,1);
        recyclerView.smoothScrollBy(0,-1);

    }




}
