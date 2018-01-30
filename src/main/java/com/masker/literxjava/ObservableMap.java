package com.masker.literxjava;

import com.masker.literxjava.function.Function;

/**
 * @Author shiguangyin
 * @Date 2018/1/30
 * @Desc transform type from UpStream to DownStream
 * @param <UpStream> source generic type
 * @param <DownStream> target generic type
*/

public final class ObservableMap<UpStream,DownStream> extends AbsObservableUpStreamWrapper<UpStream,DownStream>{

    private final Function<? super UpStream, ? extends DownStream> mapper;

    protected ObservableMap(ObservableSource<UpStream> source, Function<? super UpStream, ? extends DownStream> mapper) {
        super(source);
        this.mapper = mapper;
    }

    @Override
    protected void subscribeActual(Observer<? super DownStream> observer) {
        source.subscribe(new MapObserver<>(mapper, observer));
    }

    private static final class MapObserver<UpStream,DownStream> implements Observer<UpStream>{
        private final Function<? super UpStream, ? extends DownStream> mapper;
        private final Observer<? super DownStream> observer;

        private MapObserver(Function<? super UpStream, ? extends DownStream> mapper, Observer<? super DownStream> observer) {
            this.mapper = mapper;
            this.observer = observer;
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            observer.onSubscribe(disposable);
        }

        @Override
        public void onNext(UpStream upStream) {
            observer.onNext(mapper.apply(upStream));
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void onError(Throwable throwable) {
            observer.onError(throwable);
        }
    }

}
