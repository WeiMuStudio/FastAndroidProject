package org.luyinbros.repository.core;

import android.content.Context;

import org.luyinbros.beanmapper.BeanFactoryConfiguration;
import org.luyinbros.beanmapper.BeanMapper;
import org.luyinbros.repository.impl.LocalUserRepositoryImpl;
import org.luyinbros.repository.local.LocalUserRepository;
import org.luyinbros.repository.net.RemoteRepositoryClient;

import java.lang.annotation.Annotation;

public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;
    private BeanMapper beanMapper;


    public RepositoryFactory(Context context) {
        beanMapper = new BeanMapper.Builder()
                .setFactoryConfiguration(new RepositoryFactoryConfig(context.getApplicationContext()))
                .build();
    }

    public static RepositoryFactory get(Context context) {
        if (repositoryFactory == null) {
            repositoryFactory = new RepositoryFactory(context.getApplicationContext());
        }
        return repositoryFactory;
    }


    public <T> T getRepository(Class<T> requireType) {
        return beanMapper.getBean(requireType);
    }

    private static class RepositoryFactoryConfig implements BeanFactoryConfiguration {
        private Context context;

        RepositoryFactoryConfig(Context context) {
            this.context = context;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T newInstance(Class<T> requiredType, Annotation[] annotations) {
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Remote) {
                        return RemoteRepositoryClient.get(context, ((Remote) annotation).value())
                                .create(requiredType);
                    }
                }
            }

            if (requiredType == LocalUserRepository.class) {
                return (T) new LocalUserRepositoryImpl(context);
            }

            return null;
        }
    }
}
