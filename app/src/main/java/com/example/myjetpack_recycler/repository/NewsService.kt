package com.example.myjetpack_recycler.repository


import com.example.myjetpack_recycler.data.Model
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

     @GET("top-headlines")
     suspend fun getNews(@Query("country") country : String , @Query("apiKey") apiKey : String):Response<Model>

}