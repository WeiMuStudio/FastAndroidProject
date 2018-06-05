package org.luyinbros.domain;

import org.luyinbros.beanmapper.ScopeType;
import org.luyinbros.beanmapper.annotation.Scope;
import org.luyinbros.repository.data.LoginBean;

import io.reactivex.Observable;

@Scope(ScopeType.PROTOTYPE)
public interface LoginCase {

    Observable<LoginBean> login(String account, String passport);
}
