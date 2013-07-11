#ifndef DISABLE_IMPORTGL

#ifdef LINUX
#include <stdlib.h>
#include <dlfcn.h>
static void *sGLESSO = NULL;
#endif // LINUX

#endif /* DISABLE_IMPORTGL */


#define IMPORTGL_NO_FNPTR_DEFS
#define IMPORTGL_API
#define IMPORTGL_FNPTRINIT = NULL
#include "importgl.h"

/* Imports function pointers to selected function calls in OpenGL ES Common
 * or Common Lite profile DLL or shared object. The function pointers are
 * stored as global symbols with equivalent function name but prefixed with
 * "funcPtr_". Standard gl/egl calls are redirected to the function pointers
 * with preprocessor macros (see importgl.h).
 */
int importGLInit() {
	int result = 1;

#ifndef DISABLE_IMPORTGL

#undef IMPORT_FUNC

#ifdef ANDROID_NDK
	sGLESSO = dlopen("libGLESv1_CM.so", RTLD_NOW);

	if (sGLESSO == NULL)
		return 0; // Cannot find OpenGL ES Common or Common Lite SO.

#define IMPORT_FUNC(funcName) do { \
        void *procAddress = (void *)dlsym(sGLESSO, #funcName); \
        if (procAddress == NULL) result = 0; \
        *((void **)&FNPTR(funcName)) = procAddress; } while (0)
#endif // ANDROID_NDK

	IMPORT_FUNC(glBlendFunc);
	IMPORT_FUNC(glClear);
	IMPORT_FUNC(glClearColorx);
	IMPORT_FUNC(glColor4x);
	IMPORT_FUNC(glColorPointer);
	IMPORT_FUNC(glDisable);
	IMPORT_FUNC(glDisableClientState);
	IMPORT_FUNC(glDrawArrays);
	IMPORT_FUNC(glEnable);
	IMPORT_FUNC(glEnableClientState);
	IMPORT_FUNC(glFrustumx);
	IMPORT_FUNC(glGetError);
	IMPORT_FUNC(glLightxv);
	IMPORT_FUNC(glLoadIdentity);
	IMPORT_FUNC(glMaterialx);
	IMPORT_FUNC(glMaterialxv);
	IMPORT_FUNC(glMatrixMode);
	IMPORT_FUNC(glMultMatrixx);
	IMPORT_FUNC(glNormalPointer);
	IMPORT_FUNC(glPopMatrix);
	IMPORT_FUNC(glPushMatrix);
	IMPORT_FUNC(glRotatex);
	IMPORT_FUNC(glScalex);
	IMPORT_FUNC(glShadeModel);
	IMPORT_FUNC(glTranslatex);
	IMPORT_FUNC(glVertexPointer);
	IMPORT_FUNC(glViewport);

#endif /* DISABLE_IMPORTGL */

	return result;
}

void importGLDeinit() {
#ifndef DISABLE_IMPORTGL
#ifdef LINUX
    dlclose(sGLESSO);
#endif /* LINUX */
#endif /* DISABLE_IMPORTGL */
}
