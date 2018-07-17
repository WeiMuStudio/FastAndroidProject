package org.luyinbros.repository.impl;

import android.app.Application;

import org.luyinbros.repository.core.AbstractRepositoryFactory;
import org.luyinbros.repository.core.LocalRepositoryFactory;
import org.luyinbros.repository.local.LocalNewsRepository;
import org.luyinbros.repository.local.LocalUserRepository;

public class LocalRepositoryFactoryImpl extends AbstractRepositoryFactory implements LocalRepositoryFactory {

    public LocalRepositoryFactoryImpl(Application application) {
        super(application);
    }

    @Override
    public LocalUserRepository userRepository() {
        LocalUserRepository localUserRepository;
        if (containsSingleton("LocalUserRepository")) {
            localUserRepository = (LocalUserRepository) getSingleton("LocalUserRepository");
        } else {
            localUserRepository = new LocalUserRepositoryImpl(context());
            registerSingleton("LocalUserRepository", localUserRepository);
        }
        return localUserRepository;
    }

    @Override
    public LocalNewsRepository newsRepository() {
        return new LocalNewsRepositoryImpl(context());
    }
}
