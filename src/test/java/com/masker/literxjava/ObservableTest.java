package com.masker.literxjava;

import com.masker.literxjava.function.Function;
import org.junit.Test;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        List<Integer> calls = new ArrayList<>();
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
                calls.add(1);
            }

            @Override
            public void onError(Throwable throwable) {
                assertNotNull(throwable);
            }
        });
        assertTrue(calls.contains(1));
    }

    @Test
    public void testMap(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "value="+integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                assertEquals(s,"value=1");
            }
        });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                assertEquals(s,"1");
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {
                
            }
        });

    }

}
