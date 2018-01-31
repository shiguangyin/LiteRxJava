package com.masker.literxjava;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public final class ObservableUnsafeCreate<T> extends Observable<T> {

    private ObservableSource<T> source;

    public ObservableUnsafeCreate(ObservableSource<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        source.subscribe(observer);
    }
}
