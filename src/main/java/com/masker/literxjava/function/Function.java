package com.masker.literxjava.function;

/**
 * @Author shiguangyin
 * @Date 2018/1/30
 * @Desc
 */
public interface Function<T,R> {
    /**
     *
     * @param t input
     * @return output
     */
    R apply(T t);

}
