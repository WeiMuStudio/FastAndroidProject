package org.luyinbros.domain.core;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import org.luyinbros.domain.impl.CaseBeanFactoryImpl;

public class CaseFactoryClient {
    private static CaseFactoryClient mFactory;
    private static final Object mLock = new Object();

    private CaseBeanFactory mConfigurable;

    private CaseFactoryClient(Application application) {
        mConfigurable = new CaseBeanFactoryImpl(application);
    }

    public static CaseBeanFactory get(Context context) {
        synchronized (mLock) {
            if (mFactory == null) {
                mFactory = new CaseFactoryClient((Application) context.getApplicationContext());
            }
            return mFactory.mConfigurable;
        }
    }

    @Nullable
    public static Context getApplication() {
        if (mFactory != null) {
            return mFactory.mConfigurable.context();
        }
        return null;
    }



}
