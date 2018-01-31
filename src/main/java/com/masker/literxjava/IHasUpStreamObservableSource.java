package com.masker.literxjava;

/**
 * @Author shiguangyin
 * @Date 2018/1/30
 * @Desc has an upstream observable source
 */
public interface IHasUpStreamObservableSource<UpStream> {
    /**
     * @return observable source
     */
    ObservableSource<UpStream> getSource();

}
