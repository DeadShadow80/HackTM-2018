#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_fun_night_1gaming_outer_outer_MainActivityPage_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
