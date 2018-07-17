package org.luyinbros.repository.local;

import org.luyinbros.repository.data.LoginInfoEntity;

//TODO sample delete
public interface LocalUserRepository {

    String getToken();

    boolean isLogin();

    void saveLoginInfo(LoginInfoEntity infoEntity);

    LoginInfoEntity getLoginInfo();

}
