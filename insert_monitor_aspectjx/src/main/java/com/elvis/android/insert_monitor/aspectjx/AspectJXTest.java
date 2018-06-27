package com.elvis.android.insert_monitor.aspectjx;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by conghongjie on 2018/6/27.
 */

@Aspect
public class AspectJXTest {

    private static final String TAG = "ConstructorAspect";

//    @Pointcut("call(* com.elvis.android.lib.insert_monitor.demo.Animal.fly(..))")
//    public void callMethod() {}
//
//    @Before("callMethod()")
//    public void beforeMethodCall(JoinPoint joinPoint) {
//        Log.e(TAG, "before->");
//        //Log.e(TAG, "before->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());
//    }
    @Around("call(* com.elvis.android.insert_monitor.aspectjx.Animal.fly(..))")
    public void aroundMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "around->" + joinPoint.getTarget().toString() + "#" + joinPoint.getSignature().getName());

        // 执行原代码
        joinPoint.proceed();
    }

    @Before("execution (* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        Log.e(TAG, joinPoint.getSignature().toString() + ":beforecall");
    }
}
