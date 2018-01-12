package com.example.renkuo.rxjavademo.http

import com.example.renkuo.rxjavademo.model.GitHubRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path




/**
 * Created by renkuo on 2017/8/16.
 */
interface GitHubClient {

    //Call<>  1，不关心返回值的时候可以用Call<Void>  2,不知道返回类型的时候可以用Call<ResponseBody>
    @GET("/users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Call<List<GitHubRepo>>  //Path Parameters (Path("user"))

//    @GET("/tutorials")
//    fun getTutorials(
//            @Query("page") page: Int?,
//            @Query("order") order: String,
//            @Query("author") author: String,
//            @Query("published_at") date: Date
//    ): Call<List<Tutorial>>   //Query Parameters (@Query)



    //Url Handling Introduction
//    # Good Practice
//    base url: https://futurestud.io/api/
//    endpoint: my/endpoint
//    Result:   https://futurestud.io/api/my/endpoint
//
//    # Bad Practice
//    base url: https://futurestud.io/api
//    endpoint: /my/endpoint
//    Result:   https://futurestud.io/my/endpoint
//
//    # Example 1
//    base url: https://futurestud.io/api/v3/
//    endpoint: my/endpoint
//    Result:   https://futurestud.io/api/v3/my/endpoint
//
//    # Example 2
//    base url: https://futurestud.io/api/v3/
//    endpoint: /api/v2/another/endpoint
//    Result:   https://futurestud.io/api/v2/another/endpoint
//
//    # Example 3 — completely different url
//    base url: http://futurestud.io/api/
//    endpoint: https://api.futurestud.io/
//    Result:   https://api.futurestud.io/
//
//    # Example 4 — Keep the base url’s scheme
//    base url: https://futurestud.io/api/
//    endpoint: //api.futurestud.io/
//    Result:   https://api.futurestud.io/
//
//    # Example 5 — Keep the base url’s scheme
//    base url: http://futurestud.io/api/
//    endpoint: //api.github.com
//    Result:   http://api.github.com  ／

}
