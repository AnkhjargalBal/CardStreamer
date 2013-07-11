LOCAL_PATH := $(call my-dir)

######################################################OPENCV
include $(CLEAR_VARS)

LOCAL_MODULE := functions_ar
LOCAL_SRC_FILES := functions_ar.cpp \

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)
#OPENCV_CAMERA_MODULES:=off
OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=SHARED
LOCAL_ARM_NEON := true

#include OpenCV native libraries
include ../opencv/jni/OpenCV.mk

LOCAL_MODULE := JniAr
LOCAL_LDLIBS :=  -llog -ldl -ljnigraphics -landroid
LOCAL_STATIC_LIBRARIES := functions_ar
LOCAL_SRC_FILES := CameraProcessingView.cpp \

include $(BUILD_SHARED_LIBRARY)


include $(all-subdir-makefiles)


######################################################OPENMAXAL
include $(CLEAR_VARS)

LOCAL_MODULE    := JniVideo
LOCAL_SRC_FILES := native-media-jni.c
# for native multimedia
LOCAL_LDLIBS    += -lOpenMAXAL
# for logging
LOCAL_LDLIBS    += -llog
# for native windows
LOCAL_LDLIBS    += -landroid
LOCAL_CFLAGS    += -UNDEBUG
include $(BUILD_SHARED_LIBRARY)

##########CORE MODULE
include $(CLEAR_VARS)

LOCAL_MODULE := JniWrapper
LOCAL_LDLIBS := -lGLESv1_CM -ldl -llog -lz
#LOCAL_STATIC_LIBRARIES :=  libavformat libavcodec libpostproc libswscale libavutil
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/ffmpeg
LOCAL_CFLAGS := -DANDROID_NDK  \
		-DDISABLE_IMPORTGL              

LOCAL_SRC_FILES := \
    importgl.c \
    functions.c \
    JniWrapper.c \

include $(BUILD_SHARED_LIBRARY)



LOCAL_PATH := $(call my-dir)
LOCAL_C_INCLUDES += $(LOCAL_PATH)/ffmpeg


include $(all-subdir-makefiles)



