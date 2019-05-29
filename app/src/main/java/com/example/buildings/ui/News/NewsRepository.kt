package com.example.buildings.ui.News

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.buildings.api.Api
import com.example.buildings.model.News
import com.example.buildings.model.NewsResponse
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository: KoinComponent {
    var news: MutableLiveData<List<News>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    val api: Api by inject()

    init {
        getNews()
    }

    private fun getNews() {
        isLoading.postValue(true)
        val call: Call<NewsResponse> = api.news
        call.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                isLoading.postValue(false)

            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.d("NNews", "Size : " + response.body()?.data?.size)
                    isLoading.postValue(false)
                    news.postValue(response?.body()?.data)
                } else {
                    isLoading.postValue(false)
                }
            }
        })
    }
}