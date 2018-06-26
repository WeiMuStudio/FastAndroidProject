package org.luyinbros.domain.core;

import android.content.Context;

import org.luyinbros.domain.LoginCase;

public interface CaseBeanFactory {

    Context context();

    LoginCase loginCase();

}
