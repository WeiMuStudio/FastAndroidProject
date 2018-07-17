package org.luyinbros.repository.remote;

import org.luyinbros.repository.data.LoginInfoEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

//TODO sample delete
public interface RemotePassportRepository {

    @FormUrlEncoded
    @POST("www.baddd.com")
    Observable<LoginInfoEntity> login(@Field("username") String userName,
                                      @Field("passport") String passport);
}
