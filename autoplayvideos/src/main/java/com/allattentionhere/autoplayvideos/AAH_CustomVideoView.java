package com.allattentionhere.autoplayvideos;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.io.IOException;
import java.util.concurrent.Callable;

import static android.content.ContentValues.TAG;


public class AAH_CustomVideoView extends TextureView implements TextureView.SurfaceTextureListener {
    private MediaPlayer mMediaPlayer;
    private Uri mSource;
    //    private MediaPlayer.OnCompletionListener mCompletionListener;
    private boolean isLooping = false, isPaused = false;
    private Callable<Integer> myFuncIn = null;
    private Callable<Integer> showThumb = null;
    private Activity _act;

    public void setShowThumb(Callable<Integer> showThumb) {
        this.showThumb = showThumb;
    }

    public AAH_CustomVideoView(Context context) {
        this(context, null, 0);
    }

    public AAH_CustomVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AAH_CustomVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
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
//        Log.d("k9k9", "startVideo: ");
        if (!isPaused && _act!=null && !_act.isFinishing() && mSource!=null && !mSource.toString().isEmpty()) {
            setSurfaceTextureListener(this);
            if (this.getSurfaceTexture() != null) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.start();
                    try {
                        myFuncIn.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Surface surface = new Surface(this.getSurfaceTexture());
                    try {
                        mMediaPlayer = new MediaPlayer();
                        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
                            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
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
                            });
                        } else {
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

//                  mMediaPlayer.setOnCompletionListener(mCompletionListener);
//                  mMediaPlayer.setOnBufferingUpdateListener(this);
//                  mMediaPlayer.setOnErrorListener(this);
//                        Log.d("k9looper", "isMainThread prepare on start: "+(Looper.myLooper() == Looper.getMainLooper()));
                        mMediaPlayer.setLooping(isLooping);
                        mMediaPlayer.setDataSource(_act, mSource);
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
        try {
            if (showThumb!=null) showThumb.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDetachedFromWindow();
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
//        Log.d("k9k9", "onSurfaceTextureAvailable: ");
        if (!isPaused && _act!=null && !_act.isFinishing() && mSource!=null && !mSource.toString().isEmpty()) {
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
                try {
                    myFuncIn.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                final Surface surface = new Surface(surfaceTexture);
                try {
                    mMediaPlayer = new MediaPlayer();
                    if (myFuncIn != null) {
                        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
                            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
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
                            });
                        } else {
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

                    }

//            mMediaPlayer.setOnCompletionListener(mCompletionListener);
//            mMediaPlayer.setOnBufferingUpdateListener(this);
//            mMediaPlayer.setOnErrorListener(this);
                    final HandlerThread handlerThread = new HandlerThread("DONT_GIVE_UP2",android.os.Process.THREAD_PRIORITY_BACKGROUND+ android.os.Process.THREAD_PRIORITY_MORE_FAVORABLE);
                    handlerThread.start();
                    Looper looper = handlerThread.getLooper();
                    Handler handler = new Handler(looper);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            Log.d("k9looper", "isMainThread prepare on Avail: "+(Looper.myLooper() == Looper.getMainLooper()));
                            try {
                                mMediaPlayer.setLooping(isLooping);
                                mMediaPlayer.setDataSource(_act, mSource);
                                mMediaPlayer.setSurface(surface);
                                mMediaPlayer.prepare();
                                if (mMediaPlayer != null) mMediaPlayer.start();
                            } catch (Exception e) {
                                Log.e("k9exception", "run: "+e.getMessage());
                                e.printStackTrace();
                            }finally {
                                handlerThread.quit();
                            }

                        }
                    });



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//        Log.d("k9k9", "onSurfaceTextureDestroyed: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //pre lollipop needs SurfaceTexture it owns before calling onDetachedFromWindow super
            surface.release();
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        try {
            showThumb.call();
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        }
        return false;
    }

    public void muteVideo() {
        if (mMediaPlayer != null)
            mMediaPlayer.setVolume(0f, 0f);
    }

    public void unmuteVideo() {
        if (mMediaPlayer != null)
            mMediaPlayer.setVolume(1f, 1f);
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public Uri getSource() {
        return mSource;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public boolean isPaused() {
        return isPaused;
    }
}