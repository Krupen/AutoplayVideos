package com.allattentionhere.autplayvideoslibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * Created by krupenghetiya on 03/02/17.
 */

public class AAH_VideoImage extends FrameLayout {
    CustomVideoView cvv;
    ImageView iv;

    public AAH_VideoImage(Context context) {
        super(context);
        init();
    }

    public AAH_VideoImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AAH_VideoImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AAH_VideoImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public CustomVideoView getCustomVIdeoView() {
        return cvv;
    }

    public ImageView getImageView() {
        return iv;
    }


    private void init() {
        this.setTag("aah_vi");
        cvv = new CustomVideoView(getContext());
        iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.addView(cvv);
        this.addView(iv);
    }
}
