package org.weimu.project.module.passport;

import org.luyinbros.core.rx.CombineDisposable;
import org.luyinbros.domain.LoginCase;
import org.luyinbros.domain.core.CaseFactoryClient;
import org.luyinbros.domain.core.DomainException;
import org.luyinbros.domain.core.SingleDomainObserver;
import org.luyinbros.presentation.BasePresenter;
import org.luyinbros.repository.data.LoginInfoEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.LoginView>
        implements LoginContract.LoginPresenter {
    private LoginCase loginCase;
    private CombineDisposable combineDisposable;

    @Override
    public void onAttach(LoginContract.LoginView view) {
        super.onAttach(view);
        loginCase = CaseFactoryClient.get(getApplicationContext()).loginCase();
        combineDisposable = new CombineDisposable();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        combineDisposable.dispose();
    }

    @Override
    public void login(String account, String passport) {
        loginCase.login(account, passport)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleDomainObserver<LoginInfoEntity>() {
                    @Override
                    public void onStart(Disposable disposable) {
                        mView.setLoginButtonEnable(false);
                        combineDisposable.addDisposable("login", disposable);
                    }

                    @Override
                    public void onSuccess(LoginInfoEntity value) {
                        mView.setLoginButtonEnable(true);
                        mView.loginSuccess();
                    }

                    @Override
                    public void onFailure(DomainException e) {
                        mView.setLoginButtonEnable(true);
                        mView.loginFailure(e.message());
                    }
                });
    }
}
