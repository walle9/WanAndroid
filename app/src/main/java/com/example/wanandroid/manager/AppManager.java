package com.example.wanandroid.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @ProjectName: MyMvpDemo
 * @Package: com.example.wanandroid.manager
 * @ClassName: AppManager
 * @Description: java类作用描述
 * @Author: walle
 * @CreateDate: 2019/9/4 15:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/9/4 15:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AppManager {

    private static volatile Stack<Activity> activityStack;

    public AppManager() {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     * @param cls   activity类名
     */
    public void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            if (cls.equals(next.getClass())) {
                iterator.remove();
                next.finish();
                next=null;
            }
        }
    }


    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     * @param cls activity类名
     */
    public void finishOthersActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            if (!cls.equals(next.getClass())) {
                iterator.remove();
                next.finish();
                next=null;
            }
        }
    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }


    /**
     * 应用程序退出
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }

}
