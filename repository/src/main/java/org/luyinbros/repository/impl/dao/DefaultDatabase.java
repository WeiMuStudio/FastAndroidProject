package org.luyinbros.repository.impl.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import org.luyinbros.repository.po.NewsPO;


@Database(entities = {NewsPO.class,}, version = 1, exportSchema = false)
public abstract class DefaultDatabase extends RoomDatabase {
    private static volatile DefaultDatabase INSTANCE;

    public abstract NewsDao newsDao();

    public static DefaultDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DefaultDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DefaultDatabase.class, "default.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
