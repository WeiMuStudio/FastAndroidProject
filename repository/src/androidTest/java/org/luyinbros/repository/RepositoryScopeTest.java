package org.luyinbros.repository;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luyinbros.repository.core.RepositoryFactory;
import org.luyinbros.repository.local.LocalUserRepository;
import org.luyinbros.repository.remote.RemotePassportRepository;


@RunWith(AndroidJUnit4.class)
public class RepositoryScopeTest {

    @Test
    public void instance() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Assert.assertFalse(RepositoryFactory.get(appContext).getRepository(LocalUserRepository.class)
                == RepositoryFactory.get(appContext).getRepository(LocalUserRepository.class));

        Assert.assertTrue(RepositoryFactory.get(appContext).getRepository(RemotePassportRepository.class)
                == RepositoryFactory.get(appContext).getRepository(RemotePassportRepository.class));
    }
}
