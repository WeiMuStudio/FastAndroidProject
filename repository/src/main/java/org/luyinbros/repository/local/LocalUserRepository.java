package org.luyinbros.repository.local;

import org.luyinbros.beanmapper.ScopeType;
import org.luyinbros.beanmapper.annotation.Scope;

//TODO sample delete
@Scope(ScopeType.PROTOTYPE)
public interface LocalUserRepository {

    String getToken();

}
