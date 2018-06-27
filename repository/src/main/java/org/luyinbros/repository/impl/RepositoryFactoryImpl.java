package org.luyinbros.repository.impl;

import android.app.Application;
import android.content.Context;

import org.luyinbros.bean.AbstractBeanFactory;
import org.luyinbros.repository.core.RepositoryFactory;
import org.luyinbros.repository.local.LocalUserRepository;
import org.luyinbros.repository.main.UserRepository;
import org.luyinbros.repository.net.RemoteRepositoryClient;
import org.luyinbros.repository.remote.RemotePassportRepository;

public class RepositoryFactoryImpl extends AbstractBeanFactory implements RepositoryFactory {
    private Application application;

    public RepositoryFactoryImpl(Application application) {
        this.application = application;
    }


    @Override
    public Context context() {
        return application;
    }

    @Override
    public LocalUserRepository localUserRepository() {
        return new LocalUserRepositoryImpl(context());
    }

    @Override
    public UserRepository userRepository() {
        return null;
    }

    @Override
    public RemotePassportRepository remotePassportRepository() {
        return RemoteRepositoryClient.getDefault(context()).create(RemotePassportRepository.class);
    }


}
