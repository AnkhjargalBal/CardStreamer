 package my.exercise.videoprocess;

import android.graphics.SurfaceTexture;
import android.os.Environment;
import android.view.Surface;

public class VideoFrameUnit {

	public VideoFrameUnit(SurfaceTexture surfaceTexture) {

		mSurfaceTexture = surfaceTexture;
		// String uri = "/storage/sdcard0/DCIM/Camera/Pikach.mp4";
		mUri = Environment.getExternalStorageDirectory() + "/NativeMedia.ts";	
		//mUri = Environment.getExternalStoragePublicDirectory(
	    //        Environment.DIRECTORY_PICTURES).toURI().toASCIIString() + "native_media.ts";
		//mUri = "/storage/sdcard0/DCIM/Camera/Qurius.ts";

		InitStartVideo();
	}

	public VideoFrameUnit(SurfaceTexture surfaceTexture, String uri) {

		mSurfaceTexture = surfaceTexture;

		mUri = uri;

		InitStartVideo();
	}

	public void StartVideo() {
		if(created) {
			setPlayingStreamingMediaPlayer(true);
		}
		else {
			created = createStreamingMediaPlayer(mUri);
		}		
	}
	
	public void StartVideo(String uri) {
		if(created) {
			setPlayingStreamingMediaPlayer(true);
		}
		else {
			created = createStreamingMediaPlayer(uri);
		}		
	}
	
	public void PauseVideo() {
		if(created) {
			setPlayingStreamingMediaPlayer(false);
		}
	}

	private void InitStartVideo() {
		createEngine();

		Surface s = new Surface(mSurfaceTexture);
		// the surface is copied
		setSurface(s);
		s.release();
	}

	public void OnDestroy() {
		shutdown();
	}

	/** Native methods, implemented in jni folder */
	public static native void createEngine();

	public static native boolean createStreamingMediaPlayer(String filename);

	public static native void setPlayingStreamingMediaPlayer(boolean isPlaying); // Optional

	public static native void shutdown();

	public static native void setSurface(Surface surface);

	public static native void rewindStreamingMediaPlayer();

	/** Private variables */
	private boolean created;
	private SurfaceTexture mSurfaceTexture;
	private String mUri;

	static {
		System.loadLibrary("JniVideo");
	}

}
