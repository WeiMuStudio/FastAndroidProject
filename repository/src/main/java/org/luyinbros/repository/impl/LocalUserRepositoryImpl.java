package org.luyinbros.repository.impl;

import android.content.Context;
import android.content.SharedPreferences;

import org.luyinbros.repository.data.LoginInfoEntity;
import org.luyinbros.repository.local.LocalUserRepository;
import org.weimu.common.StringUtils;


class LocalUserRepositoryImpl implements LocalUserRepository {
    private SharedPreferences sharedPreferences;

    LocalUserRepositoryImpl(Context context) {
        sharedPreferences = context.getSharedPreferences("qaz", Context.MODE_PRIVATE);

    }

    @Override
    public String getToken() {
        return sharedPreferences.getString("doiz", "");
    }


    @Override
    public boolean isLogin() {
        return !StringUtils.isEmpty(getToken());
    }

    @Override
    public void saveLoginInfo(LoginInfoEntity infoEntity) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("doiz", infoEntity.getToken());
        editor.apply();
    }

    @Override
    public LoginInfoEntity getLoginInfo() {
        LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
        loginInfoEntity.setToken(getToken());
        return loginInfoEntity;
    }
}
