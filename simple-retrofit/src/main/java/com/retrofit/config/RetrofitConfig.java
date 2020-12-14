package com.retrofit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.retrofit.retrofit.RetrofitInterface;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Autowired
    private Interceptor commonInterceptor;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(commonInterceptor).build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json().featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .modules(new JavaTimeModule()).build();
    }

    // 메인 
    @Bean
    public Retrofit retrofit(ObjectMapper objectMapper, OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl("http://localhost:8080/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper)).client(okHttpClient).build();
    }

    // 하위 API Interface
    @Bean
    public RetrofitInterface customApiInterface(Retrofit retrofit) {
        return retrofit.create(RetrofitInterface.class);
    }
}
