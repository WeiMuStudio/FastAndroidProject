package org.luyinbros.core.rx;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Created by Andy Hong on 11/30/2017.
 */

public class CombineDisposable implements Disposable {
    private Map<String, Disposable> mMap = new HashMap<>();

    /**
     * 添加
     * @param key
     * @param disposable
     */
    public void addDisposable(String key, Disposable disposable) {
        dispose(key);
        mMap.put(key, disposable);
    }

    public void dispose(String key) {
        Disposable disposable = mMap.get(key);
        if (disposable != null) {
            DisposableUtils.dispose(disposable);
            mMap.remove(key);
        }
    }

    public boolean isDisposable(String key) {
        return DisposableUtils.isDisposed(mMap.get(key));

    }

    @Override
    public void dispose() {
        for (Map.Entry<String, Disposable> entry : mMap.entrySet()) {
            Disposable disposable = entry.getValue();
            DisposableUtils.dispose(disposable);
        }
        mMap.clear();
    }


    @Override
    public boolean isDisposed() {
        if (mMap.size() == 0) {
            return true;
        } else {
            for (Map.Entry<String, Disposable> entry : mMap.entrySet()) {
                Disposable disposable = entry.getValue();
                if (!DisposableUtils.isDisposed(disposable)) {
                    return false;
                }
            }
        }
        return true;
    }
}
