package org.luyinbros.domain.core;

import android.support.annotation.Nullable;

import org.luyinbros.error.Converter;
import org.luyinbros.error.ErrorHandler;

/**
 * @see DomainException
 */
public class DomainErrorConvert implements Converter {

    @Nullable
    @Override
    public ErrorHandler convert(final Throwable throwable) {
        if (throwable instanceof DomainException) {
            return new ErrorHandler() {
                @Override
                public String errorMessage() {
                    return throwable.getMessage();
                }

                @Override
                public Throwable getThrowable() {
                    return throwable;
                }

                @Override
                public boolean equals(String name) {
                    return "domain".equals(name);
                }
            };
        }
        return null;
    }


}
