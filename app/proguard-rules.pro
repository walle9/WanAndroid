# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# 指定不去忽略包可见的库类的成员
-dontskipnonpubliclibraryclassmembers

# 屏蔽警告
-ignorewarnings

# 保留泛型
-keepattributes Signature

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 如果使用了上一行配置，还需要添加如下配置将源文件重命名为SourceFile，以便通过鼠标点击直达源文件
-renamesourcefileattribute SourceFile

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#webView-------------

#当webView中使用一些复杂的中,可能需要
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}

-keepclassmembers class * extends android.webkit.webViewClient {
      public void *(android.webkit.webView, java.lang.String);
}

#相册
-keepclassmembers class * extends android.webkit.WebChromeClient{
   public void openFileChooser(...);
}

#js
-keepclassmembers class com.xxx.xxx.JSInterface {
   public *;
}
#内部类
-keepattributes *JavascriptInterface*
-keepclassmembers class com.xxx.xxx.WebActivity$JSInterface {
    public *;
}
#webView-------------


#实体类
-keep class com.example.wanandroid.model.bean.*{*;}
-keep class com.example.wanandroid.model.event.*{*;}
-keep class com.example.wanandroid.model.http.HttpNoResult{*;}
-keep class com.example.wanandroid.model.http.HttpResult{*;}

#对含有反射类的处理
#-keep class com.example.wanandroid.model.bean.*{*;}

# 删除代码中Log相关的代码,但是要开启优化,build.gradle中可以换成proguard-android-optimize.txt
#-assumenosideeffects class android.util.Log{
#    public static *** v(...);
#    public static *** i(...);
#    public static *** d(...);
#    public static *** w(...);
#    public static *** e(...);
#}

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

#
# ----------------------------- 第三方 -----------------------------
#

-dontwarn com.google.gson.**
-keep class com.google.gson.**{*;}
-keep interface com.google.gson.**{*;}
#        。。。。。。


#
#---------------------------------androidX-------------------------
#
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

#
#---------------------------------EventBus-------------------------
#
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}


#
#---------------------------------okhttp3-------------------------
#

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform


#
#---------------------------------okio-------------------------
#

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*


#
#---------------------------------logger-------------------------
#
-dontwarn com.orhanobut.logger.**
-keep class com.orhanobut.logger.**{*;}
-keep interface com.orhanobut.logger.**{*;}


#
#---------------------------------glide-------------------------
#
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#
#---------------------------------banner-------------------------
#
-keep class com.youth.banner.** {
    *;
 }

-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule


#
#---------------------------------毛玻璃 realtimeblurview -------------------------
#
-keep class android.support.v8.renderscript.** { *; }
-keep class androidx.renderscript.** { *; }


#
#---------------------------------gif -------------------------
#
-keep public class pl.droidsonroids.gif.GifIOException{<init>(int);}
-keep class pl.droidsonroids.gif.GifInfoHandle{<init>(long,int,int,int);}

#
#---------------------------------agentweb -------------------------
#
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**

#
#---------------------------------persistentcookiejar -------------------------
#

-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#
#---------------------------------toast -------------------------
#
-keep class com.hjq.toast.** {*;}