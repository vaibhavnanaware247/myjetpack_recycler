package com.example.myjetpack_recycler.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjetpack_recycler.data.Articles
import com.example.myjetpack_recycler.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    var newsList : List<Articles> by mutableStateOf(listOf())
     fun fetchNews(country : String)
    {
        viewModelScope.launch {
            val list = NewsRepository.getNews(country)
            if (list != null) {
                newsList = list
            }

        }
    }

}