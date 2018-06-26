package org.luyinbros.domain;

import org.luyinbros.repository.data.LoginBean;

import io.reactivex.Observable;


public interface LoginCase {

    Observable<LoginBean> login(String account, String passport);
}
