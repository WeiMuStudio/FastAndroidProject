package org.luyinbros.domain.core;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SingleDomainObserver<T> implements Observer<T> {
    private Disposable disposable;

    public abstract void onStart(Disposable disposable);

    public abstract void onSuccess(T value);

    public abstract void onFailure(DomainException e);

    @Override
    public final void onSubscribe(Disposable d) {
        disposable = d;
        onStart(d);
    }

    @Override
    public final void onNext(T value) {
        onSuccess(value);
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public final void onError(Throwable e) {
        onFailure(CaseThrowableFactory.covertThrowable(e));
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public final void onComplete() {

    }
}
