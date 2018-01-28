package literxjava;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public interface Observer<T> {

    void onSubscribe(Disposable disposable);

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);

}
