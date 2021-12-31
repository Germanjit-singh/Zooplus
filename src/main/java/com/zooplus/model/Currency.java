package com.zooplus.model;
/**
 * @author germanjit singh version 1.0
 * */
public enum Currency {
    EUR;

    public static Currency getCurrency(String currency){
        return Currency.valueOf(currency);
    }
}
