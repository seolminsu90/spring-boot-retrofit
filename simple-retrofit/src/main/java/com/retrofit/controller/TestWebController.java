package com.retrofit.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retrofit.domain.User;
import com.retrofit.retrofit.RetrofitInterface;

import retrofit2.Response;

@RestController
public class TestWebController {
    @Autowired
    RetrofitInterface apiInterface;

    @GetMapping("/remote/test/api")
    public User test() {
        User user = new User();
        user.setAge(20);
        user.setName("Seol");
        return user;
    }

    @GetMapping("/test/retrofit")
    public void testRetrofit() {
        try {
            Response<User> result = apiInterface.getRemoteTestApi().execute();
            System.out.println(result.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // @Query String queryStringValue
    // @Query Map<String, String> queryStringMapValue
    // @Path String pathVariable
    // @Part("description") RequestBody description,
    // @Part MultipartBody.Part file
}
