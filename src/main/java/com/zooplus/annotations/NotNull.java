package com.zooplus.annotations;

import java.lang.annotation.*;
/**
 * @author germanjit singh version 1.0
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ValidateCondition
public @interface NotNull {

}