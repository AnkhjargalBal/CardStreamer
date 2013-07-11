#include <android/log.h>
#include <stdlib.h>
#include <math.h>


#include "my_exercise_opengl_JniWrapper.h"
#include "importgl.h"
#include "functions.h"

// DEFINITIONS
#define PI 3.1415926535897932f
#define POLYGON_SELECTION 1
#define OBJECT_SELECTION  2
#define FRUSTUM_LEFT   -1.f     //left vertical clipping plane
#define FRUSTUM_RIGHT   1.f     //right vertical clipping plane
#define FRUSTUM_BOTTOM -1.f     //bottom horizontal clipping plane
#define FRUSTUM_TOP     1.f     //top horizontal clipping plane
#define FRUSTUM_NEAR    3.f     //near depth clipping plane
#define FRUSTUM_FAR  1000.f     //far depth clipping plane
#define GL_TEXTURE_EXTERNAL_OES  0x8D65


// GLOBALS
static int sWidth;
static int sHeight;
static float sAspectRatio;
static int sFrame = 0;

static void* sTextureData = NULL;
static int sTextureWidth;
static int sTextureHeight;
static GLuint sTextureId;
//static GLfloat* sPrevVertices;
static GLfloat* sDeltaVertices;


#define  LOG_TAG    "JniWrapper"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

//=============================================================================
// void prepareCoordinates()
// Is called by upper class whenever the coordinates' event is raised
//=============================================================================
void prepareCoordinates(int* data) {
  //Transform OpenCV coords into OpenGL vertices
  int i = 0;
  
  for(i=0; i<12; i++) {
    prevVertices[i] = vertices[i];
  }

  sortCoordinates(data);

  // 650 / 330
  const int aspect = 440;
  const int aspect_y = 490;
  const int frame_rate = 25;

  //LOGI("Deltab: %g %g",  vertices[0], vertices[1]);

  // #1
  // x
  vertices[0] = ((GLfloat)data[0]) / aspect;
  
  sDeltaVertices[0] = (vertices[0] - prevVertices[0]) / frame_rate;
  // y
  vertices[1] = ((GLfloat)data[1]) / aspect_y;
  
  sDeltaVertices[1] = (vertices[1] - prevVertices[1]) / frame_rate;
  

  // #2
  // x
  vertices[3] = ((GLfloat)data[2]) / aspect;
  
  sDeltaVertices[3] = (vertices[3] - prevVertices[3]) / frame_rate;
  // y
  vertices[4] = ((GLfloat)data[3]) / aspect_y;
  
  sDeltaVertices[4] = (vertices[4] - prevVertices[4]) / frame_rate;
  


  // #3
  // x
  vertices[6] = ((GLfloat)data[4]) / aspect;
  
  sDeltaVertices[6] = (vertices[6] - prevVertices[6]) / frame_rate;
  // y
  vertices[7] = ((GLfloat)data[5]) / aspect_y;
  
  sDeltaVertices[7] = (vertices[7] - prevVertices[7]) / frame_rate;
  
  
  // #4
  // x
  vertices[9] = ((GLfloat)data[6]) / aspect;
  
  sDeltaVertices[9] = (vertices[9] - prevVertices[9]) / frame_rate;
  // y
  vertices[10] = ((GLfloat)data[7]) / aspect_y;
  
  sDeltaVertices[10] = (vertices[10] - prevVertices[10]) / frame_rate;

  /*
 LOGI("SORT final [%.3g %.3g] [%.3g %.3g] [%.3g %.3g] [%.3g %.3g]", 
       vertices[0], vertices[1],
       vertices[2], vertices[3],
       vertices[4], vertices[5],
       vertices[6], vertices[7]);*/

 //  LOGI("Deltas: %g %g", vertices[0], vertices[1]);

  sFrame = 0;
 
  lastError();
}



//=============================================================================
// int compareCoordinates()
// Compares 2 coordinates 
//=============================================================================
static int compareCoordinates(int x1, int y1, int x2, int y2) {

  int d1, d2;

  d1 = sqrt(pow(x1,2) + pow(y1,2)) ;
  d2 = sqrt(pow(x2,2) + pow(y2,2)) ;

  if(d1 > d2)
    return 1;
  else if(d1 < d2)
    return -1;
  else
    return 0;
}


//=============================================================================
// void sortCoordinates()
// Sortes OpenCV coords
//=============================================================================
static void sortCoordinates(int* data) {
  int i,j;
  int min_dist;
  int min_indx;
  int tmp_res;
  int tmp_x, tmp_y;

  /*LOGI("SORT before [%d %d] [%d %d] [%d %d] [%d %d]", 
       data[0], data[1],
       data[2], data[3],
       data[4], data[5],
       data[6], data[7]);*/
  
  for(i=0; i < 8; i+=2) {
    min_dist = sqrt(pow(data[i],2) + pow(data[i+1],2));
    min_indx = i;
  
    for(j = i + 2; j < 8; j+=2) {
      tmp_res = sqrt(pow(data[j],2) + pow(data[j+1],2));
      if(min_dist > tmp_res) {
	min_dist = tmp_res;
	min_indx = j;
      }
    }

    if(min_indx != i) {
      tmp_x = data[i];
      tmp_y = data[i+1];
      data[i] = data[min_indx];
      data[i+1] = data[min_indx+1];
      data[min_indx] = tmp_x;
      data[min_indx+1] = tmp_y;
    }
  }

  /*LOGI("SORT after [%d %d] [%d %d] [%d %d] [%d %d]", 
       data[0], data[1],
       data[2], data[3],
       data[4], data[5],
       data[6], data[7]);*/       
}

//=============================================================================
// void appDeInit()
// Is called by destructor
//=============================================================================
static void freeGLObject(GLOBJECT *object) {
  if (object == NULL)
    return;
  free(object->normalArray);
  free(object->colorArray);
  free(object->vertexArray);
  free(object);
}

//=============================================================================
// void appInit()
// Initializes the environment, the projection
//=============================================================================
GLuint appInit() {

  glClearColor(0.f, 0.f, 0.f, 0.f);

  glEnable(0x8D65);

  // transparency
  glEnable(GL_BLEND);
  glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

  glEnableClientState(GL_VERTEX_ARRAY);

  glEnableClientState(GL_TEXTURE_COORD_ARRAY); 

  glGenTextures(1, &sTextureId);
  //GL_TEXTURE_EXTERNAL_OES
  // for using internal textures use GL_TEXTURE_2D instead
  glBindTexture(0x8D65, sTextureId);

  glTexParameteri(0x8D65, GL_TEXTURE_WRAP_S, GL_REPEAT);
  glTexParameteri(0x8D65, GL_TEXTURE_WRAP_T, GL_REPEAT);
  glTexParameterf(0x8D65, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
  glTexParameterf(0x8D65, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

  // this needs to be set up when getting texture from ffmpeg
  glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);

  glTexCoordPointer(2, GL_FLOAT, 0, texCoords);

  sFrame = 0;

  sDeltaVertices = (GLfloat*)calloc(12,sizeof(GLfloat));

  return sTextureId;
}


//=============================================================================
// void appAddInit()
// Initializes the rest of the environment, when appInit() is done
//=============================================================================
void appAddInit(int width, int height) {
  sWidth = width;
  sHeight = height;
  sAspectRatio = (float) width / (float) height; 

  glViewport(0, 0, sWidth, sHeight);

  sTextureWidth = 256;
  sTextureHeight = 256;

  sTextureData = (char*) calloc(sTextureWidth * sTextureHeight * 4, sizeof(char));

  // use 0x8d65 as the target
  glTexImage2D(0x8D65, 0, GL_RGBA, sTextureWidth, sTextureHeight, 0,
	       GL_RGBA, GL_UNSIGNED_BYTE, (GLvoid*)sTextureData);
}

//=============================================================================
// void appDeInit()
// Is called by destructor
//=============================================================================
void appDeinit() {
  int a;
  glDeleteTextures(1, &sTextureId);
  free(sTextureData);
}


//=============================================================================
// voif lastError()
// Logs the last known error from within the GL context
//=============================================================================
void lastError() {
  const char* opengl = "OpenGL";
  switch (glGetError()) {
  case GL_NO_ERROR:
    LOGI("OGL No error occured", opengl);
    break;
  case GL_INVALID_ENUM:
    LOGI("OGL Invalid enum", opengl);
    break;
  case GL_INVALID_VALUE:
    LOGI("OGL Invalid value", opengl);
    break;
  case GL_INVALID_OPERATION:
    LOGI("OGL Invalid operation", opengl);
    break;
  case GL_STACK_OVERFLOW:
    LOGI("OGL Stack overflow", opengl);
    break;
  case GL_STACK_UNDERFLOW:
    LOGI("OGL Stack underflow", opengl);
    break;
  case GL_OUT_OF_MEMORY:
    LOGI("OGL Out of memory", opengl);
    break;
  }
}



//=============================================================================
// static void prepareFrame(int, int)
// Loads identities and matrixes for the frame
//=============================================================================
static void prepareFrame(int width, int height) {

  glViewport(0, 0, width, height);

  glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);

  // make the surface transparent with the 4th param
  glColor4f(1.0f,1.0f,1.0f,0.8f);

  glLoadIdentity();

  glOrthof(0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f);

  if (sAspectRatio > 0.25f && sAspectRatio < 0.85f) {
    glScalef(1.0f, sAspectRatio, 1.0f);    
  }

  glTranslatef(1.0f, 0.0f, 0.0f);
  glRotatef(90.0f, 0.0f, 0.0f, 1.0f);

  if(sFrame < 200)
    ++sFrame;

  // move vertices a tick towards the new coordinates    
  vertices[0] = prevVertices[0] + (sDeltaVertices[0]*sFrame);
  vertices[1] = prevVertices[1] + (sDeltaVertices[1]*sFrame);

  vertices[3] = prevVertices[3] + (sDeltaVertices[3]*sFrame);
  vertices[4] = prevVertices[4] + (sDeltaVertices[4]*sFrame);

  vertices[6] = prevVertices[6] + (sDeltaVertices[6]*sFrame);
  vertices[7] = prevVertices[7] + (sDeltaVertices[7]*sFrame);

  vertices[9] = prevVertices[9] + (sDeltaVertices[9]*sFrame);
  vertices[10] = prevVertices[10] + (sDeltaVertices[10]*sFrame);
}

//=============================================================================
// void appRender(GLOBJECT*)
// Draws the object given as the parameter
//=============================================================================
void appRender() {

  prepareFrame(sWidth, sHeight);    
      
  glVertexPointer(3, GL_FLOAT, 0, vertices);
  
  glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);	
}


// End of file
