#include <jni.h>
#include <string.h>

#include <opencv/cv.h>
#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv/highgui.h>
#include <android/log.h>
#include <pthread.h>

#ifdef __cplusplus
#include <vector>
#endif

#include "CameraProcessingView.hpp"
#include "functions_ar.hpp"


#define  LOG_TAG    "libgl2jni"

#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)


static int* sPrevData = new int[8];

//=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
static double now_ms(void)
{
    struct timespec res;
    clock_gettime(CLOCK_REALTIME, &res);
    return 1000.0*res.tv_sec + (double)res.tv_nsec/1e6;
}

//=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
inline double angle(cv::Point pt1, cv::Point pt2, cv::Point pt0) {
	double dx1 = pt1.x - pt0.x;
	double dy1 = pt1.y - pt0.y;
	double dx2 = pt2.x - pt0.x;
	double dy2 = pt2.y - pt0.y;
	return (dx1 * dx2 + dy1 * dy2)
			/ sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2) + 1e-10);
}


//=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
inline void vector_Rect_to_Mat(std::vector<cv::Rect>& v_rect, cv::Mat& mat) {
	mat = cv::Mat(v_rect, true);
}

///=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================/
inline void byteArray_to_Mat(jbyte& b_array, int _rows, int _cols,
		cv::Mat& mat) {

	/**
	 * declaration is following
	 * cv::Mat::Mat(int _rows, int _cols, int _type, void* _data, size_t _step=AUTO_STEP);
	 */
	mat = cv::Mat(_rows, _cols, CV_8U, (void*) &b_array);
}

//=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
inline void intArray_to_Mat(jint& b_array, int _rows, int _cols, cv::Mat& mat) {

	/**
	 * declaration is following
	 * cv::Mat::Mat(int _rows, int _cols, int _type, void* _data, size_t _step=AUTO_STEP);
	 */
	mat = cv::Mat(_rows, _cols, CV_32FC1, (void*) &b_array); //, 0x04);
}



//=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
JNIEXPORT jintArray  JNICALL Java_my_exercise_cameraprocess_CameraProcessingView_coords
(JNIEnv * aEnv, jobject aObj, jbyteArray aYUVPicture, jint aWidth,
		jint aHeight) {
	/* Get a pointer to the raw output buffer */
	jbyte *src_buf = (jbyte*) aEnv->GetByteArrayElements(aYUVPicture, 0);


	/* detected rectangle as 4 [x,y] corners */
	int* dest_buf = (int*)calloc(8, sizeof(int)); //new int[8];
	jintArray result = aEnv->NewIntArray(8);

	unsigned char* tmp_gray = (unsigned char*) malloc(
			sizeof(unsigned char) * aWidth * aHeight + 1); 

	double t0, t1, time_dif;

	cv::Mat *mat = new cv::Mat(aHeight, aWidth, CV_32FC1);
	cv::Mat *imgGray = new cv::Mat(aHeight, aWidth, CV_8U); 

	byteArray_to_Mat((jbyte&) *src_buf, aHeight, aWidth, *imgGray); 


	using namespace cv;
	using namespace std;

	t0 = now_ms();
	Canny(*imgGray, *imgGray, 25, 200);
	dilate(*imgGray, *imgGray, Mat());
	t1 = now_ms();

	//time_dif = t1 - t0;
	//LOGI("Canny + dilate: %g ms", time_dif);

	vector<vector<Point> > conts;
	vector<Vec4i> hierarchy;

	findContours(*imgGray, conts, hierarchy, CV_RETR_LIST,
			CV_CHAIN_APPROX_SIMPLE, Point(0, 0));

	int i;
	vector<Point> approx;
	double cont_area = 0.0f;
	//t0 = now_ms();
	for (i = 0; i < conts.size(); i++) {
	  
	  cv::approxPolyDP(Mat(conts[i]), approx,
			   arcLength(Mat(conts[i]), true) * 0.02, true);
	  
	  cont_area = fabs(contourArea(Mat(approx)));
	  if (approx.size() == 4 && cont_area > 3000
	      	      && cont_area < 150000
	      && isContourConvex(Mat(approx))) {
	    
	    double s = 0;
	    for (int i = 2; i < 4; i++) {
	      double t = fabs(angle(approx[i], approx[i - 2], approx[i - 1]));
	      s = s > t ? s : t;
	    }
	    if (s < 0.3) {
	      for (int i = 0, ip=0; i < 4; i++, ip+=2 ) {
		dest_buf[ip] = approx[i].x;
		dest_buf[ip+1] = approx[i].y;
		aEnv->SetIntArrayRegion(result, 0, 8, (jint*)dest_buf);		
		//circle(*mat, approx[i], 5, Scalar(0.4823923), -1);
	      }
	      //LOGI("FOUND points %d %d", dest_buf[0], dest_buf[1]);
	    }
	  }
	}
	//t1 = now_ms();
	//time_dif = t1 - t0;
	//LOGI("Last ApproxPoly: %g ms", time_dif);
	//cv::findContours()


	//	t0 = now_ms();
	//	memcpy((unsigned int*) dest_buf, (unsigned int*) mat->data,
	//		mat->cols * mat->rows * 0x04);
	//t1 = now_ms();

	//time_dif = t1 - t0;
	//LOGI("Memcpy from Mat:"); // %g ms", time_dif);
	
	int j;
	double max_dist; 
	int max_indx;
	int tmp_res;
	int tmp_x, tmp_y;

	/*	LOGI("FOUND before [%d %d] [%d %d] [%d %d] [%d %d]", 
	     dest_buf[0], dest_buf[1],
	     dest_buf[2], dest_buf[3],
	     dest_buf[4], dest_buf[5],
	     dest_buf[6], dest_buf[7]);*/
	

	// sort the vertices descendently	
	if((sPrevData[0] != 0 && sPrevData[2] != 0 && sPrevData[4] != 0) 
	   && (dest_buf[0] != 0 && dest_buf[2] != 0 && dest_buf[4] != 0)) {

	  //find pivot ~ 1
	  i=0;
	  max_dist = sqrt(pow(dest_buf[i],2) + pow(dest_buf[i+1]));
	  max_indx = i;
	  
	  for(j=i+2;j<8;j+=2) {
	    tmp_res = sqrt(pow(dest_buf[j],2) + pow(dest_buf[j+1]));
	    if(tmp_res > max_dist) {
	      max_dist = tmp_res;
	      max_indx = j;		
	    }
	  }
	  
	  if(max_indx != i) {	
	    tmp_x = dest_buf[i];
	    tmp_y = dest_buf[i+1];
	    dest_buf[i] = dest_buf[max_indx];
	    dest_buf[i+1] = dest_buf[max_indx+1];
	    dest_buf[max_indx] = tmp_x;
	    dest_buf[max_indx+1] = tmp_y;
	      //LOGI("FOUND CHANGED [%d %d] [%d %d]", 
	      //   dest_buf[i], dest_buf[i+1],
	      //   dest_buf[min_indx], dest_buf[min_indx+1]);
	  }

	  //find best bottom ~ 2
	  i = 2;
	  max_dist = abs(dest_buf[i] - dest_buf[i+2]);
	  max_indx = i;
	  
	  for(j=i+4;j<8;j+=2) {
	    tmp_res = abs(dest_buf[j] - dest_buf[j+2]);
	    if(tmp_res < max_dist) {	      
	      tmp_res = max_dist;
	      max_indx = j;
	    }
	  }
	    
	  if(max_indx != i) {	
	    tmp_x = dest_buf[i];
	    tmp_y = dest_buf[i+1];
	    dest_buf[i] = dest_buf[max_indx];
	    dest_buf[i+1] = dest_buf[max_indx+1];
	    dest_buf[max_indx] = tmp_x;
	    dest_buf[max_indx+1] = tmp_y;	      
	  }

	  //find best far ~ 3
	  i = 4;
	  //TODO
	  max_dist = sqrt(pow(dest))
	  


	}
	memcpy(sPrevData, dest_buf, 8 * 0x04);
	
	/*
	LOGI("FOUND points [%d %d] [%d %d] [%d %d] [%d %d]", 
	     dest_buf[0], dest_buf[1],
	     dest_buf[2], dest_buf[3],
	     dest_buf[4], dest_buf[5],
	     dest_buf[6], dest_buf[7]); */

	delete mat;
	delete imgGray;

	LOGI("Copying array dest_buf");	
	aEnv->ReleaseByteArrayElements(aYUVPicture, src_buf, 0);
	
	//delete dest_buf;
	free(dest_buf);

	return result;
}

//=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
JNIEXPORT void JNICALL Java_my_exercise_cameraprocess_CameraProcessingView_finish
(JNIEnv *aEnv, jobject aObj) {

  delete sPrevData;
}


//=============================================================================
// jintArray tessInput(jbyteArray, int, int)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
JNIEXPORT void JNICALL 
Java_my_exercise_cameraprocess_NameRecognition_tessInput (JNIEnv * aEnv, 
							  jobject aObj, 
							  jbyteArray yuv420, 
							  jint width,
							  jint height,
							  jint anchorX,
							  jint anchorY,
							  jint new_width, 
							  jint new_height,
							  jintArray bgra) {

  jbyte *src_buf = (jbyte*) aEnv->GetByteArrayElements(yuv420, 0);
  jint  *dest_buf = (jint*) aEnv->GetIntArrayElements(bgra, 0);
  

  LOGI("PASS %d %d", width, height);

  toRGB( (const char*)src_buf, width, height, (unsigned int*)dest_buf );


  // clean up
  aEnv->ReleaseByteArrayElements(yuv420, src_buf, 0);
  aEnv->ReleaseIntArrayElements(bgra, dest_buf, 0);
 
}

//=============================================================================
// void threshold(jintArray, int, int, int, jintArray)
// The function for image correction, so that OCR is able to capture it
//=============================================================================
JNIEXPORT void JNICALL 
Java_my_exercise_cameraprocess_NameRecognition_threshold (JNIEnv * aEnv, 
							  jobject aObj, 
							  jintArray gray, 
							  jint width,
							  jint height,
							  jint level,
							  jintArray res) {

  jint *src_buf = (jint*) aEnv->GetIntArrayElements(gray, 0);
  jint  *dest_buf = (jint*) aEnv->GetIntArrayElements(res, 0);

  int length = width*height;
  int i;
  int val;

  // filter within threshold in ARGB sense (while R=G=B)
  // because it is grayscale only
  for(i=0; i < length; i++) {
    
    val = src_buf[i] & 0xff;
    if(val < level) {
      dest_buf[i] = 0xff000000;
    }
    else {
      dest_buf[i] = src_buf[i];
    }    
  }

  // clean up
  aEnv->ReleaseIntArrayElements(gray, src_buf, 0);
  aEnv->ReleaseIntArrayElements(res, dest_buf, 0);
}

// end of file
