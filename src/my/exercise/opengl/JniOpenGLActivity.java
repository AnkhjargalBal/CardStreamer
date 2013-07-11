package my.exercise.opengl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.datacontract.schemas.BusinessObjects.User;
import org.datacontract.schemas.WebService.UserRequestResult;
import org.tempuri.Configuration;
import org.tempuri.IService;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import my.exercise.cameraprocess.CameraProcessEventListener;
import my.exercise.cameraprocess.CameraProcessingView;
import my.exercise.cameraprocess.EventCoordsProcessed;

@SuppressWarnings("unused")
public class JniOpenGLActivity extends Activity implements
		CameraProcessEventListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout lContainerLayout = new RelativeLayout(this);
		lContainerLayout.setLayoutParams(new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		/** copy the resources onto external storage */
		/*
		File media = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/NativeMedia.ts");

		if (!media.exists()) {
			try {
				InputStream is = getResources().openRawResource(
						R.raw.native_media);

				String localFilePath = Environment
						.getExternalStorageDirectory().getPath()
						+ "/NativeMedia.ts";
				FileOutputStream fos = new FileOutputStream(localFilePath,
						false);
				OutputStream os = new BufferedOutputStream(fos);

				byte[] buffer = new byte[1024];
				int byteRead = 0;

				while ((byteRead = is.read(buffer)) != -1) {
					os.write(buffer, 0, byteRead);
				}
				fos.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		*/

		mRenderer = new JniWrapper();

		mGLView = new OpenGLView(this.getApplicationContext());
		mGLView.injectRenderer(mRenderer);
		mGLView.setZOrderOnTop(true);

		mCameraView = new CameraProcessingView(this);

		mCameraView
				.setOnCoordsProcessedListener((CameraProcessEventListener) mRenderer);

		mCameraView
				.setOnOCRProcessedListener((CameraProcessEventListener) this);

		ViewGroup.LayoutParams cameraViewParams = new ViewGroup.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mCameraView.setLayoutParams(cameraViewParams);

		// buttons
		mFbButton = new Button(this);
		mFbButton
				.setBackground(getResources().getDrawable(R.drawable.facebook));
		mFbButton.bringToFront();
		mFbButton.setAlpha(0);

		mLInButton = new Button(this);

		mLInButton.setBackground(getResources()
				.getDrawable(R.drawable.linkedin));

		RelativeLayout.LayoutParams rlButton = new RelativeLayout.LayoutParams(
				150, 150);
		rlButton.leftMargin = 0;
		rlButton.topMargin = 120;
		mLInButton.setLayoutParams(rlButton);
		mLInButton.bringToFront();
		mLInButton.setAlpha(0);

		lContainerLayout.addView(mCameraView);
		lContainerLayout.addView(mGLView);
		lContainerLayout.addView(mFbButton);
		lContainerLayout.addView(mLInButton);

		setContentView(lContainerLayout);
	}

	@Override
	public void onCoordsProcessed(EventCoordsProcessed evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOCRProcessed(User user) {
		// TODO Auto-generated method stub
		if (user != null) {
			mUser = user;
			if (mUser.getLinkFacebook("") != null) {
				mFbButton.setAlpha(1);
				mFbButton.setOnClickListener(facebookListener);
			}

			if (mUser.getLinkLinkedIn("") != null) {
				mLInButton.setAlpha(1);
				mLInButton.setOnClickListener(linkedinListener);
			}
		}
		mRenderer.StartVideo();
	}

	@Override
	public void finish() {
		super.finish();
		mRenderer.DestroyVideo();
	}

	// Create an anonymous implementation of OnClickListener
	private OnClickListener facebookListener = new OnClickListener() {
		public void onClick(View v) {
			Button button = (Button) v;
			if (JniOpenGLActivity.this.getUser() != null) {

				JniOpenGLActivity.this.openWebURL(JniOpenGLActivity.this
						.getUser().getLinkFacebook(""));

			}
		}
	};

	private OnClickListener linkedinListener = new OnClickListener() {
		public void onClick(View v) {
			Button button = (Button) v;
			if (JniOpenGLActivity.this.getUser() != null) {

				JniOpenGLActivity.this.openWebURL(JniOpenGLActivity.this
						.getUser().getLinkLinkedIn(""));

			}
		}
	};

	public void openWebURL(String inURL) {
		Uri uri = Uri.parse(inURL);
		Intent browserIntent = new Intent(Intent.ACTION_VIEW);
		browserIntent.setDataAndType(uri, "text/html");
		browserIntent.addCategory(Intent.CATEGORY_BROWSABLE);

		mRenderer.PauseVideo();

		startActivity(browserIntent);
	}

	public User getUser() {
		return mUser;
	}

	/** Private members */
	private CameraProcessingView mCameraView;
	private OpenGLView mGLView;
	private JniWrapper mRenderer;
	private Button mFbButton;
	private Button mLInButton;
	private User mUser;

}