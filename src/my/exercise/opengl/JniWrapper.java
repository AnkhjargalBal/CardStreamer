package my.exercise.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.datacontract.schemas.BusinessObjects.User;

import my.exercise.cameraprocess.*;
import my.exercise.videoprocess.VideoFrameUnit;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;

public class JniWrapper implements GLSurfaceView.Renderer,
		CameraProcessEventListener, SurfaceTexture.OnFrameAvailableListener {

	/** default constructor */
	public JniWrapper() {
		mUri = "";

	}

	/** path to video constructor */
	public JniWrapper(String uri) {
		mUri = uri;
	}

	/** public methods */
	public void onDrawFrame(GL10 gl) {

		synchronized (this) {
			if (updateSurface) {
				mSurface.updateTexImage();

				updateSurface = false;
			}
		}

		if (updateCoords && mCoords != null) {			
			feedPosition(mCoords);
			updateCoords = false;
		}
		nativeRender(0);
	}

	synchronized public SurfaceTexture getSurfaceTexture() {
		return mSurface;
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		nativeResize(width, height);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		int textureId = nativeInit();

		mSurface = new SurfaceTexture(textureId);
		mSurface.setOnFrameAvailableListener(this);

		synchronized (this) {
			updateSurface = false;
		}		

		mVideoUnit = new VideoFrameUnit(getSurfaceTexture());
	}

	public void onSurfaceDestroyed(GL10 gl) {

		DestroyVideo();
		nativeDone();
	}

	/** implements SurfaceTexture.OnFrameAvailableListener */
	synchronized public void onFrameAvailable(SurfaceTexture surfaceTexture) {

		updateSurface = true;
	}

	/** implements CameraProcessEventListener */
	public void onCoordsProcessed(EventCoordsProcessed evt) {
		// for camera frame only
		updateCoords = ((evt.getCoords()[0] != 0) || (evt.getCoords()[2] != 0) || (evt
				.getCoords()[4] != 0));
		if (updateCoords)
			mCoords = evt.getCoords();
	}
	
	@Override
	public void onOCRProcessed(User user) {
		// TODO Auto-generated method stub
		updateUser = (user != null);
	}

	/** stop and clean up the streaming resources */
	public void StartVideo() {
		mVideoUnit.StartVideo();
	}
	
	public void StartVideo(String uri) {
		mVideoUnit.StartVideo(uri);
	}
	
	public void PauseVideo() {
		mVideoUnit.PauseVideo();
	}
	
	public void DestroyVideo() {
		mVideoUnit.OnDestroy();
	}

	/** native methods openGL */
	private static native int nativeInit();

	private static native void nativeRender(int aTextureId);

	private static native void nativeResize(int aWidth, int aHeight);

	private static native void feedPosition(int[] aCoordinates);

	private static native void nativeDone();

	/** private variables */
	private SurfaceTexture mSurface;
	private static int[] mCoords;
	private static String mUri;
	private final int mWidth = 256;
	private final int mHeight = 256;
	private boolean updateSurface = false;
	private boolean updateCoords = false;
	private boolean updateUser = false;
	private VideoFrameUnit mVideoUnit;

	/** static library for OpenGL + Ffmpeg */
	static {
		System.loadLibrary("JniWrapper");
	}	
}
