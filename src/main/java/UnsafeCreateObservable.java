/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public class UnsafeCreateObservable<T> extends Observable<T>{

    private UnsafeCreateOnSubscribe<T> actual;

    public UnsafeCreateObservable(UnsafeCreateOnSubscribe<T> actual){
        this.actual = actual;
    }

    @Override
    protected void subscribeActual(Subscriber<T> subscriber) {
        actual.onSubscribe(subscriber);
    }
}
