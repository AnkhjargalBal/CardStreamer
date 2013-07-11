#include <string.h>
#include <stdlib.h>
/*#include "memcpy_neon.S"*/
#include "functions_ar.hpp"


//=============================================================================
// void toRGB(const char*, int, int, unsigned int*)
// Converts YUV4:2:0 (given as 1st param) into a RGB bitmap
//=============================================================================
void toRGB(const char* yuv420sp, int width, int height, unsigned int* rgb) {

  int frameSize = (int) (((width) * (height)));
  int j, yp, uvp, i, y, y1192, r, g, b, u, v;
  int y_gr;

  for (j = 0, yp = 0; j < height; j++) {
    uvp = frameSize + (j >> 1) * width;
    u = 0;
    v = 0;
    for (i = 0; i < width; i++, yp++) {

      y = (0xff & ((int) yuv420sp[yp])) - 16;
      if (y < 0)
	y = 0;

      if ((i & 1) == 0) {
	v = (0xff & yuv420sp[uvp++]) - 128;
	u = (0xff & yuv420sp[uvp++]) - 128;
      }

      y1192 = 1192 * y;
      r = (y1192 + 1634 * v);
      g = (y1192 - 833 * v - 400 * u);
      b = (y1192 + 2066 * u);

      if (r < 0)
	r = 0;
      else if (r > 262143)
	r = 262143;
      if (g < 0)
	g = 0;
      else if (g > 262143)
	g = 262143;
      if (b < 0)
	b = 0;
      else if (b > 262143)
	b = 262143;

      // put it to grayscale
      r = (int)(r*0.3f);
      g = (int)(g*0.59f);
      b = (int)(b*0.11f);
      y_gr = r+g+b;
			      			
      rgb[yp] = 0xff000000 | 
	((y_gr << 6) & 0xff0000) | 
	((y_gr >> 2) & 0xff00) | 
	((y_gr >> 10) & 0xff);
    }
  }

}

//=============================================================================
// void toRGBCropped(const char*, int, int, int, int, int, int, unsigned int*)
// Converts YUV4:2:0 (given as 1st param) into a RGB bitmap
// && crops the output based on anchor_x, anchor_y and new_width and new_height
//=============================================================================
void toRGBCropped(const char* yuv420sp, 
		  int width, int height, 
		  int anchor_x, int anchor_y, 
		  int new_width, int new_height, 
		  unsigned int* rgb) {
  
  int frameSize = (int) (((width) * (height)));
  int j, yp, uvp, i, y, y1192, r, g, b, u, v;
  int y_gr;

  int delta_width = width - (anchor_x + new_width);
  int delta_height = height - (anchor_y + new_height);

  for (j = anchor_y, yp = 0; j < (anchor_y + new_height); j++) {
    uvp = frameSize + (j >> 1) * width;
    uvp += anchor_x;
    yp += delta_width;
    u = 0;
    v = 0;
    for (i = anchor_x; i < (anchor_x + new_width); i++, yp++) {

      y = (0xff & ((int) yuv420sp[yp])) - 16;
      if (y < 0)
	y = 0;

      if ((i & 1) == 0) {
	v = (0xff & yuv420sp[uvp++]) - 128;
	u = (0xff & yuv420sp[uvp++]) - 128;
      }

      y1192 = 1192 * y;
      r = (y1192 + 1634 * v);
      g = (y1192 - 833 * v - 400 * u);
      b = (y1192 + 2066 * u);

      if (r < 0)
	r = 0;
      else if (r > 262143)
	r = 262143;
      if (g < 0)
	g = 0;
      else if (g > 262143)
	g = 262143;
      if (b < 0)
	b = 0;
      else if (b > 262143)
	b = 262143;

      // put it to grayscale
      r = (int)(r*0.3f);
      g = (int)(g*0.59f);
      b = (int)(b*0.11f);
      y_gr = r+g+b;
			     
			  
      rgb[yp] = 0xff000000 | 
	((y_gr << 6) & 0xff0000) | 
	((y_gr >> 2) & 0xff00) | 
	((y_gr >> 10) & 0xff);     
    }
  }
}
 

//=============================================================================
// void sobelScreen(char*, int, int)
// Applies Sobel filter on YUV4:2:0 single frame
//=============================================================================
int* sobelScreen(char* aInput, int aWidth, int aHeight) {
  int len = strlen(aInput);
  int* dest_buf = (int*) malloc(0x04 * ((aWidth * aHeight) + 1));
  //int* mod_inp = (unsigned int*) malloc(0x04 * ((aWidth * aHeight) + 1));

  int x, y, w = aWidth, pos = aWidth + 1;
  int maxX = aWidth - 1, maxY = aHeight - 1;
  int sobelX, sobelY, sobelFinal;

  
  for (y = 1; y < maxY; y++, pos += 2) {
    for (x = 1; x < maxX; x++, pos++) {
      sobelX = aInput[pos + w + 1] - aInput[pos + w - 1] + aInput[pos + 1]
	+ aInput[pos + 1] - aInput[pos - 1] - aInput[pos - 1]
	+ aInput[pos - w + 1] - aInput[pos - w - 1];

      sobelY = aInput[pos + w + 1] + aInput[pos + w] + aInput[pos + w]
	+ aInput[pos + w - 1] - aInput[pos - w + 1]
	- aInput[pos - w] - aInput[pos - w] - aInput[pos - w - 1];
      sobelFinal = (sobelX + sobelY) >> 1;
      if (sobelFinal < 48)
	sobelFinal = 0; //pix_RGB565(aInput[x * y], aInput[pos]) & 0xff;
      if (sobelFinal >= 48)
	sobelFinal = 255;
      dest_buf[pos] = (sobelFinal << 0) | (sobelFinal << 8)
	| (sobelFinal << 16) | (sobelFinal << 24);
    }
  }

  return dest_buf;
}

// End of file
