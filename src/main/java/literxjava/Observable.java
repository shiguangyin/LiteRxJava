package literxjava;

import literxjava.utils.Utils;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public void subscribe(){
        LambdaObserver<T> observer = new LambdaObserver<>(Functions.EMPTY_ON_SUBSCRIBE,
                Functions.EMPTY_ON_NEXT, Functions.EMPTY_COMPLETE, Functions.EMPTY_ON_ERROR);
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
                onNext, Functions.EMPTY_COMPLETE, Functions.EMPTY_ON_ERROR);
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
}
