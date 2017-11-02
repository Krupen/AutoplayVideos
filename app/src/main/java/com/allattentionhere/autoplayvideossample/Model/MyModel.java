package com.allattentionhere.autoplayvideossample.Model;

/**
 * Created by krupenghetiya on 03/02/17.
 */

public class MyModel {
    private final String image_url;
    private String video_url;
    private final String name;

    public MyModel(String video_url, String image_url, String name) {
        this.video_url = video_url;
        this.image_url = image_url;
        this.name = name;
    }

    public MyModel(String image_url, String name) {
        this.image_url = image_url;
        this.name = name;
    }

    public MyModel(String name) {
        this.image_url=null;
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public String getName() {
        return name;
    }
}
