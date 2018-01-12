package com.example.renkuo.rxjavademo.model

import java.util.*

/**
 * Created by renkuo on 2017/9/26.
 */
//@Query("page") int page,
//@Query("order") String order,
//@Query("author") String author,
//@Query("published_at") Date date,
data class News(var page: Int, var order: String, var author :String, var published_at: Date)