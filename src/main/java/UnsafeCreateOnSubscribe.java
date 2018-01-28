/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public interface UnsafeCreateOnSubscribe<T> {
    void onSubscribe(Subscriber<T> subscriber);
}
