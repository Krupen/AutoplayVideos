Change Log
==========

Version 0.2.0 *(2017-11-13)*
----------------------------

 * Added Support to specify percentage of view that needs to be visible for video to start playing.
 
Version 0.1.9 *(2017-11-02)*
----------------------------

 * Added Support for multiple ViewTypes in RecyclerView.
 
Version 0.1.8 *(2017-10-21)*
----------------------------

 * Support to play videos offline from local storage.
 
Version 0.1.7 *(2017-10-18)*
----------------------------

 * Added getter for various variables for advanced usage.
 
Version 0.1.6 *(2017-10-08)*
----------------------------

 * Crash fix for SetDataUri().
 * UI Freeze issue fix.
 
Version 0.1.5 *(2017-09-05)*
----------------------------

 * Single HandlerThread with multiple runnables instead of multiple HandlerThreads.
 * Check internet connection before playing or downloading videos (Requires ACCESS_NETWORK_STATE permission).
 
Version 0.1.4 *(2017-08-03)*
----------------------------

 * `onDraw` crash fix.
 * Restart videos on app minimize/maximize.
 * `HandlerThread` instead of `Thread` to run it in background.
 * Video pause/unpause bug fix.

Version 0.1.2 *(2017-07-06)*
----------------------------

 * Only `AAH_VideoImage` view has to be completely visible to start auto-play instead of the whole RecyclerView item.
 * Get callbacks when video is started or paused by overriding `videoStarted()` and `pauseVideo()` methods of `AAH_CustomViewHolder`.
 * Set looping property of videos by using `holder.setLooping(true);`.
 * If RecyclerView is loaded Asynchronously, call `recyclerView.playAvailableVideos(0)` to start autoplay videos once item is loaded.
 * Does not need smoothScrollBy hack anymore to init videos before scrolling.
