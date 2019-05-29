package com.example.buildings.ui.News

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.buildings.model.News

class NewsVM(application: Application) : AndroidViewModel(application) {
    var repository: NewsRepository
        private set

    var news: LiveData<List<News>> = MutableLiveData<List<News>>()

    var isLoading: LiveData<Boolean>

    init {
        repository = NewsRepository()
        news = repository.news
        isLoading = repository.isLoading
    }
}