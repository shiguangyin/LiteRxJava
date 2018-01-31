package com.masker.literxjava;

import com.masker.literxjava.utils.Utils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author shiguangyin
 * @Date 2018/1/28
 * @Desc
 */
public class DisposableHelper {
    private static final Disposable DISPOSED = new Disposable() {
        @Override
        public boolean dispose() {
            return false;
        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    };

    public static boolean setDisposable(AtomicReference<Disposable> field, Disposable disposable) {
        while (true) {
            Disposable current = field.get();
            if (current == DISPOSED) {
                if (disposable != null) {
                    disposable.dispose();
                }
                return false;
            }
            if (field.compareAndSet(current, disposable)) {
                if (disposable != null) {
                    disposable.dispose();
                }
                return true;
            }
        }
    }


    public static boolean dispose(AtomicReference<Disposable> filed) {
        Disposable current = filed.get();
        Disposable d = DISPOSED;
        if (current != d) {
            current = filed.get();
            while (current != d) {
                current = filed.getAndSet(d);
            }
            return true;
        }
        return false;
    }

    public static boolean isDisposed(AtomicReference<Disposable> filed) {
        Utils.checkNotNull(filed);
        return filed.get() == DISPOSED;
    }


}
