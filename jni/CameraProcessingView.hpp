#include <jni.h>
/* Header for class my_exercise_Pikachu_JniWrapper */

#ifndef _Included_my_exercise_Pikachu_JniWrapper
#define _Included_my_exercise_Pikachu_JniWrapper


#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     my_exercise_cameraprocess_CameraProcessingView
 * Method:    coords
 * Signature: ()Ljava/lang/int[];
 */
  JNIEXPORT jintArray JNICALL Java_my_exercise_cameraprocess_CameraProcessingView_coords
  (JNIEnv *, jobject, jbyteArray, jint, jint);

/*
 * Class:     my_exercise_cameraprocess_CameraProcessingView
 * Method:    finish
 * Signature: void;
 */
  JNIEXPORT void JNICALL Java_my_exercise_cameraprocess_CameraProcessingView_finish
  (JNIEnv *, jobject);

/*
 * Class:     my_exercise_cameraprocess_CameraProcessingView
 * Method:    tessInput
 * Signature: void;
 */
  JNIEXPORT void JNICALL Java_my_exercise_cameraprocess_NameRecognition_tessInput 
  (JNIEnv *, jobject, jbyteArray, jint, jint, jint, jint, jint, jint, jintArray);
/*
 * Class:     my_exercise_cameraprocess_CameraProcessingView
 * Method:    threshold
 * Signature: void;
 */
  JNIEXPORT void JNICALL Java_my_exercise_cameraprocess_NameRecognition_threshold
  (JNIEnv *, jobject, jintArray, jint, jint, jint, jintArray);

  /**
   * returns current system time
   * for testing purposes
   */
  static double now_ms(void);

#ifdef __cplusplus
}
#endif

#endif
