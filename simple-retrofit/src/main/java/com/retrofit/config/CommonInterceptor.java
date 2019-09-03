package com.retrofit.config;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import okhttp3.Interceptor;
import okhttp3.Response;

@Component
public class CommonInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        return chain.proceed(
                chain.request().newBuilder().addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .addHeader("Cache-Control", "no-cache").addHeader("Cache-Control", "no-store").build());
    }
}
