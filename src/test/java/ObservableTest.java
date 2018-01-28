import static org.junit.Assert.*;

import literxjava.*;
import org.junit.Test;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public class ObservableTest {
    @Test
    public void testUnsafe(){
        Observable.unsafeCreate((ObservableSource<String>) subscriber -> {
            subscriber.onNext("onNext");
            subscriber.onComplete();
        }).subscribe(s -> {
            assertEquals(s, "onNext");
        });

        Observable.unsafeCreate(new ObservableSource<Integer>() {
            @Override
            public void subscribe(Observer<? super Integer> observer) {
                observer.onNext(1);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                assertEquals(integer, new Integer(1));
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    @Test
    public void testCreate(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("create");
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                assertNotNull(disposable);
            }

            @Override
            public void onNext(String s) {
                assertEquals("create",s);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {
                assertNotNull(throwable);
            }
        });
    }

}
