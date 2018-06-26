package org.luyinbros.repository.core;

import android.app.Application;
import android.content.Context;

import org.luyinbros.repository.impl.RepositoryFactoryImpl;

public class RepositoryFactoryClient {
    private static RepositoryFactoryClient mFactory;
    private static final Object mLock = new Object();

    private RepositoryFactory mConfigurable;


    private RepositoryFactoryClient(Application application) {
        mConfigurable = new RepositoryFactoryImpl(application);
    }

    public static RepositoryFactory get(Context context) {
        synchronized (mLock) {
            if (mFactory == null) {
                mFactory = new RepositoryFactoryClient((Application) context.getApplicationContext());
            }
            return mFactory.mConfigurable;
        }
    }

}
