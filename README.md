# AutoplayVideos
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoPlay%20Videos-blue.svg)](https://android-arsenal.com/details/1/5372) [![API](https://img.shields.io/badge/API-16%2B-green.svg)](https://android-arsenal.com/api?level=16)
This library is created with the purpose to implement recyclerview with videos easily.
This is the first version of the library and suggestions/contributions are most welcome to improvise the library.

It is targeted at solving following problems:

1. Flicker when scrolling.
2. Lag or skipping frames when video starts.
3. OutOfMemory errors.

And it has following features:

1. Auto-play videos when in view.
2. Auto-pause videos when not in view or partially in view.


# Demo
![autoplayvideos_demo](https://raw.githubusercontent.com/Krupen/AutoplayVideos/master/AutoPlayVideos%20Demo.gif)

# Gradle

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
	 compile 'com.allattentionhere:autoplayvideos:0.0.4'
}
```


# Usage

Add `AAH_VideoImage` to your xml file for single list item `single_card.xml`:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:gravity="center"
    android:layout_marginBottom="8dp"
    android:layout_marginLeft="64dp"
    android:layout_marginRight="64dp"
    android:layout_marginTop="8dp"
    >
 <android.support.v7.widget.CardView
    android:id="@+id/card_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    card_view:cardCornerRadius="4dp"
    card_view:cardElevation="4dp"
    android:layout_gravity="center"
    card_view:cardPreventCornerOverlap="false">
    <LinearLayout
        android:orientation="vertical"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
	
        <com.allattentionhere.autoplayvideos.AAH_VideoImage
            android:layout_width="220dp"
            android:layout_height="220dp"/>
	    
        <TextView
            android:gravity="center"
            android:text="hello"
            android:id="@+id/tv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </LinearLayout>
 </android.support.v7.widget.CardView>
</LinearLayout>
```

Add `AAH_CustomRecyclerView` to your Activity layout xml `MainActivity.xml`:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:ignore="MissingPrefix"
    android:gravity="center">

<com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView
        android:layout_gravity="center"
        android:id="@+id/rv_home"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"/>

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
        TextView tv;
        public MyViewHolder(View x) {
            super(x);
            tv = ButterKnife.findById(x, R.id.tv);
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
 //todo before setAdapter
    recyclerView.setActivity(this);
    recyclerView.setAdapter(mAdapter);
 //to init videos before scrolling
    recyclerView.smoothScrollBy(0,1);
    recyclerView.smoothScrollBy(0,-1);
```
# Use Cloudinary (Optional)

It is recommended to use <a href="https://cloudinary.com" target="_blank">Cloudinary.com</a> to host your videos as it provides easy <a href="http://cloudinary.com/documentation/video_manipulation_and_delivery#generating_video_thumbnails" target="_blank">thumbnail-generation</a> and <a href="http://cloudinary.com/documentation/video_manipulation_and_delivery#resizing_and_cropping_videos" target="_blank">resizing/cropping videos</a> on-the-fly.

# License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


