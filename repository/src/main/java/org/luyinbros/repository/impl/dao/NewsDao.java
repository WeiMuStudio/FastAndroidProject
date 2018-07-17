package org.luyinbros.repository.impl.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.UiThread;

import org.luyinbros.repository.po.NewsPO;

import java.util.List;

@Dao
public interface NewsDao {

    @UiThread
    @Query("SELECT * FROM news limit :row,:number")
    List<NewsPO> getNewsList(int row, int number);

    @UiThread
    @Insert
    void insert(List<NewsPO> list);

    @UiThread
    @Query("DELETE FROM news")
    void deleteAll();
}
