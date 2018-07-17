package org.luyinbros.repository.local;

import org.luyinbros.repository.po.NewsPO;

import java.util.List;

import io.reactivex.Observable;

public interface LocalNewsRepository {

    Observable<List<NewsPO>> getNewsList(int page, int pageSize);

    void insert(List<NewsPO> list);

    void deleteAll();
}
