package org.luyinbros.repository.core;

import android.content.Context;

import org.luyinbros.repository.local.LocalUserRepository;
import org.luyinbros.repository.main.UserRepository;
import org.luyinbros.repository.remote.RemotePassportRepository;

public interface RepositoryFactory {

    Context context();

    LocalUserRepository localUserRepository();

    UserRepository userRepository();

    RemotePassportRepository remotePassportRepository();
}
