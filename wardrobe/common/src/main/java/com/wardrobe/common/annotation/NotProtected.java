package com.wardrobe.common.annotation;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME) //JVM运行加载此注解
@Target({ElementType.TYPE, ElementType.METHOD}) //类,方法注解
@Inherited //允许子类继承此注解
public @interface NotProtected {}