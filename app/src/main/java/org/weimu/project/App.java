package org.weimu.project;

import android.app.Application;

import org.luyinbros.domain.core.DomainErrorConvert;
import org.luyinbros.error.ErrorHandlerFactory;
import org.luyinbros.repository.core.RepositoryErrorConvert;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ErrorHandlerFactory.initialize(new ErrorHandlerFactory.Builder()
                .addConverter(new DomainErrorConvert())
                .addConverter(new RepositoryErrorConvert(this)));
    }
}
