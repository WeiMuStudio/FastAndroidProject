package org.luyinbros.repository;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luyinbros.repository.core.RepositoryFactoryClient;
import org.luyinbros.repository.local.LocalNewsRepository;
import org.luyinbros.repository.po.NewsPO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private LocalNewsRepository localNewsRepository;

    @Before
    public void initAllRepository() {
        Context context = InstrumentationRegistry.getTargetContext();
        localNewsRepository =
                RepositoryFactoryClient
                        .getLocalRepositoryFactory(context)
                        .newsRepository();
    }

    @Test
    public void insert() {
        List<NewsPO> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            list.add(new NewsPO(String.valueOf(i), "news" + i));
        }
        localNewsRepository.insert(list);
        Log.d("ifReadDB", "list:" + list.toString());
    }

    @Test
    public void deleteAll() {
        localNewsRepository.deleteAll();
    }


    @Test
    public void viewAllList() {
        insert();
        localNewsRepository.getNewsList(0, 10)
                .subscribe(new Consumer<List<NewsPO>>() {
                    @Override
                    public void accept(List<NewsPO> list) throws Exception {
                        Log.d("ifReadDB", "list:" + list.toString());
                    }
                });
    }


}
