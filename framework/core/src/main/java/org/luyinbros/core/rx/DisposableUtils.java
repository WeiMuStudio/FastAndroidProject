package org.luyinbros.core.rx;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Andy Hong on 4/25/2017.
 */

public class DisposableUtils {

    public static boolean isDisposed(Disposable d) {
        return d == null || d.isDisposed();
    }


    public static void dispose(Disposable d) {
        if (!isDisposed(d)) {
            d.dispose();
        }
    }

}
