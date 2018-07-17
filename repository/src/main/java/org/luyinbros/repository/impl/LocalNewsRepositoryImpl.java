package org.luyinbros.repository.impl;

import android.content.Context;

import org.luyinbros.repository.impl.dao.DefaultDatabase;
import org.luyinbros.repository.local.LocalNewsRepository;
import org.luyinbros.repository.po.NewsPO;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

class LocalNewsRepositoryImpl implements LocalNewsRepository {
    private Context context;

    LocalNewsRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<List<NewsPO>> getNewsList(final int page, final int pageSize) {
        return Observable.create(new ObservableOnSubscribe<List<NewsPO>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsPO>> e) throws Exception {
                e.onNext(DefaultDatabase.getInstance(context)
                        .newsDao().getNewsList(page * pageSize, pageSize));
                e.onComplete();
            }
        });
    }

    @Override
    public void insert(List<NewsPO> list) {
        DefaultDatabase.getInstance(context)
                .newsDao().insert(list);
    }

    @Override
    public void deleteAll() {
        DefaultDatabase.getInstance(context)
                .newsDao().deleteAll();
    }
}
