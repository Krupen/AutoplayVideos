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
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name1"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg","name2"));
//        urls.add(new MyModel("http://www.betcoingaming.com/webdesigns/animatedslider/images/liveroulette3.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name1"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","name3"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg","name4"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg","name5"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg","name6"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg","name7"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg","name8"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name9"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","name10"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795676/4_nvnzry.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg","name11"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name12"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","name13"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg","name14"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg","name15"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/3_yqeudi.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg","name16"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795675/1_pyn1fm.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg","name17"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70/v1481795681/2_rp0zyy.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_300,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name18"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg","name19"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg","name20"));
        urls.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg","name21"));


        mAdapter = new MyVideosAdapter(urls, p);
        mLayoutManager = new LinearLayoutManager(this);

        //todo before setAdapter
        recyclerView.setActivity(this);


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        //to init videos before scrolling
        recyclerView.smoothScrollBy(0,1);
        recyclerView.smoothScrollBy(0,-1);

    }




}
