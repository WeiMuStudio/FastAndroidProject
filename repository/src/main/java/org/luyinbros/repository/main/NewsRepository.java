package org.luyinbros.repository.main;

import org.luyinbros.repository.data.NewsListEntity;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<NewsListEntity> getNewsList(int page, int size);
}
