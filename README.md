# ˵��

����һ��ʹ��ndk��ʾHelloWorld��Demo��

------------

## ��������

��ʹ�õ�IDE��Eclipse����׿�汾��5.0�����ֻ����ں���arm�ġ�jdk�汾��1.8.0_77��ndk�汾��r10b��
�ڲ��𻷾�֮ǰ�����Ǽ�����������Ѿ�����ndk�����Ұ�װ���˷�����Ŀ¼�С����windows - preferences������ndk��·����
![](./images/ndklujing.png)

## ��ϸ�̳�

###��һ�����½���׿�����Ӧ��׿�汾��Ĭ�ϵ���û���޸ġ�
![](./images/xinjian.png)

ֻ��һ������MainActivity������Ҳ�ܼ򵥣�һ��buttonһ��textview������ʵ�ֵľ��ǵ��button��textview�����c��������string��
``` java
package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btn;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_main);
	     btn = (Button) findViewById(R.id.button);
	     tv = (TextView) findViewById(R.id.text);
	     btn.setOnClickListener(new View.OnClickListener() {

	         @Override
	         public void onClick(View v) {
	             // TODO Auto-generated method stub
	             tv.setText(stringFromJni());
	         }
	     });
	 }
	 private native String stringFromJni();
	
	 static {
	     System.loadLibrary("HelloWorld");
	 }
}
```
����̣ܶ��������ں�����findviewbyid�Ͳ�˵�ˣ��ص�����private native String stringFromJni();��������һ��native������String���͵ģ���������Ϊ�գ���������ľ���ʵ������c�Ǳ�ʵ�ֵģ�������һ��Ҫ��ס����Ϊ��c�Ǳ�Ҫ�����Ӧ��
static {
	System.loadLibrary("HelloWorld");
}
������˼�ǵ�����c��ʱ�򣬵��õĲ�����д��.c�ļ������ǹ���Ŀ¼�µ�һ����obj�ļ����е�.so��⣬���.so�������־ͽ�System.loadLibrary("");�������еģ���������.so������־ͽ�HelloWorld.so��
����ڵ���¼��У�setText��������Ĳ����������native������

###�ڶ������Ҽ���Ŀ��HelloWorld - new - other - Convert to a C/C++ Project(Adds C/C++ Native)-next��ѡ�������ĿHelloWorld�����finish����һ����Ϊ�˸�����Ŀ���c/c++���ԣ�ʹ��Ŀ֧��c/c++��̡�
![](./images/converttoc1.png)
![](./images/converttoc2.png)

###���������Ҽ���Ŀ��HelloWorld - new - Folder�½�һ���ļ��У�����Ϊjni���Ҽ�jni�ļ��� - New - File �½�һ���ļ�����HelloWorld.c�������������c++д�ľ�����.cpp�������cд�ģ���׺����.c������ļ�����һ��Ҫ��ס���Ҽ�jni�ļ��� - New - File �½�һ���ļ�����Android.mk���ļ������������ὲ����
![](./images/newfolder1.png)

``` C
#include <jni.h>

Java_com_example_helloworld_MainActivity_stringFromJni( JNIEnv* env,
                                                  jobject thiz )
{
    return (*env)->NewStringUTF(env, "Hello from JNI !");
}
```
����HelloWorld.c�ļ��е����ݣ�����Ҫ����ͷ�ļ�jni.h��������Ҫ��java�˵İ���������������ȫ��Ӧ����������JNIEnv* env,jobject thiz �ǹ̶�д�������stringFromJniû�в�������д�����������Ϳ����ˡ�

``` C
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := HelloWorld
LOCAL_SRC_FILES := HelloWorld.c

include $(BUILD_SHARED_LIBRARY)
```
����Android.mk�ļ��е����ݣ��ص���LOCAL_MODULE    := HelloWorld��Ҫ������java��static������ָ����.so��������һ����
LOCAL_SRC_FILES := HelloWorld.c����Ҫ�����jni�ļ����µ�.c�ļ�������һ��

###���Ĳ���
��xml�����ļ��£�����Щ���ݣ�
``` xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.helloworld.MainActivity" >

    <TextView
        android:id="@+id/text"  
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />

    <Button   
        android:id="@+id/button"  
        android:layout_width="wrap_content"  
        android:layout_height="wrap_content"
        android:layout_below="@id/text"   
        android:text="@string/str_proc"/> 
        
</RelativeLayout>
```
��string.xml�ļ�����������
``` xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <string name="app_name">HelloWor1d</string>
    <string name="hello_world">Hello world!</string>
    <string name="str_proc">button</string>
    <string name="action_settings">Settings</string>

</resources>
```
ûʲô�ɽ��ģ��������һ��buttonһ��textview��

###���岽
�Ҽ���ĿHelloWorld - Properties - c/c++ Build - Tool Chain Editor����Current Builder��ΪAndroid Builder��Ȼ����c/c++ build����Makefile generate �µ� Generate Makefiles automatically�Ĺ�ȥ����
![](./images/toolchainediter1.png)
![](./images/toolchainediter2.png)

###������
�Ҽ���Ŀ��HelloWorld - clean project����ʱ��Ĺ����ļ�����Ӧ�þͳ�����һ��obj�ļ��У��ļ�������һ���ļ���HelloWorld.so����ʱ������ͨ��build���jni�ļ����µ�c�ļ����ɵģ���ʵ��ʱ�����jni�ļ���ɾ������������������ܣ���Ϊ����ʱ����õ���.so��⣬���������jni�ļ����µ�c�ļ�����ʱ���̻���ʾ�����Ŀ�д��󣬴�����HelloWorld.c�ļ��£���ֱ�ӰѴ���ɾ�������й��̾Ϳ����ˡ�

