package org.weimu.project.module.passport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.luyinbros.core.utils.ToastUtils;
import org.luyinbros.presentation.ViewActivity;
import org.weimu.project.R;

public class LoginActivity extends ViewActivity<LoginContract.LoginPresenter> implements LoginContract.LoginView {

    private EditText etAccount;
    private EditText etPassport;
    private Button btnLogin;

    @NonNull
    @Override
    protected LoginContract.LoginPresenter onCreatePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreateView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        etAccount = findViewById(R.id.et_account);
        etPassport = findViewById(R.id.et_passport);
        btnLogin = findViewById(R.id.btn_login);
    }


    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login(etAccount.getText().toString(),
                        etPassport.getText().toString());
            }
        });
    }

    @Override
    public void setLoginButtonEnable(boolean isEnable) {
        btnLogin.setEnabled(isEnable);
    }

    @Override
    public void loginSuccess() {
        ToastUtils.show(getApplicationContext(), "登录成功");
    }

    @Override
    public void loginFailure(String msg) {
        ToastUtils.show(getApplicationContext(), msg);
    }


}
