package org.luyinbros.domain.core;

import org.luyinbros.error.ErrorHandler;
import org.luyinbros.error.ErrorHandlerFactory;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class TaskObserver<T> implements Observer<T> {
    private Disposable disposable;

    public abstract void onStart(Disposable disposable);

    public abstract void onNext(T value);

    public abstract void onComplete();

    public abstract void onError(ErrorHandler errorHandler);


    @Override
    public final void onSubscribe(Disposable d) {
        disposable = d;
        onStart(d);
    }

    @Override
    public final void onError(Throwable e) {
        onError(ErrorHandlerFactory.from(e));
        if (disposable != null) {
            disposable.dispose();
        }
    }


}
