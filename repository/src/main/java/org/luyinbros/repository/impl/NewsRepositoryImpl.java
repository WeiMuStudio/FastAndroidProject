package org.luyinbros.repository.impl;

import android.content.Context;

import org.luyinbros.repository.core.RepositoryFactoryClient;
import org.luyinbros.repository.data.NewsListEntity;
import org.luyinbros.repository.main.NewsRepository;
import org.luyinbros.repository.po.NewsPO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

class NewsRepositoryImpl implements NewsRepository {

    private Context context;

    NewsRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<NewsListEntity> getNewsList(int page, int size) {
        return RepositoryFactoryClient
                .getRemoteRepositoryFactory(context)
                .newsRepository()
                .getNewsList(page, size)
                .onExceptionResumeNext(RepositoryFactoryClient
                        .getLocalRepositoryFactory(context)
                        .newsRepository()
                        .getNewsList(page, size)
                        .flatMap(new Function<List<NewsPO>, ObservableSource<NewsListEntity>>() {
                            @Override
                            public ObservableSource<NewsListEntity> apply(List<NewsPO> list) throws Exception {
                                NewsListEntity newsListEntity = new NewsListEntity();
                                List<NewsListEntity.ItemEntity> resultList = new ArrayList<>();
                                for (int i = 0; i < list.size(); i++) {
                                    NewsListEntity.ItemEntity itemEntity = new NewsListEntity.ItemEntity();
                                    itemEntity.setId(list.get(i).getSourceId());
                                    itemEntity.setName(list.get(i).getName());
                                    resultList.add(itemEntity);
                                }
                                newsListEntity.setItemEntities(resultList);
                                return Observable.just(newsListEntity);
                            }
                        }));
    }
}
