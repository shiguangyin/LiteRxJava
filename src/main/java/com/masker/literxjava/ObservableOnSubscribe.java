package com.masker.literxjava;

/**
 * @Author shiguangyin
 * @Date 2018/1/28
 * @Desc
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(ObservableEmitter<T> emitter);

}
