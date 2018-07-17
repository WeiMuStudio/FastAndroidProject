package org.luyinbros.repository.remote;

import org.luyinbros.repository.data.NewsListEntity;
import org.luyinbros.repository.net.RemoteRepositoryClient;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RemoteNewsRepository {

    /**
     * 获取新闻地址
     */
    @GET("/news")
    @Headers(RemoteRepositoryClient.HEADER_CLIENT_NO_LOGIN_REQUEST)
    Observable<NewsListEntity> getNewsList(int page, int size);
}
