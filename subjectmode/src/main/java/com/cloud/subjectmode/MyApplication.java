package com.cloud.subjectmode;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Cloud on 2017/2/16.
 */
public class MyApplication extends Application {
  /*  * 内存泄露检测
    */
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);//内存泄露检测
    }
}
