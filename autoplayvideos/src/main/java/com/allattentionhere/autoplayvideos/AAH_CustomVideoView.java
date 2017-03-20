package com.allattentionhere.autoplayvideos;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.io.IOException;
import java.util.concurrent.Callable;


public class AAH_CustomVideoView extends TextureView implements TextureView.SurfaceTextureListener {
    private MediaPlayer mMediaPlayer;
    private Uri mSource;
    //    private MediaPlayer.OnCompletionListener mCompletionListener;
    private boolean isLooping = false;
    Callable<Integer> myFuncIn = null;
    Activity _act;

    public AAH_CustomVideoView(Context context) {
        this(context, null, 0);
    }

    public AAH_CustomVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AAH_CustomVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSource(Uri source) {
        mSource = source;
    }

    public void set_act(Activity _act) {
        this._act = _act;
    }

    public void setMyFuncIn(Callable<Integer> myFuncIn) {
        this.myFuncIn = myFuncIn;
    }

    public void startVideo() {
        setSurfaceTextureListener(this);
        if (this.getSurfaceTexture() != null) {
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
            } else {
                Surface surface = new Surface(this.getSurfaceTexture());
                try {
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                                _act.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            myFuncIn.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });

                            }
                            return false;
                        }
                    });

//            mMediaPlayer.setOnCompletionListener(mCompletionListener);
//            mMediaPlayer.setOnBufferingUpdateListener(this);
//            mMediaPlayer.setOnErrorListener(this);
                    mMediaPlayer.setLooping(isLooping);
                    mMediaPlayer.setDataSource(getContext(), mSource);
                    mMediaPlayer.setSurface(surface);
                    mMediaPlayer.prepare();
                    if (mMediaPlayer != null) mMediaPlayer.start();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
//        mCompletionListener = listener;
//    }

    public void setLooping(boolean looping) {
        isLooping = looping;
    }

    @Override
    protected void onDetachedFromWindow() {
        // release resources on detach
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        super.onDetachedFromWindow();
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();

        } else {
            Surface surface = new Surface(surfaceTexture);
            try {
                mMediaPlayer = new MediaPlayer();
                if (myFuncIn != null) {
                    mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                                _act.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            myFuncIn.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                            return false;
                        }
                    });
                }

//            mMediaPlayer.setOnCompletionListener(mCompletionListener);
//            mMediaPlayer.setOnBufferingUpdateListener(this);
//            mMediaPlayer.setOnErrorListener(this);
                mMediaPlayer.setLooping(isLooping);
                mMediaPlayer.setDataSource(getContext(), mSource);
                mMediaPlayer.setSurface(surface);
                mMediaPlayer.prepare();
                mMediaPlayer.start();

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //pre lollipop needs SurfaceTexture it owns before calling onDetachedFromWindow super
            surface.release();
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    public void clearAll() {
        if (getSurfaceTexture() != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //pre lollipop needs SurfaceTexture it owns before calling onDetachedFromWindow super
                getSurfaceTexture().release();
            }
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
        }
        setSurfaceTextureListener(null);
    }

    public void pauseVideo() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }

    }


}