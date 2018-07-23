package com.wardrobe.common.annotation;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME) //JVM运行加载此注解
@Target(ElementType.FIELD) //属性注解
@Inherited //允许子类继承此注解 
public @interface GsonExclude {}
