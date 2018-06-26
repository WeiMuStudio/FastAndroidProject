package org.luyinbros.core.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SingleObserver<T> implements Observer<T>{
    private Disposable disposable;

    protected abstract void onStart(Disposable disposable);

    protected abstract void onSuccess(T value);

    protected abstract void onFailure(Throwable e);

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
        onFailure(e);
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public final void onComplete() {

    }
}
