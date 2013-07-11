#ifndef __MY_EXERCISE_JNIWRAPPER_FUNCTIONS_H__
#define __MY_EXERCISE_JNIWRAPPER_FUNCTIONS_H__

/*Use this when handling complex OpenGL objects*/
typedef struct {
	/* Vertex array and color array are enabled for all objects, so their
	 * pointers must always be valid and non-NULL. Normal array is not
	 * used by the ground plane, so when its pointer is NULL then normal
	 * array usage is disabled.
	 *
	 * Vertex array is supposed to use GL_FIXED datatype and stride 0
	 * (i.e. tightly packed array). Color array is supposed to have 4
	 * components per color with GL_UNSIGNED_BYTE datatype and stride 0.
	 * Normal array is supposed to use GL_FIXED datatype and stride 0.
	 */
	GLubyte *vertexArray;
	GLubyte *colorArray;
	GLubyte *trianglesArray;
	GLfixed *normalArray;
	GLint vertexComponents;
	GLsizei count;
} GLOBJECT;


typedef struct {
	float x, y, z;
} VECTOR3;



/**
 * appInit
 * initializes gl
 */
GLuint appInit();

/**
 * appAddInit
 * @param1: width
 * @param2: height
 */
void appAddInit(int, int);


/**
 * appDeinit
 * function called from a destructor
 */
void appDeinit();

/**
 * Logs the last error
 * after it is called
 */
void lastError();

/**
 * appRender
 * @param1: frequency of the rendering of a frame
 * @param2: texture
 */
void appRender();


/**
 * freeGLObject
 * @param1: object to free from memory
 */
static void freeGLObject(GLOBJECT *);

/**
 * toRGB
 * @param1: input yuv4221 frame
 * @param2: width
 * @param3: height
 * @param4: output RGB(A) frame
 * @param5: crop to output width
 * @param6: crop to output height
 */
void toRGB( const char*, int, int, unsigned int*, int, int);


/**
 * prepareFrame
 * loads matrixes for the frame
 * @param1: width of the frame
 * @param2: height of the frame
 */
static void prepareFrame(int , int );


/**
 * OpenCV data ??
 * converts OpenCV coords into OpenGL ones
 * @param1: array of coordinates (4 points ~ [x0,y0,x1,y1,x2,y2,x3,y3]
 * @sideeffect: vertices are set accordingly
 */
void prepareCoordinates(int* );

void minDistanceCoordinates(int*);

/**
 * Sort OpenCV data 
 */
static void sortCoordinates(int*);

/**
 * Compares 2 Carteasian coordinates
 * Coordinate#1: @param1: x; @param2: y
 * Coordinate#2: @param3: x; @param4: y
 * @return: 0 if distance is equal;
 * -1 if #1 is lesser than #2
 * 1 if #1 is greater than #2
 */
static int compareCoordinates(int , int , int , int );


/**
 * convexCoordinates
 * @param1: data to sort in convexity matter
 */
/*static void convexCoordinates(int*);*/

/**
 * This will be changed based on an output from OpenCV
 */
static GLfloat vertices[] = {
        /* top */
        -1.0f, -1.0f, 0.0f,
	1.0f, -1.0f, 0.0f,
	-1.0f, 1.0f, 0.0f,
	1.0f, 1.0f, 0.0f
};

/**
 * Static structures
 */
static GLfloat prevVertices[] = {
  	-1.0f, -1.0f, 0.0f,
	1.0f, -1.0f, 0.0f,
	-1.0f, 1.0f, 0.0f,
	1.0f, 1.0f, 0.0f
};


static const GLubyte triangles[] = {
	     1,0,3,
	     1,3,2
};


static const GLfloat texCoords[] = {
		0.1f, 0.1f,
		0.9f, 0.1f,
		0.1f, 0.9f,
		0.9f, 0.9f
};


#endif /* __MY_EXERCISE_JNIWRAPPER_FUNCTIONS_H__ */
