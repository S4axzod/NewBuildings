package com.example.buildings.ui.Feeds

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.buildings.api.Api
import com.example.buildings.model.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedsRepository : KoinComponent {
    var featured: MutableLiveData<List<Event>> = MutableLiveData()
    var buildings: MutableLiveData<List<Building>> = MutableLiveData()

    var isLoading1 = MutableLiveData<Boolean>()
    var isLoading3 = MutableLiveData<Boolean>()

    val api: Api by inject()

    init {
        getFeaturedEvents()
        getBuildings()
    }

    private fun getFeaturedEvents() {
        isLoading1.postValue(true)
        val call: Call<BannerResponse> = api.featuredEvents
        call.enqueue(object : Callback<BannerResponse> {
            override fun onFailure(call: Call<BannerResponse>, t: Throwable) {
                isLoading1.postValue(false)

            }

            override fun onResponse(call: Call<BannerResponse>, response: Response<BannerResponse>) {
                if (response.isSuccessful) {
                    Log.d("MyBuilding", "Size : " + response.body()?.data?.size)
                    isLoading1.postValue(false)
                    featured.postValue(response?.body()?.data)
                } else {
                    isLoading1.postValue(false)
                }
            }
        })
    }

    private fun logger(msg: String) {
        Log.d("INHAUZ", msg)
    }

   private fun getBuildings(){
       logger("Buildings function called")
       isLoading3.postValue(true)
       val call: Call<BuildingResponse> = api.buildings
       call.enqueue(object : Callback<BuildingResponse>{
           override fun onFailure(call: Call<BuildingResponse>, t: Throwable) {
               logger("Buildings fail")
               isLoading3.postValue(false)
           }

           override fun onResponse(call: Call<BuildingResponse>, response: Response<BuildingResponse>) {
               logger("Buildings On Response")
               if(response.isSuccessful){
                   isLoading3.postValue(false)
                   buildings.postValue(response.body()?.data)
               } else {
                   isLoading3.postValue(false)
               }
           }
       })
   }
}