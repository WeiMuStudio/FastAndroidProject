package org.luyinbros.repository.impl;

import android.app.Application;

import org.luyinbros.repository.core.AbstractRepositoryFactory;
import org.luyinbros.repository.core.MainRepositoryFactory;
import org.luyinbros.repository.main.NewsRepository;

public class MainRepositoryFactoryImpl extends AbstractRepositoryFactory implements MainRepositoryFactory {

    public MainRepositoryFactoryImpl(Application application) {
        super(application);
    }

    @Override
    public NewsRepository newsRepository() {
        return new NewsRepositoryImpl(context());
    }
}
