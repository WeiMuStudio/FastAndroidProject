package org.luyinbros.repository.core;


import org.luyinbros.repository.net.RemoteRepositoryClient;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface Remote {
    int value() default RemoteRepositoryClient.DEFAULT;
}
