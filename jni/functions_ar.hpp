#ifndef __JNI_WRAPPER_FUNCTIONS_PIKA__
#define __JNI_WRAPPER_FUNCTIONS_PIKA__

#include <stdlib.h>

#ifdef __cplusplus
extern "C" {
#endif


  // convert YUV image to RGB
  void toRGB( const char*, int, int, unsigned int*);  

  // unsafe
  void toRGBCropped( const char*, int, int, int, int, int, int, unsigned int*);

  // apply the Sobel effect on a given picture (frame)
  int* sobelScreen(char*, int, int);

#ifdef __cplusplus
}
#endif

#endif
