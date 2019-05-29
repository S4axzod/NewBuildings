package com.example.buildings.ui.Feeds.Partners

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.buildings.model.Partner

class PartnersVM(application: Application) : AndroidViewModel(application) {
    var repository: PartnersRepository
        private set

    var partners: LiveData<List<Partner>> = MutableLiveData()

    var isLoading: LiveData<Boolean>

    init {
        repository = PartnersRepository()
        partners = repository.partners
        isLoading = repository.isPartnersLoading
    }
}