package org.luyinbros.domain.impl;

import android.content.Context;
import android.text.TextUtils;

import org.luyinbros.domain.LoginCase;
import org.luyinbros.domain.R;
import org.luyinbros.domain.core.DomainException;
import org.luyinbros.repository.core.RepositoryFactoryClient;
import org.luyinbros.repository.data.LoginInfoEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


class LoginCaseImpl implements LoginCase {
    private Context context;

    LoginCaseImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<LoginInfoEntity> login(String account, String passport) {
        if (TextUtils.isEmpty(account)) {
            return Observable.error(new DomainException(context.getString(R.string.msg_failure_login_account_empty)));
        } else if (TextUtils.isEmpty(passport)) {
            return Observable.error(new DomainException(context.getString(R.string.msg_failure_login_passport_empty)));
        }
        return RepositoryFactoryClient
                .getRemoteRepositoryFactory(context)
                .passportRepository()
                .login(account, passport)
                .flatMap(new Function<LoginInfoEntity, ObservableSource<LoginInfoEntity>>() {
                    @Override
                    public ObservableSource<LoginInfoEntity> apply(LoginInfoEntity infoEntity) throws Exception {
                        RepositoryFactoryClient.getLocalRepositoryFactory(context)
                                .userRepository().saveLoginInfo(infoEntity);
                        return Observable.just(infoEntity);
                    }
                });
    }
}
