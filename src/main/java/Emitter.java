/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public interface Emitter<T> {

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);

}
