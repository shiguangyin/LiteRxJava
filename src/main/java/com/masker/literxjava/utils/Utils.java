package com.masker.literxjava.utils;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public class Utils {
    public static void checkNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException("object can't be null");
        }
    }
}
