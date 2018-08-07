package com.wardrobe.controller.annotation;

import java.lang.annotation.*;

/**
 * Created by cxs on 2018/8/7.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommodityResolver {
}
