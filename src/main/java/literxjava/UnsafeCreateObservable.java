package literxjava;

import literxjava.Observable;
import literxjava.ObservableSource;
import literxjava.Observer;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public final class UnsafeCreateObservable<T> extends Observable<T> {

    private ObservableSource<T> source;

    public UnsafeCreateObservable(ObservableSource<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        source.subscribe(observer);
    }
}
