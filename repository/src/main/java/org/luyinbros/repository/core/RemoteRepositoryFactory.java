package org.luyinbros.repository.core;

import org.luyinbros.repository.remote.RemoteNewsRepository;
import org.luyinbros.repository.remote.RemotePassportRepository;

public interface RemoteRepositoryFactory {

    RemotePassportRepository passportRepository();

    /**
     * TODO  sample
     */
    RemoteNewsRepository newsRepository();
}
