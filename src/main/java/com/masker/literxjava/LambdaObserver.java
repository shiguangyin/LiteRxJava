package com.masker.literxjava;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public class LambdaObserver<T> implements Observer<T> {

    private Consumer<? super Disposable> onSubscribe;

    private Consumer<? super T> onNext;

    private Consumer<? super Throwable> onError;

    private Action onComplete;


    public LambdaObserver(Consumer<? super Disposable> onSubscribe, Consumer<? super T> onNext, Action onComplete, Consumer<? super Throwable> onError) {
        this.onSubscribe = onSubscribe;
        this.onNext = onNext;
        this.onComplete = onComplete;
        this.onError = onError;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        if (onSubscribe != null) {
            onSubscribe.accept(disposable);
        }
    }

    @Override
    public void onNext(T t) {
        if (onNext != null) {
            onNext.accept(t);
        }
    }

    @Override
    public void onComplete() {
        if (onComplete != null) {
            onComplete.call();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (onError != null) {
            onError.accept(throwable);
        }
    }
}
