package com.allattentionhere.autoplayvideossample.Activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.allattentionhere.autoplayvideos.AAH_CustomViewHolder;
import com.allattentionhere.autoplayvideos.AAH_Utils;
import com.allattentionhere.autoplayvideossample.Adapter.MyVideosAdapter;
import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;
import com.allattentionhere.autoplayvideossample.Model.MyModel;
import com.allattentionhere.autoplayvideossample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_home)
    AAH_CustomRecyclerView recyclerView;

    private final List<MyModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Picasso p = Picasso.with(this);
//        modelList.add(new MyModel("http://www.betcoingaming.com/webdesigns/animatedslider/images/liveroulette2.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","name1"));
        modelList.add(new MyModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video1"));
        modelList.add(new MyModel("text 1"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "video2"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "image3"));
        modelList.add(new MyModel("text 2"));
        modelList.add(new MyModel("text 3"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "video4"));
        modelList.add(new MyModel("text 4"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "video5"));
        modelList.add(new MyModel("text 5"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1491561340/hello_cuwgcb.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1491561340/hello_cuwgcb.jpg", "video6"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "image7"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "image8"));
        modelList.add(new MyModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video9"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "name10"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795676/4_nvnzry.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795676/4_nvnzry.jpg", "video11"));
        modelList.add(new MyModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video12"));
        modelList.add(new MyModel("text 6"));
        modelList.add(new MyModel("text 7"));
        modelList.add(new MyModel("text 8"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "image13"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "image14"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/3_yqeudi.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/3_yqeudi.jpg", "video16"));
        modelList.add(new MyModel("text 9"));
        modelList.add(new MyModel("text 10"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "image15"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70/v1481795675/1_pyn1fm.mp4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795675/1_pyn1fm.jpg", "video17"));
        modelList.add(new MyModel("https://firebasestorage.googleapis.com/v0/b/flickering-heat-5334.appspot.com/o/demo1.mp4?alt=media&token=f6d82bb0-f61f-45bc-ab13-16970c7432c4", "http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg", "video18"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/2_qwpgis.jpg", "image19"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/3_lfndfq.jpg", "image20"));
        modelList.add(new MyModel("http://res.cloudinary.com/krupen/image/upload/q_70/v1481795690/1_ybonak.jpg", "image21"));

        //you can pass local file uri, but make sure it exists
//        modelList.add(new MyModel("/storage/emulated/0/VideoPlay/myvideo.mp4","http://res.cloudinary.com/krupen/video/upload/w_300,h_150,c_crop,q_70,so_0/v1481795681/2_rp0zyy.jpg","video18"));

        MyVideosAdapter mAdapter = new MyVideosAdapter(modelList, p);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //todo before setAdapter
        recyclerView.setActivity(this);

        //optional - to play only first visible video
        recyclerView.setPlayOnlyFirstVideo(true); // false by default

        //optional - by default we check if url ends with ".mp4". If your urls do not end with mp4, you can set this param to false and implement your own check to see if video points to url
        recyclerView.setCheckForMp4(false); //true by default

        //optional - download videos to local storage (requires "android.permission.WRITE_EXTERNAL_STORAGE" in manifest or ask in runtime)
        recyclerView.setDownloadPath(Environment.getExternalStorageDirectory() + "/MyVideo"); // (Environment.getExternalStorageDirectory() + "/Video") by default

        recyclerView.setDownloadVideos(true); // false by default

        recyclerView.setVisiblePercent(50); // percentage of View that needs to be visible to start playing

        //extra - start downloading all videos in background before loading RecyclerView
        List<String> urls = new ArrayList<>();
        for (MyModel object : modelList) {
            if (object.getVideo_url() != null && object.getVideo_url().contains("http"))
                urls.add(object.getVideo_url());
        }
        recyclerView.preDownload(urls);

        recyclerView.setAdapter(mAdapter);
        //call this functions when u want to start autoplay on loading async lists (eg firebase)
        recyclerView.smoothScrollBy(0,1);
        recyclerView.smoothScrollBy(0,-1);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //add this code to pause videos (when app is minimised or paused)
        recyclerView.stopVideos();
    }


}
