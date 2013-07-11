#include <jni.h>
#include <android/log.h>
#include <stdlib.h>
#include <stdio.h>


//#include "VideoFrameUnit.h"
#include "JniWrapper.h"
#include "importgl.h"
#include "functions.h"


static int sWindowWidth = 320;
static int sWindowHeight = 480;

#define  LOG_TAG    "JniWrapper"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

JNIEXPORT jint JNICALL Java_my_exercise_opengl_JniWrapper_nativeInit
(JNIEnv * aEnv, jclass aObj) {
  //TODO return Texture Id
	importGLInit();
	return appInit();	
}

JNIEXPORT void JNICALL Java_my_exercise_opengl_JniWrapper_nativeRender
(JNIEnv * aEnv, jclass aObj, jint aTexture) {

	appRender();
}

JNIEXPORT void JNICALL Java_my_exercise_opengl_JniWrapper_nativeResize
(JNIEnv * aEnv, jclass aObj, jint aWidth, jint aHeight) {

	sWindowWidth = aWidth;
	sWindowHeight = aHeight;
	float ratio = (float)aWidth / (float) aHeight;
	//LOGI("Aspect ratio is %d / %d: %f\n", aWidth, aHeight, ratio);
	appAddInit(aWidth, aHeight);
}

/*
 * Class:     my_exercise_opengl_JniWrapper
 * Method:    nativeDone
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_my_exercise_opengl_JniWrapper_nativeDone
(JNIEnv * aEnv, jclass aObj) {

  LOGI("GL Done");
  appDeinit();
  importGLDeinit();
}


/*
 * Class:     my_exercise_opengl_JniWrapper
 * Method:    feedPosition
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_my_exercise_opengl_JniWrapper_feedPosition
(JNIEnv *aEnv, jclass aObj, jintArray aCoords) {
  	
       	jint* coords = (jint*)(*aEnv)->GetIntArrayElements(aEnv, aCoords, NULL);
	
	prepareCoordinates((int*)coords );

	(*aEnv)->ReleaseIntArrayElements(aEnv, aCoords, coords, 0);	
}




// end of file
