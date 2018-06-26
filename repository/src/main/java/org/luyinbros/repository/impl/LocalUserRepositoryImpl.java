package org.luyinbros.repository.impl;

import android.content.Context;

import org.luyinbros.repository.local.LocalUserRepository;

//TODO sample delete

class LocalUserRepositoryImpl implements LocalUserRepository {
    private Context context;

    LocalUserRepositoryImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public String getToken() {
        return null;
    }
}
