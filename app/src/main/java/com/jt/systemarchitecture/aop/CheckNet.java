package com.jt.systemarchitecture.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/5/3 0003.
 * 标记切点 注解
 */
@Target(ElementType.METHOD) //注解放在哪个位置
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface CheckNet {

}
