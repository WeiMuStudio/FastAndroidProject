package org.luyinbros.repository.core;

import org.luyinbros.repository.local.LocalNewsRepository;
import org.luyinbros.repository.local.LocalUserRepository;

public interface LocalRepositoryFactory {

    LocalUserRepository userRepository();

    LocalNewsRepository newsRepository();
}
