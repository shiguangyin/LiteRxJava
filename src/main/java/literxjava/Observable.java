package literxjava;

import literxjava.utils.Utils;

/**
 * @Author masker
 * @Date 28/01/2018
 * @Desc
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public void subscribe() {
        LambdaObserver<T> observer = new LambdaObserver<>(Functions.EMPTY_ON_SUBSCRIBE,
                Functions.EMPTY_ON_NEXT, Functions.EMPTY_ON_COMPLETE, Functions.EMPTY_ON_ERROR);
        subscribeActual(observer);
    }

    @Override
    public void subscribe(Observer<? super T> observer) {
        Utils.checkNotNull(observer);
        subscribeActual(observer);
    }

    public void subscribe(Consumer<? super T> onNext) {
        Utils.checkNotNull(onNext);
        LambdaObserver<T> observer = new LambdaObserver<>(Functions.EMPTY_ON_SUBSCRIBE,
                onNext, Functions.EMPTY_ON_COMPLETE, Functions.EMPTY_ON_ERROR);
        subscribeActual(observer);
    }

    public void subscribe(Consumer<? super T> onNext, Consumer<Throwable> onError) {
        Utils.checkNotNull(onNext);
        Utils.checkNotNull(onError);
        LambdaObserver<T> observer = new LambdaObserver<>(Functions.EMPTY_ON_SUBSCRIBE, onNext,
                Functions.EMPTY_ON_COMPLETE, onError);
        subscribeActual(observer);
    }

    public void subscribe(Consumer<? super T> onNext, Consumer<Throwable> onError, Action onComplete) {
        Utils.checkNotNull(onNext);
        Utils.checkNotNull(onError);
        Utils.checkNotNull(onComplete);
        LambdaObserver<T> observer = new LambdaObserver<>(Functions.EMPTY_ON_SUBSCRIBE, onNext, onComplete, onError);
        subscribeActual(observer);
    }

    public void subscribe(Consumer<? super T> onNext, Consumer<Throwable> onError, Action onComplete, Consumer<Disposable> onSubscribe) {
        Utils.checkNotNull(onNext);
        Utils.checkNotNull(onError);
        Utils.checkNotNull(onComplete);
        Utils.checkNotNull(onSubscribe);
        LambdaObserver<T> observer = new LambdaObserver<>(onSubscribe, onNext, onComplete, onError);
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<? super T> observer);


    public static <T> Observable<T> unsafeCreate(ObservableSource<T> source) {
        Utils.checkNotNull(source);
        if (source instanceof Observable) {
            throw new IllegalArgumentException("source can't be observable");
        }
        return new UnsafeCreateObservable<>(source);
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> onSubscribe) {
        Utils.checkNotNull(onSubscribe);
        return new SafeCreateObservable<>(onSubscribe);
    }
}
