package literxjava;

import literxjava.Observer;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public interface ObservableSource<T> {

    void subscribe(Observer<? super T> observer);

}
