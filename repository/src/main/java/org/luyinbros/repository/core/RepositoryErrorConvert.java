package org.luyinbros.repository.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.luyinbros.error.Converter;
import org.luyinbros.error.ErrorHandler;
import org.luyinbros.repository.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;




public class RepositoryErrorConvert implements Converter {
    private Context context;

    public RepositoryErrorConvert(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    @Nullable
    @Override
    public ErrorHandler convert(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return new NormalErrorHandler(context, throwable, R.string.msg_connection_failure);
        } else if (throwable instanceof ConnectException) {
            return new NormalErrorHandler(context, throwable, R.string.msg_connection_failure);
        } else if (throwable instanceof SSLHandshakeException) {
            return new NormalErrorHandler(context, throwable, R.string.msg_ssl_handshake_failure);
        } else if (throwable instanceof SocketTimeoutException || throwable instanceof UnknownHostException) {
            return new NormalErrorHandler(context, throwable, R.string.msg_net_time_out);
        } else {
            //JsonParseException ParseException JSONException etc
            return new NormalErrorHandler(context, throwable, R.string.msg_server_failure);
        }
    }



    private static class NormalErrorHandler implements ErrorHandler {
        private Context context;
        @StringRes
        private int res;
        private Throwable throwable;

        public NormalErrorHandler(Context context,
                                  Throwable throwable,
                                  int res) {
            this.context = context;
            this.throwable = throwable;
            this.res = res;
        }

        @Override
        public String errorMessage() {
            return context.getString(res);
        }

        @Override
        public Throwable getThrowable() {
            return throwable;
        }

        @Override
        public boolean equals(String name) {
            return false;
        }
    }
}
