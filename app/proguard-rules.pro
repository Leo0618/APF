#指定代码的压缩级别
-optimizationpasses 5
#包名不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
#优化  不优化输入的类文件
-dontoptimize
#预校验
-dontpreverify
#混淆时是否记录日志
-verbose
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
#忽略警告
-ignorewarning
-dontwarn
#如果引用了v4或者v7包
-dontwarn android.support.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}
#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
  !static !transient <fields>;
  private void writeObject(java.io.ObjectOutputStream);
  private void readObject(java.io.ObjectInputStream);
  java.lang.Object writeReplace();
  java.lang.Object readResolve();
}
#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
-keep public class * implements java.io.Serializable {
       public *;
       static final long serialVersionUID;
       !static !transient <fields>;
       private void writeObject(java.io.ObjectOutputStream);
       private void readObject(java.io.ObjectInputStream);
       java.lang.Object writeReplace();
       java.lang.Object readResolve();
}

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    static transient <fields>;
    private <fields>;
    private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

##################################################################### 项目通用处理
# 保持下面的api类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

# 自定义view
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# keep 泛型
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

##################################################################### 项目特殊处理
# javabean
-keep class com.leo618.apf.bean.** { *; }
-dontwarn com.leo618.hellome.hello.bean.**

#net cookie
-keep class com.leo618.apf.manager.net.okhttpwrap.cookie.** { *; }

-keep class com.lzj.appupdate.** { *; }
-dontwarn com.lzj.appupdate.**

##################################################################### 第三方SDK
# fastjson
-keep class com.alibaba.fastjson.**{*;}
-dontwarn com.alibaba.fastjson.**

#### otto
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

##### ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#### greenDao
-keep class de.greenrobot.dao.** {*;}
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static Java.lang.String TABLENAME;
}
-keep class **$Properties

# rxAndroid
-keep class io.reactivex.android.**{*;}
-dontwarn io.reactivex.android.**
# rxJava
-keep class io.reactivex.**{*;}
-dontwarn io.reactivex.**
# reactivestreams
-keep class org.reactivestreams.**{*;}
-dontwarn org.reactivestreams.**

# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature-keepattributes Exceptions