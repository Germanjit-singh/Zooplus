package com.zooplus.util;
/**
 * @author germanjit singh version 1.0
 * */
public class DateUtil {
    public static java.sql.Date getDate(){
        return new java.sql.Date(new java.util.Date().getTime());
    }
}
