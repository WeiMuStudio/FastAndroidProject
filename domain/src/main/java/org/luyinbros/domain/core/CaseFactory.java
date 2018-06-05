package org.luyinbros.domain.core;

import android.content.Context;

import org.luyinbros.beanmapper.BeanFactoryConfiguration;
import org.luyinbros.beanmapper.BeanMapper;
import org.luyinbros.domain.LoginCase;
import org.luyinbros.domain.impl.LoginCaseImpl;

import java.lang.annotation.Annotation;

public class CaseFactory {
    private static CaseFactory caseFactory;
    private BeanMapper beanMapper;


    public CaseFactory(Context context) {
        beanMapper = new BeanMapper.Builder()
                .setFactoryConfiguration(new CaseFactoryConfig(context.getApplicationContext()))
                .build();
    }

    public static CaseFactory get(Context context) {
        if (caseFactory == null) {
            caseFactory = new CaseFactory(context.getApplicationContext());
        }
        return caseFactory;
    }


    public <T> T getCase(Class<T> requireType) {
        return beanMapper.getBean(requireType);
    }

    private static class CaseFactoryConfig implements BeanFactoryConfiguration {
        private Context context;

        CaseFactoryConfig(Context context) {
            this.context = context;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T newInstance(Class<T> requiredType, Annotation[] annotations) {

            if (requiredType == LoginCase.class) {
                return (T) new LoginCaseImpl(context);
            }

            return null;
        }
    }
}
