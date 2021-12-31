package com.zooplus.annotations;

import java.lang.annotation.*;
/**
 * @author germanjit singh version 1.0
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ValidateCondition
public @interface NumberValue {


    long min() default 0;


    long max() default Long.MAX_VALUE;

}