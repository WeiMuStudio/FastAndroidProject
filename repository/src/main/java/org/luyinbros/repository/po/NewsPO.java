package org.luyinbros.repository.po;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "news")

public class NewsPO {

    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "sourceId")
    private String sourceId;
    @ColumnInfo(name = "name")
    private String name;

    public NewsPO(@NonNull int id, @NonNull String sourceId, String name) {
        this.id = id;
        this.sourceId = sourceId;
        this.name = name;
    }

    @Ignore
    public NewsPO(@NonNull String sourceId, String name) {
        this.sourceId = sourceId;
        this.name = name;
    }


    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(@NonNull String sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NewsPO{" +
                "id=" + id +
                ", sourceId='" + sourceId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
