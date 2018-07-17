package org.luyinbros.repository.core;

import android.app.Application;
import android.content.Context;

import org.luyinbros.bean.AbstractBeanFactory;

public abstract class AbstractRepositoryFactory extends AbstractBeanFactory {
    private Application application;

    public AbstractRepositoryFactory(Application application) {
        this.application = application;
    }

    protected Context context() {
        return application;
    }
}
