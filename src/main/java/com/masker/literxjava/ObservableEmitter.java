package com.masker.literxjava;

/**
 * @Author shiguangyin
 * @Date 2018/1/28
 * @Desc
 */
public interface ObservableEmitter<T> extends Emitter<T>{

    void setDisposable(Disposable disposable);

    boolean isDisposed();

}
