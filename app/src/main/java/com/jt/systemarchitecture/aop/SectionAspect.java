package com.jt.systemarchitecture.aop;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Administrator on 2018/5/3 0003.
 * 处理切点
 */
@Aspect
public class SectionAspect {
    /**
     * 找到处理的切点
     *
     * **(..)可以处理所有的方法
     */
    @Pointcut("execution(@com.jt.systemarchitecture.aop.CheckNet * *(..))")
    public void checkNetBehavior(){

    }

    /**
     * 处理切面
     * @param joinPoint
     * @throws Throwable
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable{
        //1.获取CheckNet注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if (checkNet != null){
            //2.判断有没有网络
            //怎么样获取Context
            Object object = joinPoint.getThis();//当前切点方法注解所在的类
            Context context = getContext(object);
            if (context != null){
                if (!isNetworkAvailable(context)){
                    Toast.makeText(context,"请检查你的网络环境，Ok?",Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 通过对象获取上下文
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity){
            return (Context) object;
        }else if (object instanceof Fragment){
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        }else if (object instanceof View){
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }

    /**
     * 检查当前网络是否可用
     *
     * @return
     */
    private static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
