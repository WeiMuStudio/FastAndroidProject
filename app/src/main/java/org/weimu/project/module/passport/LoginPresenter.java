package org.weimu.project.module.passport;

import org.luyinbros.core.rx.CombineDisposable;
import org.luyinbros.domain.LoginCase;
import org.luyinbros.domain.core.CaseFactory;
import org.luyinbros.domain.core.SingleTaskObserver;
import org.luyinbros.error.ErrorHandler;
import org.luyinbros.presentation.BasePresenter;
import org.luyinbros.repository.data.LoginBean;

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
        loginCase = CaseFactory.get(getApplicationContext()).getCase(LoginCase.class);
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
                .subscribe(new SingleTaskObserver<LoginBean>() {
                    @Override
                    public void onStart(Disposable disposable) {
                        mView.setLoginButtonEnable(false);
                        combineDisposable.addDisposable("login", disposable);
                    }

                    @Override
                    public void onSuccess(LoginBean value) {
                        mView.setLoginButtonEnable(true);
                        mView.loginSuccess();
                    }

                    @Override
                    public void onError(ErrorHandler errorHandler) {
                        mView.setLoginButtonEnable(true);
                        mView.loginFailure(errorHandler.errorMessage());
                    }
                });
    }
}
