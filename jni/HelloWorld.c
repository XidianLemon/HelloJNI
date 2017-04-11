#include <jni.h>

Java_com_example_helloworld_MainActivity_stringFromJni( JNIEnv* env,
                                                  jobject thiz )
{
    return (*env)->NewStringUTF(env, "Hello from JNI !");
}
