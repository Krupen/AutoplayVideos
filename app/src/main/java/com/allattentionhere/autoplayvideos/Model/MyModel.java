package com.allattentionhere.autoplayvideos.Model;

/**
 * Created by krupenghetiya on 03/02/17.
 */

public class MyModel {
    String url;
    String name;

    public MyModel(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
