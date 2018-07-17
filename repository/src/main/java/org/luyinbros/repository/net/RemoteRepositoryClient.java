package org.luyinbros.repository.net;

import android.content.Context;
import android.util.SparseArray;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.luyinbros.log.LogA;
import org.luyinbros.repository.net.gson.DefaultConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

public class RemoteRepositoryClient {
    private static final int DEFAULT = 0;
    private static SparseArray<Retrofit> httpClientArray = new SparseArray<>();
    public static final String HEADER_CLIENT_NO_LOGIN_REQUEST = "Android_Client_No_Login_Request:1";

    public static Retrofit getDefault(Context context) {
        return getDefaultClient(context.getApplicationContext(), DEFAULT);
    }


    private static Retrofit getDefaultClient(Context context, int type) {
        Retrofit retrofit = httpClientArray.get(type);
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient
                    .Builder()
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new RequestInfoInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpURL.BASE)
                    .client(okHttpClient)
                    .addConverterFactory(DefaultConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            httpClientArray.put(DEFAULT, retrofit);
        }
        return retrofit;

    }

    private static class RequestInfoInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("method:").append(request.method()).append("\n");
            stringBuilder.append("URL:").append(request.url().toString()).append("\n");
            stringBuilder.append("header:").append(request.headers().toString()).append("\n");

            RequestBody body = request.body();
            if (body instanceof MultipartBody) {
                stringBuilder.append(getMultipartBodyInfo((MultipartBody) body));
            } else if (body instanceof FormBody) {
                stringBuilder.append(getFormBodyInfo((FormBody) body));
            }

            LogA.d(stringBuilder.toString());

            return chain.proceed(request);
        }

        private String getMultipartBodyInfo(MultipartBody multipartBody) {
            List<MultipartBody.Part> parts = multipartBody.parts();
            if (parts.size() != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MultipartBodyInfo: \n");
                for (int i = 0; i < parts.size(); i++) {
                    MultipartBody.Part part = parts.get(i);
                    stringBuilder.append("part: \n");
                    stringBuilder.append("part: ").append(part.headers().toString()).append("\n");
                    String length;
                    try {
                        length = String.valueOf(part.body().contentLength());
                    } catch (IOException e) {
                        length = "can't get the content length";
                    }
                    stringBuilder.append("mediaType: ").append(part.body().contentType())
                            .append("length: ").append(length);

                }
                return stringBuilder.toString();
            } else {
                return "no multipart body info";
            }

        }

        private String getFormBodyInfo(FormBody formBody) {
            int size = formBody.size();
            if (size != 0) {
                StringBuilder stringBuilder = new StringBuilder("FormBodyInfo: \n");
                for (int index = 0; index < size; index++) {
                    stringBuilder.append("name: ").append(formBody.name(index))
                            .append(" value: ").append(formBody.value(index)).append("\n");
                }
                return stringBuilder.toString();
            } else {
                return "no form body info";
            }

        }
    }
}
