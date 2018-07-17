package org.luyinbros.repository.net.gson;

import android.text.TextUtils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Description:
 * Author: 洪培林
 * Date: 2017-04-12 18:11
 */
public final class DefaultConverterFactory extends Converter.Factory {

    private static DefaultConverterFactory mFactory;

    public static DefaultConverterFactory create() {
        if (mFactory != null) {
            return mFactory;
        }

        return create(new GsonBuilder()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        Expose expose = f.getAnnotation(Expose.class);
                        if (expose != null && !expose.deserialize()) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        Expose expose = f.getAnnotation(Expose.class);
                        if (expose != null && !expose.serialize()) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeHierarchyAdapter(List.class, new JsonDeserializer<List<?>>() {

                    @SuppressWarnings("unchecked")
                    @Override
                    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        if (json.isJsonArray()) {
                            JsonArray array = json.getAsJsonArray();
                            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                            List list = new ArrayList();
                            for (JsonElement element : array) {
                                list.add(context.deserialize(element, itemType));
                            }
                            return list;
                        } else {
                            return Collections.EMPTY_LIST;
                        }

                    }
                }).registerTypeHierarchyAdapter(String.class, new JsonDeserializer<String>() {
                    @Override
                    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                        if (json.isJsonObject()) {
                            return json.getAsJsonObject().toString();
                        } else if (json.isJsonArray()) {
                            return json.getAsJsonArray().toString();
                        }
                        String target = json.getAsString();
                        return TextUtils.isEmpty(target) ? "" : target;
                    }
                }).create());
    }

    public static DefaultConverterFactory create(Gson gson) {
        if (mFactory != null) {
            return mFactory;
        }
        mFactory = new DefaultConverterFactory(gson);
        return mFactory;
    }

    private final Gson gson;

    private DefaultConverterFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }

        this.gson = gson;
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        TypeAdapter<?> basicAdapter = gson.getAdapter(TypeToken.get(type));
        return new DefaultResponseBodyConverter<>(basicAdapter, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new DefaultRequestBodyConverter<>();
    }

}
