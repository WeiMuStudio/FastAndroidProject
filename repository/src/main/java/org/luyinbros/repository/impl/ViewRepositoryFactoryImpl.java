package org.luyinbros.repository.impl;

import android.app.Application;

import org.luyinbros.repository.core.AbstractRepositoryFactory;
import org.luyinbros.repository.core.ViewRepositoryFactory;

public class ViewRepositoryFactoryImpl extends AbstractRepositoryFactory implements ViewRepositoryFactory{

    public ViewRepositoryFactoryImpl(Application application) {
        super(application);
    }
}
