package com.example.renkuo.rxjavademo.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




/**
 * Created by renkuo on 2017/8/17.
 */
class ServiceGenerator private constructor() {

//    //静态单利
//    companion object {
//
//        fun getInstance(): ServiceGenerator{
//            return SingleHolder.serviceGenerator
//        }
//
//        class SingleHolder{
//            companion object {
//                val serviceGenerator = ServiceGenerator()
//            }
//        }
//    }


    //懒汉模式单利
    private object Holder {
        val INSTANCE = ServiceGenerator()
    }
    companion object {
        val instance: ServiceGenerator by lazy { Holder.INSTANCE }
    }

    private var logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private var apiBaseUrl: String = "https://api.github.com/"

    //构建OkHttpClient并添加header
    private var httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor {
        val original = it.request()

        var originalHttpUrl = original.url()
        var url = originalHttpUrl.newBuilder()
                .addEncodedQueryParameter("apikey", "your-actual-api-key")
                .build()

        val request = original.newBuilder()
//                .header("User-Agent", "Your-App-Name")
//                .header("Accept", "application/vnd.yourapi.v1.full+json") //header:will override preexisting headers identified by key
//                .addHeader("Cache-Control", "no-cache")  //addheader:will add the header and don’t override preexisting ones
                .method(original.method(), original.body())
//                .url(url)// Add Query Parameters to every Request
                .build()

        return@addInterceptor it.proceed(request)
    }
    private var client: OkHttpClient = httpClient.build()


    //构建Retrofit.Builder
    private var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)

    //构建Retrofit
    private var retrofit: Retrofit = builder.build()

    /**
     * 构建一个封装请求的服务类接口
     */
    fun <S> createService(serviceClass: Class<S>): S {

        return retrofit.create(serviceClass)
    }

    fun changeApiBaseUrl(newApiBaseUrl: String) {
        apiBaseUrl = newApiBaseUrl

        builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl)
                .client(client)

    }

}