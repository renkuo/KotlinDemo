package com.example.renkuo.rxjavademo.http

import com.example.renkuo.rxjavademo.model.News
import com.example.renkuo.rxjavademo.model.Task
import com.example.renkuo.rxjavademo.model.User
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by renkuo on 2017/9/21.
 */
interface RequestDemo {

    //Send Objects as Request Body
    @POST("/tasks")
    fun createTask(@Body task: Task): Call<Task>

    //Encoded Requests
    // @FormUrlEncoded :can’t use this annotation for GET requests.
    @FormUrlEncoded
    @POST("/tasks")
    fun createTask(@Field("title") title: String): Call<Task>

    //The @Field annotation has an option field for encoding:like (@Field(value = "title", encoded = true) title: String)
    @POST("/tasks")
    fun createTasks(@Field(value = "title", encoded = true) title: String): Call<Task>

    //Form Encoded Requests Using an Array of Values
    @FormUrlEncoded
    @POST("/tasks")
    fun createTask(@Field("title") title: List<String>): Call<Task>

    //Form Encoded Requests Using FieldMap
    @FormUrlEncoded
    @PUT("user")
    fun update(@FieldMap fields: Map<String, String>): Call<User>

    @FormUrlEncoded
    @PUT("user")
    fun updates(@FieldMap(encoded = true) fields: Map<String, String>): Call<User>

    //static header
    @Headers("Cache-Control: max-age=640000")
    @GET("/tasks")
    fun getTasks(): Call<List<Task>>

    @Headers("X-Foo: Bar","X-Ping: Pong")
    @GET("/tasks/{task_id}")
    fun getTask(@Path("task_id") taskId: Long): Call<Task>

    //Dynamic header 可以覆盖传递给使用的OkHttp客户端的Interceptor实现中的值。
    @GET("/tasks")
    fun getTasks(@Header("Content-Range") contentRange: String): Call<List<Task>>

    //Dynamic headermap
    @GET("/tasks")
    fun getTasks(@HeaderMap headers: Map<String,String>): Call<List<Task>>

    //https://api.example.com/tasks?id=123
    @GET("/tasks")
    fun getTasks(@Query("id") taskId: Long): Call<List<Task>>

    //https://api.example.com/tasks?id=123&id=124&id=125
    @GET("/tasks")
    fun getTasks(@Query("id") taskIds: List<Long>): Call<List<Task>>

    //Now you can pass null for either of the defined query parameters order and page when calling the getTasks method.
    @GET("/tasks")
    fun getTasks(@Query("sort") order: String, @Query("page") page: Integer): Call<List<Task>>

    @GET("/news")
    fun getNews(@QueryMap options: Map<String,String>): Call<List<News>>

    @GET("/news")
    fun getNewss(@QueryMap(encoded = true) options: Map<String,String>): Call<List<News>>
}