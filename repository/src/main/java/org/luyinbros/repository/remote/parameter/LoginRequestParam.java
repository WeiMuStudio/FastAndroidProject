package org.luyinbros.repository.remote.parameter;

import org.luyinbros.repository.core.ParamFactory;
import org.luyinbros.repository.net.RequestParameter;

import okhttp3.RequestBody;

public class LoginRequestParam implements RequestParameter {

    @Override
    public RequestBody getRequestBody() {
        return ParamFactory.jsonParamBody(this);
    }
}
