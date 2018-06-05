package org.weimu.project.module.passport;

import org.luyinbros.presentation.PresenterBase;
import org.luyinbros.presentation.ViewBase;

interface LoginContract {

    interface LoginView extends ViewBase<LoginPresenter> {
        void setLoginButtonEnable(boolean isEnable);

        void loginSuccess();

        void loginFailure(String msg);
    }

    interface LoginPresenter extends PresenterBase<LoginView> {

        void login(String account, String passport);
    }
}
