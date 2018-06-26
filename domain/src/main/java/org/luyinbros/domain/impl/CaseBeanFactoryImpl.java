package org.luyinbros.domain.impl;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import org.luyinbros.domain.LoginCase;
import org.luyinbros.domain.core.CaseBeanFactory;

public class CaseBeanFactoryImpl implements CaseBeanFactory {
    private Application application;

    public CaseBeanFactoryImpl(@NonNull Application application) {
        this.application = application;
    }

    @Override
    public Context context() {
        return application;
    }

    @Override
    public LoginCase loginCase() {
        return new LoginCaseImpl(context());
    }
}
