package my.exercise.cameraprocess;

import java.io.IOException;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class CameraProcessingView extends SurfaceView implements
		PreviewCallback, SurfaceHolder.Callback {

	public CameraProcessingView(Context context) {
		super(context);
		mOCR = new NameRecognition();
		takeOCR = false;
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
	}

	public CameraProcessingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mOCR = new NameRecognition();
		takeOCR = false;
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
	}

	public void setOnCoordsProcessedListener(CameraProcessEventListener listener) {
		mOnCoordsProcessedListener = listener;
	}
	
	public void setOnOCRProcessedListener(CameraProcessEventListener listener) {
		mOnOCRProcessedListener = listener;
	}

	public void onPreviewFrame(byte[] data, Camera camera) {
		if ((mOnCoordsProcessedListener != null)) {
			synchronized (this) {				
				int[] coordinates = coords(data.clone(), camera.getParameters()
						.getPreviewSize().width, camera.getParameters()
						.getPreviewSize().height);
				Log.d("COORDS", Integer.toString(coordinates[0]) + " " + Integer.toString(coordinates[1]) + 
						"\r" + Integer.toString(coordinates[2]) + " " + Integer.toString(coordinates[3]) + 
						"\r" + Integer.toString(coordinates[4]) + " " + Integer.toString(coordinates[5]) +
						"\r" + Integer.toString(coordinates[6]) + " " + Integer.toString(coordinates[7]));
				mOnCoordsProcessedListener.onCoordsProcessed(EventCoordsProcessed
						.CreateCoordsProcessedEvent(this, coordinates));
				
				if (takeOCR) {

					if (!(coordinates[0] == 0 && coordinates[4] == 0 && coordinates[6] == 0)) {
						mOCR.ocrImage(data.clone(), coordinates);
						
						mOnOCRProcessedListener.onOCRProcessed(mOCR.getUser());
						
						takeOCR = false;
					}
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mCamera != null) {
			mCamera.autoFocus(mAutoFocusCallback);
		}

		return super.onTouchEvent(event);
	}

	@TargetApi(16)
	public void initCamera(SurfaceHolder aSh) {
		if (mCamera != null) {
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
		}
		mCamera = Camera.open();
		try {
			/*
			 * List<Size> supportedSizes = mCamera.getParameters()
			 * .getSupportedPreviewSizes(); Camera.Parameters parameters =
			 * mCamera.getParameters();
			 * parameters.setPreviewSize(supportedSizes.get(0).width,
			 * supportedSizes.get(0).height); requestLayout();
			 * mCamera.setParameters(parameters);
			 */
			
			mCamera.setPreviewCallback(this);
			mCamera.setPreviewDisplay(aSh);
			mCamera.setDisplayOrientation(90);
		} catch (IOException e) {
			mCamera.release();
			e.printStackTrace();

		}
		mCamera.startPreview();
		mCamera.autoFocus(mAutoFocusCallback);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		initCamera(holder);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// do nothing
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mCamera != null) {
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
		finish();
	}

	/** native functions */
	private static native int[] coords(byte[] camera_preview, int width,
			int height);

	private static native void finish();

	/** private variables */
	private CameraProcessEventListener mOnCoordsProcessedListener = null;
	private CameraProcessEventListener mOnOCRProcessedListener = null;
	private static Camera mCamera;
	private SurfaceHolder mSurfaceHolder;

	private boolean takeOCR = false;
	private NameRecognition mOCR;	

	private AutoFocusCallback mAutoFocusCallback = new AutoFocusCallback() {
		public void onAutoFocus(boolean arg0, Camera arg1) {			
			takeOCR = true;
		}
	};

	/** static library OpenCV */
	static {
		System.loadLibrary("opencv_java");
		System.loadLibrary("JniAr");
	}
}
