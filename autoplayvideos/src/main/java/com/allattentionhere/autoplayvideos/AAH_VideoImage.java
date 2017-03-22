package com.allattentionhere.autoplayvideos;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * Created by krupenghetiya on 03/02/17.
 */

public class AAH_VideoImage extends FrameLayout {
    private AAH_CustomVideoView cvv;
    private ImageView iv;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AAH_VideoImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public AAH_CustomVideoView getCustomVideoView() {
        return cvv;
    }

    public ImageView getImageView() {
        return iv;
    }


    private void init() {
        this.setTag("aah_vi");
        cvv = new AAH_CustomVideoView(getContext());
        iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.addView(cvv);
        this.addView(iv);
    }
}
