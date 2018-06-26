package org.luyinbros.domain.impl;

import android.content.Context;
import android.text.TextUtils;

import org.luyinbros.domain.LoginCase;
import org.luyinbros.domain.R;
import org.luyinbros.domain.core.DomainException;
import org.luyinbros.repository.core.RepositoryFactoryClient;
import org.luyinbros.repository.data.LoginBean;

import io.reactivex.Observable;


class LoginCaseImpl implements LoginCase {
    private Context context;

    LoginCaseImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<LoginBean> login(String account, String passport) {
        if (TextUtils.isEmpty(account)) {
            return Observable.error(new DomainException(context.getString(R.string.msg_failure_login_account_empty)));
        } else if (TextUtils.isEmpty(passport)) {
            return Observable.error(new DomainException(context.getString(R.string.msg_failure_login_passport_empty)));
        }
        return RepositoryFactoryClient.get(context)
                .remotePassportRepository()
                .login(account, passport);
    }
}
