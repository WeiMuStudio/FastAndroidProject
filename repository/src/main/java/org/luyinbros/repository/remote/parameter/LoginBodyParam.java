package org.luyinbros.repository.remote.parameter;

import org.luyinbros.repository.core.ParamFactory;
import org.luyinbros.repository.net.RequestBodyParameter;

import okhttp3.RequestBody;

public class LoginBodyParam implements RequestBodyParameter {

    @Override
    public RequestBody requestBody() {
        return ParamFactory.jsonParamBody(this);
    }
}
