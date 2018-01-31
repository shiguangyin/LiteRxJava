package com.masker.literxjava;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author shiguangyin
 * @Date 2018/1/28
 * @Desc
 */
public final class ObservableCreate<T> extends Observable<T> {
    private final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        SafeCreateEmitter<T> emitter = new SafeCreateEmitter<>(observer);
        observer.onSubscribe(emitter);
        try{
            source.subscribe(emitter);
        }catch (Throwable throwable){
            emitter.onError(throwable);
        }
    }

    /*
     * AtomicBoolean value == false means not disposed else means disposed
     */
    private static final class  SafeCreateEmitter<T> extends AtomicReference<Disposable> implements ObservableEmitter<T>, Disposable {

        private final Observer<? super T> observer;

        SafeCreateEmitter( Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void setDisposable(Disposable disposable) {
            DisposableHelper.setDisposable(this, disposable);
        }

        @Override
        public boolean dispose() {
            return DisposableHelper.dispose(this);
        }

        @Override
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this);
        }

        @Override
        public void onNext(T t) {
            if (t == null){
                onError(new NullPointerException("onNext called with null"));
            }
            if (!isDisposed()){
                try {
                    observer.onNext(t);
                }catch (Throwable throwable){
                    observer.onError(throwable);
                }
            }
        }

        @Override
        public void onComplete() {
            if(!isDisposed()){
                try {
                    observer.onComplete();
                } finally {
                    dispose();
                }
            }
        }

        @Override
        public void onError(Throwable throwable) {
            if (throwable == null){
                throwable  = new NullPointerException("onError called with null");
            }
            if (!isDisposed()){
                try {
                    observer.onError(throwable);
                }finally {
                    dispose();
                }
            }
        }
    }
}
