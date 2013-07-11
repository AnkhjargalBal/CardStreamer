package my.exercise.cameraprocess;

import java.util.Arrays;

import org.datacontract.schemas.BusinessObjects.User;
import org.tempuri.Configuration;
import org.tempuri.IService;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * NameRecognition
 * 
 * @author martin kodovsky
 * @uses tesseract-ocr It uses mechanism of sequential matching of listed names
 *       against recognized string from camera. The advantage of it is low
 *       latency but there is a lower accuracy on the other hand. There are
 *       other approaches to check (//TODO) in order to find out the fastest
 *       algorithm.
 */
public class NameRecognition {

	public NameRecognition() {

	}

	public User getUser() {
		return mUser;
	}

	/** public methods */
	public void ocrImage(byte[] data, int[] coordinates) {

		// 1st and 3rd
		int width = 640;
		int height = 480;

		int pointX = coordinates[0];
		int pointY = coordinates[1];

		int deltaX;
		int deltaY;
		int ddy;

		int minY[] = new int[3];

		minY[0] = coordinates[3];
		minY[1] = coordinates[5];
		minY[2] = coordinates[7];
		Arrays.sort(minY);

		ddy = pointY - minY[0];

		if ((Math.abs(pointY - coordinates[5]) > Math.abs(pointY
				- coordinates[7])) &&
				(Math.abs(pointX - coordinates[4]) > Math.abs(pointX - coordinates[6]))) {
			deltaX = pointX - coordinates[4];
			deltaY = pointY - coordinates[5];
		} else {
			deltaX = pointX - coordinates[6];
			deltaY = pointY - coordinates[7];
		}
		
		Log.i("OCR", String.format("Deltas: %d %d", deltaX, deltaY));

		if (deltaX == 0 || deltaY == 0) {
			deltaX = width;
			deltaY = height;
		}

		int[] picture = new int[width * height];

		// yuv to grayscale
		tessInput(data, width, height, pointX, pointY, deltaX, deltaY, picture);

		Log.i("OCR", String.format("Yuv->Gray: %d %d", width, height));
		Bitmap bitmap = Bitmap.createBitmap(picture, width, height,
				Bitmap.Config.ARGB_8888);
		
		// crop the image
		Log.i("OCR",
				String.format("Crop: %d %d -> %d %d", bitmap.getWidth(),
						bitmap.getHeight(), pointX + Math.abs(deltaX), pointY
								+ Math.abs(deltaY)));
		if (pointX + deltaX >= bitmap.getWidth()) {
			deltaX = bitmap.getWidth() - pointX;
		}
		
		if (pointY + deltaY >= bitmap.getHeight()) {
			deltaY = bitmap.getHeight() - pointY;
		}
										
		bitmap = Bitmap.createBitmap(bitmap, pointX, pointY, Math.abs(deltaX),
				Math.abs(deltaY));
		
		// rotate the image		
		Matrix matrix = new Matrix();
		double ratio = ((double) ddy / (double) deltaX);
		double angle = Math.atan(ratio);
		double deg = -Math.toDegrees(angle);
		deg += -90.0d; //image is rotated in onFramePreview		
		matrix.postRotate((float) -deg, bitmap.getWidth() / 2,
				bitmap.getHeight() / 2);
		Log.i("OCR", String.format("Angle: %f", deg));		
		if(ddy >= bitmap.getHeight()) {
			ddy = 0;
		}
		
		if(bitmap.getHeight() - Math.abs(ddy) > 0) {
		
		bitmap = Bitmap.createBitmap(bitmap, 0, Math.abs(ddy), bitmap.getWidth(),				
				bitmap.getHeight() - Math.abs(ddy), matrix, true);		
				

		// scale it for better recognition
		int newWidth = bitmap.getWidth() << 2;
		int newHeight = bitmap.getHeight() << 2;
		Log.i("OCR", String.format("Scale: %d %d", newWidth, newHeight));
		bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);

		picture = new int[newWidth * newHeight];
		bitmap.getPixels(picture, 0, newWidth, 0, 0, newWidth, newHeight);
		// threshold it
		bitmap.recycle();
		threshold(picture, newWidth, newHeight, 127, picture);
		Log.i("OCR", String.format("Thres: %d %d", newWidth, newHeight));
		bitmap = Bitmap.createBitmap(picture, newWidth, newHeight,
				Bitmap.Config.ARGB_8888);
		}
		
		// DEBUG >>
		/*
		String filename = "pippo.png";

		File sd = new File("/storage/sdcard0/DCIM/Camera/");
		File dest = new File(sd, filename);

		try {
			FileOutputStream file = new FileOutputStream(dest);
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			bitmap.compress(Bitmap.CompressFormat.PNG, 100, file);
			out.flush();
			out.close();
			file.flush();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		 */
		// DEBUG <<		
		if (bitmap != null) {			
			TessBaseAPI baseApi = new TessBaseAPI();
			baseApi.init(DATA_PATH, LANG);
			baseApi.setImage(bitmap);
			String recognizedText = baseApi.getUTF8Text();
			//get the name against a web service
			//getUserFromWs(recognizedText);
			baseApi.end();
			
			Log.i("OCR", String.format("Name: %s", recognizedText));

			bitmap.recycle();
		}
	}
	

	// overload
	public void ocrImage(byte[] data, int width, int height) {
		/*
		Bitmap bitmap = BitmapFactory.decodeFile(BUS_PATH);

		TessBaseAPI baseApi = new TessBaseAPI();
		baseApi.init(DATA_PATH, LANG);
		baseApi.setImage(bitmap);
		String recognizedText = baseApi.getUTF8Text();

		getUserFromWs(recognizedText);

		baseApi.end();
		*/
	}

	private void getUserFromWs(String recognizedText) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		IService service = new IService();
		Configuration
				.setConfiguration("http://194.228.154.34:60125/ws/Service.svc");

		try {
			mUser = service.getUserBasedOnOCR(recognizedText);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (mUser != null && mUser.getLinkFacebook("") != null) {

			String fbLink = mUser.getLinkFacebook("");
			if (!fbLink.contains("http://")) {
				mUser.setLinkFacebook("http://" + fbLink);
			}
		}

		if (mUser != null && mUser.getLinkLinkedIn("") != null) {

			String inLink = mUser.getLinkLinkedIn("");
			if (!inLink.contains("http://")) {
				mUser.setLinkLinkedIn("http://" + inLink);
			}
		}

	}

	// paths
	private static String DATA_PATH = Environment.getExternalStorageDirectory()
			+ "/" + Environment.DIRECTORY_DCIM;
	
	private static String REL_DATA_PATH = "";
	
	//private static String BUS_PATH = Environment.getExternalStorageDirectory()
			// + "/" + Environment.DIRECTORY_DCIM + "/Camera/" + "ocr.jpg";
	private static String LANG = "eng";

	private User mUser;

	private static native void tessInput(byte[] data, int width, int height,
			int anchorX, int anchorY, int newWidth, int newHeight, int[] out);

	private static native void threshold(int[] data, int width, int height,
			int level, int[] out);

	/** static library OpenCV */
	static {
		System.loadLibrary("opencv_java");
		System.loadLibrary("JniAr");
	}

}
