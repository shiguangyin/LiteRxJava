package com.masker.literxjava;

/**
 * @Author shiguangyin
 * @Date 2018/1/30
 * @Desc base class of observable wrapper
 */
public abstract class AbsObservableUpStreamWrapper<UpStream, DownStream> extends Observable<DownStream> implements IHasUpStreamObservableSource<UpStream>{

    protected final ObservableSource<UpStream> source;


    protected AbsObservableUpStreamWrapper(ObservableSource<UpStream> source) {
        this.source = source;
    }

    @Override
    public ObservableSource<UpStream> getSource() {
        return source;
    }
}
