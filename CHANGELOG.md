Change Log
==========

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
