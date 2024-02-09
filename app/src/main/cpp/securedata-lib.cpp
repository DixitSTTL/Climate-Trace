//
// Created by 23082103 on 06-02-2024.
//
#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_app_climate_1trace_utils_StaticData_getBaseURL(JNIEnv *env, jobject thiz) {
    std::string string = "https://api.climatetrace.org/v4/";
    return env->NewStringUTF(string.c_str());
}