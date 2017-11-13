# AutoplayVideos
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg)](https://android-arsenal.com/api?level=16) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoPlay%20Videos-2CB3E5.svg)](https://android-arsenal.com/details/1/5372) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23249-2CB3E5.svg)](http://androidweekly.net/issues/issue-249) [![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%23136-2CB3E5.svg)](https://www.androiddevdigest.com/digest136/) 

### Show some :heart: and star the repo to support the project
[![GitHub stars](https://img.shields.io/github/stars/Krupen/AutoplayVideos.svg?style=social)](https://github.com/Krupen/AutoplayVideos/stargazers) [![GitHub forks](https://img.shields.io/github/forks/Krupen/AutoplayVideos.svg?style=social)](https://github.com/Krupen/AutoplayVideos/network) [![GitHub watchers](https://img.shields.io/github/watchers/Krupen/AutoplayVideos.svg?style=social)](https://github.com/Krupen/AutoplayVideos/watchers) [![GitHub followers](https://img.shields.io/github/followers/Krupen.svg?style=social)](https://github.com/Krupen/followers)  
[![Twitter Follow](https://img.shields.io/twitter/follow/KrupenGhetiya.svg?style=social&label=Follow)](https://twitter.com/krupenghetiya)


This library is created with the purpose to implement recyclerview with videos easily.

It is targeted at solving following problems:

1. Flicker when scrolling.
2. Lag or skipping frames when video starts.
3. OutOfMemory errors.

And it has following features:

1. Auto-play videos when in view.
2. Auto-pause videos when not in view or partially in view.
3. Mute/Un-mute videos.
4. Option to play only first visible video.
5. Download videos to local storage in background for faster loading.


# Demo
![autoplayvideos_demo](https://raw.githubusercontent.com/Krupen/AutoplayVideos/master/AutoPlayVideos%20Demo.gif)

# Download
**Gradle**

**Step 1.** Add the jCenter repository to your project-level build.gradle file

``` groovy
allprojects {
	repositories {
		jcenter()
	}
}
```

**Step 2.** Add the dependency to your app-level build.gradle file:

``` groovy
dependencies {
	 compile 'com.allattentionhere:autoplayvideos:0.2.0'
}
```


**Or Maven**
``` groovy
<dependency>
  <groupId>com.allattentionhere</groupId>
  <artifactId>autoplayvideos</artifactId>
  <version>0.2.0</version>
  <type>pom</type>
</dependency>
```

# Permissions

Add below permissions to `AndroidManifest.xml`
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

# Usage

Add `AAH_VideoImage` to your xml file for single list item `single_card.xml`:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v7.widget.CardView
        android:id="@+id/card_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
       
       <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="vertical">

             <FrameLayout
                android:layout_width="300dp"
                android:layout_height="150dp">

                <com.allattentionhere.autoplayvideos.AAH_VideoImage
                    android:layout_width="match_parent"
                    android:layout_height="match_parent" />

                <ImageView
                    android:id="@+id/img_vol"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="right|bottom"
                    android:layout_margin="8dp"
                    android:src="@drawable/ic_unmute"/>
            </FrameLayout>

            <TextView
                android:id="@+id/tv"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center" />
		
        </LinearLayout>
    </android.support.v7.widget.CardView>
</LinearLayout>
```

Add `AAH_CustomRecyclerView` to your Activity layout xml `MainActivity.xml`:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView
        android:id="@+id/rv_home"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
	
</LinearLayout>
```

Set Adapter with following specifics:

1. Adapter should extend `AAH_VideosAdapter`.
2. ViewHolder should extend `AAH_CustomViewHolder`.
3. Set thumbnail image url and video url in `onBindViewHolder` method.
```
public class MyVideosAdapter extends AAH_VideosAdapter {

    private List<MyModel> list;
    Picasso picasso;

    public class MyViewHolder extends AAH_CustomViewHolder {
        final TextView tv;
	final ImageView img_vol,img_playback;
        boolean isMuted; //to mute/un-mute video (optional)
	
        public MyViewHolder(View x) {
            super(x);
            tv = ButterKnife.findById(x, R.id.tv);
	    img_vol = ButterKnife.findById(x, R.id.img_vol);
	    img_playback = ButterKnife.findById(x, R.id.img_playback);
        }
    }

    public MyVideosAdapter(List<MyModel> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
    }

    @Override
    public AAH_CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AAH_CustomViewHolder holder, int position) {
        ((MyViewHolder) holder).tv.setText(list.get(position).getName());

        //todo
        holder.setImageUrl(list.get(position).getImage_url());
        holder.setVideoUrl(list.get(position).getVideo_url());
        //load image/thumbnail into imageview
        if (list.get(position).getImage_url() != null && !list.get(position).getImage_url().isEmpty())
            picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getAAH_ImageView());
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
```

Finally `setActivity` in your Activity before setting the adapter and (Optional) scroll programmatically to initiate videos on initial screen:
```
    recyclerView.setActivity(this); //todo before setAdapter
    recyclerView.setAdapter(mAdapter);
```


### Play videos initially (Optional)
Call these two functions to start video playback when the screen launches and user hasn't scrolled.
```
recyclerView.smoothScrollBy(0,1);
recyclerView.smoothScrollBy(0,-1);
```


### Play only 1st video (Optional)
Setting this parameter will play video only in 1st completely visible RecyclerView ViewHolder.
```
recyclerView.setPlayOnlyFirstVideo(true); // false by default
```


### Download videos to local storage (Optional)
You can start downloading video in background on viewholder loaded. You can change download path.
```
recyclerView.setDownloadPath(Environment.getExternalStorageDirectory() + "/MyVideo"); //optional
recyclerView.setDownloadVideos(true); // false by default
```
Optionally you can start pre-downloading all the videos by passing list of URLs to function as below:
```
List<String> urls = new ArrayList<>();
 for (MyModel object : modelList) {
     if (object.getVideo_url() != null && object.getVideo_url().endsWith(".mp4"))
         urls.add(object.getVideo_url());
 }
recyclerView.preDownload(urls);
```
Add below permission to `AndroidManifest.xml`. Ask for runtime permission in devices on Marshmallow and above.
```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```


### Remove check for .mp4 (Optional)
By default it checks for url to end with `.mp4` else it is not considered as video URL. You can override this by setting parameter as below. Please use this with caution and make sure you provide video URL only.
```
recyclerView.setCheckForMp4(false); // true by default
```


### Pause videos manually (Optional)
Call the following method to stop the videos when Activity/Fragment stops (User receives call, minimizes app etc)
```
    @Override
    protected void onStop() {
        super.onStop();
        recyclerView.stopVideos();
    }
```

### Resume videos when app resumes (Optional)
Call the following method to resume videos when App resumes (opening app after minimizing it, etc)
```
    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.playAvailableVideos(0);
    }
```

### Play partially visible video (Optional)
Call the following method to set the percentage of view that needs to be visible for video to start playing.
```
    recyclerView.setVisiblePercent(50);
```

### Get callbacks when videos starts and pauses
You can override the below methods of `AAH_CustomViewHolder` to get callback when video starts to play or pauses.
```
	@Override
        public void videoStarted() {
            super.videoStarted();
            img_playback.setImageResource(R.drawable.ic_pause);
            if (isMuted) {
                muteVideo();
                img_vol.setImageResource(R.drawable.ic_mute);
            } else {
                unmuteVideo();
                img_vol.setImageResource(R.drawable.ic_unmute);
            }
        }
        @Override
        public void pauseVideo() {
            super.pauseVideo();
            img_playback.setImageResource(R.drawable.ic_play);
        }
```


### Play or pause videos manually
You can allow the user to play or pause any video by adding below code in `onBindViewHolder`:
```
	((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.isPlaying()) {
                    holder.pauseVideo();
                    holder.setPaused(true);
                } else {
                    holder.playVideo();
                    holder.setPaused(false);
                }
            }
        });
```


### Mute or Unmute the videos
Video can be muted/unmuted by adding below code in `onBindViewHolder`:
```
	holder.getAah_vi().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MyViewHolder) holder).isMuted) {
                    holder.unmuteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_unmute);
                } else {
                    holder.muteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_mute);
                }
                ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
            }
        });
```


### Set looping on videos
Set looping on videos by adding below code in `onBindViewHolder`:
```
holder.setLooping(true); //optional - true by default
```


# Cache Videos (Optional)

It is recommended to `setDownloadVideos(value)` to true. However if you do not want to use this option, we recommend you to use <a href="https://github.com/danikula/AndroidVideoCache" target="_blank">AndroidVideoCache library</a>. 
Here is how you can use it along with our library:

### Initilize HttpProxyCacheServer
Initilize `HttpProxyCacheServer` in your Application class and register Application class in your Manifest file.
```
public class MyApplication extends Application {
	private static HttpProxyCacheServer proxy;

	public static HttpProxyCacheServer getProxy() {
	    return proxy;
	}

	@Override
	public void onCreate() {
	    super.onCreate();
	    proxy = new HttpProxyCacheServer(this);
	}
}	
```

### Set proxy video URL
In your VideosAdapter, set the Video URL as follows:
```
holder.setVideoUrl(MyApplication.getProxy().getProxyUrl(list.get(position).getVideo_url()+"")); // url should not be null
```

You can also use advanced options for caching that the library supports. Refer to their documentation for the same.


# Use Cloudinary (Optional)

It is recommended to use <a href="https://cloudinary.com" target="_blank">Cloudinary.com</a> to host your videos as it provides easy <a href="http://cloudinary.com/documentation/video_manipulation_and_delivery#generating_video_thumbnails" target="_blank">thumbnail-generation</a> and <a href="http://cloudinary.com/documentation/video_manipulation_and_delivery#resizing_and_cropping_videos" target="_blank">resizing/cropping videos</a> on-the-fly.


# Changelog
* <a href="/CHANGELOG.md" target="_blank">Changelog</a>

# Our other libraries
* <a href="https://github.com/Krupen/FabulousFilter" target="_blank">FabulousFilter</a>

# Apps by developer
[![Price Stalker](https://github.com/Krupen/AutoplayVideos/blob/master/pricestalker.png?raw=true)](https://play.google.com/store/apps/details?id=com.allattentionhere.pricestalker)  [![Show Card Game](https://github.com/Krupen/AutoplayVideos/blob/master/show.png?raw=true)](https://play.google.com/store/apps/details?id=com.allattentionhere.show)  [![Safio chat](https://github.com/Krupen/AutoplayVideos/blob/master/safiochat.png?raw=true)](https://play.google.com/store/apps/details?id=com.allattentionhere.safio)

# License
Copyright 2017 Krupen Ghetiya

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


