package my.exercise.opengl;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import my.exercise.opengl.JniWrapper;

public class OpenGLView extends GLSurfaceView {

	public OpenGLView(Context context) {
		super(context);

		setEGLConfigChooser(8, 8, 8, 8, 0, 0);
		getHolder().setFormat(PixelFormat.RGBA_8888);
	}

	public void injectRenderer(GLSurfaceView.Renderer renderer) {

		mRenderer = renderer;

		this.setRenderer(mRenderer);
	}

	public SurfaceTexture getSurfaceTexture() {
		return ((JniWrapper) mRenderer).getSurfaceTexture();
	}

	/** Private variables */
	GLSurfaceView.Renderer mRenderer;
}
