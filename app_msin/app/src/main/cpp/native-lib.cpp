#include <jni.h>
#include <string>

extern "C"
jstring
Java_projeto3msin_app_1msin_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
