package org.luyinbros.domain;

import org.luyinbros.repository.data.LoginInfoEntity;

import io.reactivex.Observable;


public interface LoginCase {

    Observable<LoginInfoEntity> login(String account, String passport);
}
