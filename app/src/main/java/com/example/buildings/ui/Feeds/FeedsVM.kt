package com.example.buildings.ui.Feeds

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.buildings.model.Building
import com.example.buildings.model.Event
import com.example.buildings.model.News

class FeedsVM(application: Application) : AndroidViewModel(application) {
    var repository: FeedsRepository
        private set
    var featured: LiveData<List<Event>> = MutableLiveData<List<Event>>()
        private set
    var building: LiveData<List<Building>> = MutableLiveData<List<Building>>()
        private set

    var isLoadingFeatured: LiveData<Boolean>
    var isLoadingBuilding: LiveData<Boolean>

    init {
        repository = FeedsRepository()
        featured = repository.featured
        building = repository.buildings

        isLoadingFeatured = repository.isLoading1
        isLoadingBuilding = repository.isLoading3
    }
}