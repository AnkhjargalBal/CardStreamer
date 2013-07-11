APP_STL := gnustl_static
APP_PROJECT_PATH := $(call my-dir)/..
#uncomment for lower versions of ARM CPU
APP_ABI := armeabi-v7a   #armeabi
APP_PLATFORM := android-17
#add new modules here
APP_MODULES      := JniAr JniWrapper JniVideo
APP_BUILD_SCRIPT := $(APP_PROJECT_PATH)/jni/Android.mk
APP_CPPFLAGS := -frtti -fexceptions
