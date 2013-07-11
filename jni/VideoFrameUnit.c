#include <jni.h>
#include <android/log.h>
#include <stdlib.h>
#include <stdio.h>

#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#include "libswscale/swscale.h"
#include "libavutil/avutil.h"


#include "VideoFrameUnit.h"
#include "importgl.h"
#include "functions.h"


/*STATIC vars*/
static int sFrameWidth = 320;
static int sFrameHeight = 480;
static AVFormatContext *pFormatCtx;
static AVCodecContext  *pCodecCtx;
static struct SwsContext *img_convert_ctx;
static int videoStream;
static int numBytes;
static uint8_t *buffer;
static AVCodec *pCodec;
static AVFrame *pFrame;
static AVFrame *pFrameRGB;
static int frameCount = 0;
static int64_t avT = 0;

#define  LOG_TAG    "JniVideo"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

#define true 1
#define false 0

typedef uint8_t bool;

static double now_ms(void)
{
    struct timespec res;
    clock_gettime(CLOCK_REALTIME, &res);
    return 1000.0*res.tv_sec + (double)res.tv_nsec/1e6;
}


void clipFrame(const int* origFrame, int width, int height, 
	       int* desFrame, int desWidth, int desHeight) {

  int frameSize = (int) (((width) * (height)));
  int j, yp, uvp, i, y, y1192;

  int dif = width - desWidth;

  for (j = 0, yp = 0; j < desHeight; j++) {	
    for (i = 0; i < desWidth; i++, yp++) {

      desFrame[yp] = ((int) origFrame[yp+j*dif]);

    }
  }

}

JNIEXPORT void JNICALL Java_my_exercise_videoprocess_VideoFrameUnit_initVideoCtx(JNIEnv *env, jobject thiz) {

   av_register_all();

}

JNIEXPORT jint JNICALL Java_my_exercise_videoprocess_VideoFrameUnit_logFileInfo
(JNIEnv * env, jobject thiz, jstring filename, jint width, jint height) {
  // av_register_all();
    
   
    const jbyte *str;
    int i;
    str = (*env)->GetStringUTFChars(env, filename, NULL);

    if(av_open_input_file(&pFormatCtx, str, NULL, 0, NULL)!=0) {
        LOGE("Can't open file '%s'\n", str);
        return 1;
    }
    else {
        LOGI("File was opened\n");
        LOGI("File '%s', Codec %s",
            pFormatCtx->filename,
            pFormatCtx->iformat->name
        );
    }

    // Retrieve stream information
    if(av_find_stream_info(pFormatCtx)<0) {
      LOGI("VideoStream not fetched\n");
      return -1; // Couldn't find stream information
    }

    LOGI("VideoStream fetched\n");
  
    // Dump information about file onto standard error
    av_dump_format(pFormatCtx, 0, str, 0);
  
    // Find the first video stream
    videoStream=-1;
    for(i=0; i<pFormatCtx->nb_streams; i++)
      if(pFormatCtx->streams[i]->codec->codec_type ==AVMEDIA_TYPE_VIDEO) {
	videoStream=i;
	break;
      } 
    
    if(videoStream==-1)
      return -1; // Didn't find a video stream

    // Get a pointer to the codec context for the video stream
    pCodecCtx=pFormatCtx->streams[videoStream]->codec;
   
    // Find the decoder for the video stream
    pCodec=avcodec_find_decoder(pCodecCtx->codec_id);
    if(pCodec==NULL) {
      //fprintf(stderr, "Unsupported codec!\n");
      return -1; // Codec not found
    }
    // Open codec
    if(avcodec_open(pCodecCtx, pCodec)<0)
      return -1; // Could not open codec

    
    // Allocate video frame
    pFrame=avcodec_alloc_frame();
  
    // Allocate an AVFrame structure
    pFrameRGB=avcodec_alloc_frame();
    if(pFrameRGB==NULL)
      return -1;
  
    // Determine required buffer size and allocate buffer
    numBytes=avpicture_get_size(PIX_FMT_RGB32, 
				width, //pCodecCtx->width,
				height); //pCodecCtx->height);

    LOGI("Determined size %d %d %d\n",width, height, numBytes);
    buffer= (uint8_t *)av_malloc((numBytes/4)*sizeof(uint32_t));
   
    // Assign appropriate parts of buffer to image planes in pFrameRGB
    // Note that pFrameRGB is an AVFrame, but AVFrame is a superset
    // of AVPicture
    avpicture_fill((AVPicture *)pFrameRGB, buffer, PIX_FMT_RGB32,
		   //pCodecCtx->width, pCodecCtx->height);
		   width, height);
  

    //sFrameWidth = width;
    //sFrameHeight = height;

    //width = pCodecCtx->width;
    //height = pCodecCtx->height;

    img_convert_ctx = sws_getContext(pCodecCtx->width, 
				     pCodecCtx->height, 
				     pCodecCtx->pix_fmt, 
				     width, 
				     height, 
				     PIX_FMT_RGB32, 
				     SWS_BICUBIC,
				     NULL, 
				     NULL, 
				     NULL);
    // Read frames and save first five frames to disk
   

    LOGI("Finished reading the stream");

    
    return 0;
}


JNIEXPORT jintArray JNICALL Java_my_exercise_videoprocess_VideoFrameUnit_getVideoFrame
(JNIEnv * env, jobject thiz) {
  
  int frameFinished = 0;
  AVPacket packet;
  jintArray result;
  
  int len = 0;
  int i = 0;
  int ret = 0;
  double t0, t1, time_dif;
  result = (*env)->NewIntArray(env, numBytes / 4);      
  t0 =  now_ms();

  while( ret = av_read_frame(pFormatCtx, &packet) >= 0) {    
    //while( av_seek_frame_generic(pCodecCtx, ) ) {

    if(ret == AVERROR(EAGAIN)){     
      continue;
    }

    // Is this a packet from the video stream?
    if(packet.stream_index==videoStream) {
      
      
      // Decode video frame  
      t1 = now_ms();
      time_dif = t1 - t0;
      LOGI("VIDEOTIME before %g ms",time_dif);
      len = avcodec_decode_video2(pCodecCtx, pFrame, &frameFinished, &packet);
      t1 = now_ms();
      time_dif = t1 - t0;
      LOGI("VIDEOTIME after %g ms",time_dif);
      if ( len < 0 ) {
	//Problems decoding frame
	return NULL;
      }      
      //LOGI("Frame decoded %d\n", len);             
      // Did we get a video frame?
      if(frameFinished) {
	++frameCount;        	        

	sws_scale(img_convert_ctx, 
		  (const uint8_t* const*)pFrame->data, 
		  pFrame->linesize, 
		  0, 
		  pCodecCtx->height, 
		  pFrameRGB->data,
		  pFrameRGB->linesize);  
	//LOGI("Scaling finished\n"); 
	break;
      }
    }       
    // Free the packet that was allocated by av_read_frame
    av_free_packet(&packet);
    i++;
  }
 
  if(len != 0) {
    //LOGI("Set region %d\n", i);
    (*env)->SetIntArrayRegion(env, result, 0, 65535, (jint*)pFrameRGB->data[0]);
    t1 = now_ms();
    time_dif = t1 - t0;
    LOGI("VIDEOTIME END %g ms",time_dif);
  }    
  return result;
}


JNIEXPORT void JNICALL Java_my_exercise_videoprocess_VideoFrameUnit_freeVideoCtx
(JNIEnv * env, jobject thiz) {

  // Free the RGB image
  av_free(buffer);
  av_free(pFrameRGB);
  
  // Free the YUV frame
  av_free(pFrame);
  
  // Close the codec
  avcodec_close(pCodecCtx);
  
  // Close the video file
  av_close_input_file(pFormatCtx); 
}



JNIEXPORT void JNICALL Java_my_exercise_videoprocess_VideoFrameUnit_rgb565ToRgba
  (JNIEnv * aEnv, jclass aObj, jintArray aInRGB565, jintArray aOutRGBA8888, jint aLength) {


	int* body = (int*) (*aEnv)->GetPrimitiveArrayCritical(aEnv, aInRGB565, 0);
	int* rgba = (int*) (*aEnv)->GetPrimitiveArrayCritical(aEnv, aOutRGBA8888, 0);

	int i = 0;


	unsigned int r, g, b, a;

	for(i = 0; i < aLength; ++i, ++body) {

		r=((*body >> 11) & 0xff); // can mask everything
		g=((*body >> 5) & 0x3f); // mask 6bits
		b=((*body >> 0) & 0x1f); // mask 5bits

		*rgba++ = 0xff000000 | (b << 16) | (g << 8) | r;
	}

	(*aEnv)->ReleasePrimitiveArrayCritical(aEnv, aInRGB565, body, 0);

	(*aEnv)->ReleasePrimitiveArrayCritical(aEnv, aOutRGBA8888, rgba, 0);
}
