package org.luyinbros.repository.core;

import android.app.Application;
import android.content.Context;

import org.luyinbros.repository.impl.LocalRepositoryFactoryImpl;
import org.luyinbros.repository.impl.MainRepositoryFactoryImpl;
import org.luyinbros.repository.impl.RemoteRepositoryFactoryImpl;
import org.luyinbros.repository.impl.ViewRepositoryFactoryImpl;

public class RepositoryFactoryClient {
    private static RepositoryFactoryClient mFactory;
    private static final Object mLock = new Object();
    private Application application;


    private LocalRepositoryFactory localRepositoryFactory;
    private RemoteRepositoryFactory remoteRepositoryFactory;
    private MainRepositoryFactory mainRepositoryFactory;
    private ViewRepositoryFactory viewRepositoryFactory;

    private RepositoryFactoryClient(Context context) {
        this.application = (Application) context.getApplicationContext();
    }

    public static RepositoryFactoryClient get(Context context) {
        synchronized (mLock) {
            if (mFactory == null) {
                mFactory = new RepositoryFactoryClient((Application) context.getApplicationContext());
            }
            return mFactory;
        }
    }

    public static LocalRepositoryFactory getLocalRepositoryFactory(Context context) {
        return get(context)._getLocalRepositoryFactory();
    }

    public static RemoteRepositoryFactory getRemoteRepositoryFactory(Context context) {
        return get(context)._getRemoteRepositoryFactory();
    }

    public static MainRepositoryFactory getMainRepositoryFactory(Context context) {
        return get(context)._getMainRepositoryFactory();
    }

    public static ViewRepositoryFactory getViewRepositoryFactory(Context context) {
        return get(context)._getViewRepositoryFactory();
    }

    public void clearData() {

    }

    private LocalRepositoryFactory _getLocalRepositoryFactory() {
        synchronized (mLock) {
            if (localRepositoryFactory == null) {
                localRepositoryFactory = new LocalRepositoryFactoryImpl(application);
            }
            return localRepositoryFactory;
        }
    }

    private RemoteRepositoryFactory _getRemoteRepositoryFactory() {
        synchronized (mLock) {
            if (remoteRepositoryFactory == null) {
                remoteRepositoryFactory = new RemoteRepositoryFactoryImpl(application);
            }
            return remoteRepositoryFactory;
        }
    }

    private MainRepositoryFactory _getMainRepositoryFactory() {
        synchronized (mLock) {
            if (mainRepositoryFactory == null) {
                mainRepositoryFactory = new MainRepositoryFactoryImpl(application);
            }
            return mainRepositoryFactory;
        }
    }

    private ViewRepositoryFactory _getViewRepositoryFactory() {
        synchronized (mLock) {
            if (viewRepositoryFactory == null) {
                viewRepositoryFactory = new ViewRepositoryFactoryImpl(application);
            }
            return viewRepositoryFactory;
        }
    }
}
