package com.guoanfamily.palmsale.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * excel操作，给javabean打上自定义注解
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/19 11:00
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {
    /**
     * excel导出 字段描述
     * excel导入 和entity的属性对应
     * name 和 column 互相绑定
     *
     * @return
     */
    public String name();

    /**
     * excel导出 数据库列属性
     * excel导入 和entity的属性对应
     * name 和 column 互相绑定
     *
     * @return
     */
    public String ref() default "";
}
