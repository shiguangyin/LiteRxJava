package com.masker.literxjava;

/**
 * @author masker
 * @date 28/01/2018
 * @Des
 */
public class Functions {

    public static final Consumer<Object> EMPTY_ON_NEXT = new Consumer<Object>() {
        @Override
        public void accept(Object o) {

        }
    };

    public static final Consumer<Throwable> EMPTY_ON_ERROR = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) {

        }
    };

    public static final Consumer<Disposable> EMPTY_ON_SUBSCRIBE = new Consumer<Disposable>() {
        @Override
        public void accept(Disposable o) {

        }
    };

    public static final Action EMPTY_ON_COMPLETE = new Action() {
        @Override
        public void call() {

        }
    };
}
