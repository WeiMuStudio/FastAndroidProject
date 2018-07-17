package org.luyinbros.repository.impl;

import android.app.Application;

import org.luyinbros.repository.core.AbstractRepositoryFactory;
import org.luyinbros.repository.core.RemoteRepositoryFactory;
import org.luyinbros.repository.net.RemoteRepositoryClient;
import org.luyinbros.repository.remote.RemoteNewsRepository;
import org.luyinbros.repository.remote.RemotePassportRepository;

public class RemoteRepositoryFactoryImpl extends AbstractRepositoryFactory implements RemoteRepositoryFactory {

    public RemoteRepositoryFactoryImpl(Application application) {
        super(application);
    }

    @Override
    public RemotePassportRepository passportRepository() {
        return RemoteRepositoryClient.getDefault(context()).create(RemotePassportRepository.class);
    }

    /**
     * TODO  sample
     */
    @Override
    public RemoteNewsRepository newsRepository() {
        return RemoteRepositoryClient.getDefault(context()).create(RemoteNewsRepository.class);
    }
}
