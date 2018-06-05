package org.luyinbros.repository.impl;

import android.content.Context;

import org.luyinbros.beanmapper.annotation.Component;
import org.luyinbros.repository.local.LocalUserRepository;

//TODO sample delete
@Component
public class LocalUserRepositoryImpl implements LocalUserRepository {
    private Context context;

    public LocalUserRepositoryImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public String getToken() {
        return null;
    }
}
