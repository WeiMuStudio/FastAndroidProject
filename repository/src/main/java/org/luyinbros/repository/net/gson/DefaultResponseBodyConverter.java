package org.luyinbros.repository.net.gson;

import android.text.TextUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;

import org.luyinbros.log.LogA;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Description:
 * Author: 洪培林
 * Date: 2017-04-12 18:11
 */
final class DefaultResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> mBasicAdapter;
    private Type type;

    DefaultResponseBodyConverter(TypeAdapter<T> basicAdapter, Type type) {
        mBasicAdapter = basicAdapter;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T convert(ResponseBody value) throws IOException {
        String data = value.string();
        LogA.d(data);
        if (type == String.class) {
            return (T) data;
        }
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(data);
        if (TextUtils.isEmpty(data)
                || data.equalsIgnoreCase("null")
                || data.equals("{}")
                || rootElement.isJsonNull()) {
            return emptyResult();
        } else if (rootElement.isJsonObject()) {
            return presentResult(mBasicAdapter.fromJson(data));
        } else if (rootElement.isJsonArray()) {
            return presentResult(mBasicAdapter.fromJson(data));
        }
        //Safety .  execute impossibly
        return null;
    }

    private T emptyResult() throws IOException {
        T resultBean = mBasicAdapter.fromJson("{}");
        return resultBean;
    }

    private T presentResult(T resultBean) throws IOException {
        return resultBean;
    }
}