package org.luyinbros.domain.core;

import android.content.Context;

import org.luyinbros.domain.R;
import org.luyinbros.repository.exception.CompatHttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

public final class CaseThrowableFactory {

    private static String httpMessage() {
        Context context = CaseFactoryClient.getApplication();
        if (context == null) {
            return "网络错误";
        } else {
            return context.getString(R.string.msg_server_failure);
        }
    }

    private static String connectMessage() {
        Context context = CaseFactoryClient.getApplication();
        if (context == null) {
            return "网络连接异常，请检查网络";
        } else {
            return context.getString(R.string.msg_connection_failure);
        }
    }

    private static String netTimeOutMessage() {
        Context context = CaseFactoryClient.getApplication();
        if (context == null) {
            return "网络超时";
        } else {
            return context.getString(R.string.msg_net_time_out);
        }
    }

    public static DomainException covertThrowable(Throwable e) {
        if (e instanceof DomainException) {
            return (DomainException) e;
        } else if (CompatHttpException.equals(e)) {
            return new DomainException(CaseThrowableFactory.httpMessage(), CompatHttpException.create(e));
        } else if (e instanceof ConnectException) {
            return new DomainException(CaseThrowableFactory.connectMessage(), CompatHttpException.create(e));
        } else if (e instanceof SSLHandshakeException) {
            return new DomainException(CaseThrowableFactory.httpMessage(), e);
        } else if (e instanceof SocketTimeoutException || e instanceof UnknownHostException) {
            return new DomainException(CaseThrowableFactory.netTimeOutMessage(), e);
        }else{
            return new DomainException(CaseThrowableFactory.httpMessage(), e);
        }

    }
}
