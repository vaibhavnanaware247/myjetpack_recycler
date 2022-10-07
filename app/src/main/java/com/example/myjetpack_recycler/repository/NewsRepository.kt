package com.example.myjetpack_recycler.repository



import com.example.myjetpack_recycler.data.Articles
import com.example.myjetpack_recycler.data.Model
import retrofit2.Response
import kotlin.collections.ArrayList

class NewsRepository {


    companion object{

        suspend fun getNews(country: String): ArrayList<Articles>?
        {
            val newsModel: Response<Model> = RetrofitService.getInstance().create(NewsService :: class.java).getNews(country , API.apiKey)
            return newsModel.body()?.articles
        }

    }

}