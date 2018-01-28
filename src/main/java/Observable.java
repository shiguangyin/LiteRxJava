import utils.Utils;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public abstract class Observable<T> {

    public void subscribe(){
        Subscriber<T> subscriber = new LambdaSubscriber<>(new EmptyConsumer<>(),
                new EmptyConsumer<T>(), new EmptyAction(), new EmptyConsumer<>());
        subscribeActual(subscriber);
    }

    public void subscribe(Subscriber<T> subscriber){
        Utils.checkNotNull(subscriber);
        subscribeActual(subscriber);
    }

    public void subscribe(Consumer<T> onNext){
        Utils.checkNotNull(onNext);
        Subscriber<T> subscriber = new LambdaSubscriber<>(new EmptyConsumer<>(),onNext,
                new EmptyAction(), new EmptyConsumer<>());
        subscribeActual(subscriber);
    }

    protected abstract void subscribeActual(Subscriber<T> subscriber);


    public static <T> Observable<T> unsafeCreate(UnsafeCreateOnSubscribe<T> onSubscribe){
        return new UnsafeCreateObservable<>(onSubscribe);
    }

    private static class LambdaSubscriber<T> implements Subscriber<T>{

        private Consumer<Disposable> onSubscribe;

        private Consumer<T> onNext;

        private Consumer<Throwable> onError;

        private Action onComplete;


        public LambdaSubscriber(Consumer<Disposable> onSubscribe, Consumer<T> onNext, Action onComplete, Consumer<Throwable> onError) {
            this.onSubscribe = onSubscribe;
            this.onNext = onNext;
            this.onComplete = onComplete;
            this.onError = onError;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            if (onSubscribe != null){
                onSubscribe.accept(disposable);
            }
        }

        @Override
        public void onNext(T t) {
            if (onNext != null){
                onNext.accept(t);
            }
        }

        @Override
        public void onComplete() {
            if (onComplete != null){
                onComplete.call();
            }
        }

        @Override
        public void onError(Throwable throwable) {
            if (onError != null){
                onError.accept(throwable);
            }
        }
    }

    private class EmptyConsumer<T> implements Consumer<T>{

        @Override
        public void accept(T t) {
            //fall through
        }
    }

    private class EmptyAction implements Action{

        @Override
        public void call() {
            //fall through
        }
    }
}
